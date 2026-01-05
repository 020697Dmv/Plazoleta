package com.plazoleta.domain.usecase;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.AssignOrderRequestDto;
import com.plazoleta.application.dto.request.OrderRequestDto;
import com.plazoleta.application.dto.request.OrderStatusRequestDto;
import com.plazoleta.application.dto.request.SmsRequestDto;
import com.plazoleta.domain.api.IOrderServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.OrderListModel;
import com.plazoleta.domain.model.RestaurantEmployee;
import com.plazoleta.domain.model.User;
import com.plazoleta.domain.spi.IOrderPersistencePort;
import com.plazoleta.domain.spi.IPlatePersistencePort;
import com.plazoleta.domain.spi.IRestaurantEmployeePersistencePort;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.ISmsSenderPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.infrastructure.exception.NotPermissionuserException;
import com.plazoleta.infrastructure.exception.OrderNotFoundException;
import com.plazoleta.infrastructure.exception.RestaurantEmployeeNotFoundException;
import com.plazoleta.infrastructure.exception.UserNotFoundException;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import com.plazoleta.infrastructure.out.jpa.entity.OrderPlateEntity;
import com.plazoleta.infrastructure.out.jpa.entity.PlateEntity;
import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import com.plazoleta.infrastructure.out.jpa.entity.UserEntity;
import com.plazoleta.infrastructure.out.jpa.mapper.IPlateEntityMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderUseCase  implements IOrderServicePort{
	
	private final IOrderPersistencePort orderPersistencePort;

	private final IPlatePersistencePort platePersistencePort;

	
	private final IRestaurantPersistencePort restaurantPersistencePort;
	
	private final IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort;
	
	private final IUserPersistencePort userPersistencePort;

	private final ISmsSenderPersistencePort smsSenderPersistencePort;


	
	@Override
	public MessageResponse saveOrder(OrderRequestDto orderRequestDto) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
	    UserEntity userEntity = (UserEntity) auth.getPrincipal(); 
	    Long clientId=null;
	    boolean isClient = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("CLIENT"));
			if(isClient) {
			clientId = userEntity.getId();
			} else {
			throw new UserNotFoundException();
			}
	    List<String> inProcessStatuses = List.of("PENDIENTE", "EN_PREPARACION", "LISTO");
	    if (orderPersistencePort.existsByClientIdAndStatusIn(clientId, inProcessStatuses)) {
	        return new MessageResponse(String.format("Ya tienes un pedido en curso.", clientId));
	    }

	    RestaurantEntity restaurant = restaurantPersistencePort.findByIdEntity(orderRequestDto.getRestaurantId())
	            .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));

	    OrderEntity orderEntity = new OrderEntity();
	    orderEntity.setClientId(clientId);
	    orderEntity.setDate(LocalDateTime.now());
	    orderEntity.setStatus("PENDIENTE"); 
	    orderEntity.setRestaurant(restaurant); 

	    List<OrderPlateEntity> plates = orderRequestDto.getPlates().stream().map(dto -> {
	        PlateEntity plate = platePersistencePort.findById(dto.getPlateId())
	                .orElseThrow(() -> new RuntimeException("Plato no encontrado: " + dto.getPlateId()));

	        if (!plate.getRestaurant().getNit().equals(restaurant.getNit())) {
	            throw new RuntimeException("El plato " + plate.getNamePlate() + " no pertenece a este restaurante.");
	        }

	        OrderPlateEntity plateEntry = new OrderPlateEntity();
	        plateEntry.setPlate(plate); 
	        plateEntry.setQuantity(dto.getQuantity()); 
	        plateEntry.setOrder(orderEntity); 
	        return plateEntry;
	    }).toList();

	    orderEntity.setOrderPlates(plates);

	    orderPersistencePort.saveOrder(orderEntity);

	    return new MessageResponse(String.format("Order created with id Client %d", clientId));
	}


	@Override
	public List<OrderListModel> orders(OrderStatusRequestDto orderStatusRequestDto) {
		
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		    UserEntity userEntity = (UserEntity) auth.getPrincipal(); 
		    Long idEmpleado = userEntity.getId();
		   
		
		Optional<RestaurantEmployee>restaruantEmployeeId=restaurantEmployeePersistencePort.findByIdEmployee(idEmpleado);

		if(restaruantEmployeeId.isEmpty()) {
	        throw new RestaurantEmployeeNotFoundException();
		}
		
		List<OrderListModel> ordersIdRestaurant=orderPersistencePort.toResponseList(orderStatusRequestDto,restaruantEmployeeId.get().getIdRestaurant());
	
		return ordersIdRestaurant;
	}


	@Override
	public List<OrderListModel> ordersAsignStatus(AssignOrderRequestDto assignOrderRequestDto) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
	    UserEntity userEntity = (UserEntity) auth.getPrincipal(); 
	    Long idEmpleado = userEntity.getId();
	   
	
	    Optional<RestaurantEmployee>restaruantEmployeeId=restaurantEmployeePersistencePort.findByIdEmployee(idEmpleado);
	    if(restaruantEmployeeId.isEmpty()) {
	        throw new RestaurantEmployeeNotFoundException();
		}
	    
	    OrderEntity orderSaveEntity = orderPersistencePort.findById(assignOrderRequestDto.getOrderId(),restaruantEmployeeId.get().getIdRestaurant())
	    	    .map(order -> {
	    	        order.setFkEmployeeId(idEmpleado); 
	    	        
	    	        order.setStatus("EN_PREPARACION"); 
	    	        
	    	        return order;
	    	    })
	    	    .orElseThrow(() -> new OrderNotFoundException()); 
	    
		List<OrderListModel> ordersIdRestaurant=orderPersistencePort.asignnedStatusAsign(assignOrderRequestDto,restaruantEmployeeId.get().getIdRestaurant(),orderSaveEntity);

		return ordersIdRestaurant;
	}


	@Override
	public void sendSmdNotify(Long orderId) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
	    UserEntity userEntity = (UserEntity) auth.getPrincipal(); 
	    Long idEmpleado = userEntity.getId();
	   
	
	    Optional<RestaurantEmployee>restaruantEmployeeId=restaurantEmployeePersistencePort.findByIdEmployee(idEmpleado);
	    if(restaruantEmployeeId.isEmpty()) {
	        throw new RestaurantEmployeeNotFoundException();
		}
		
		
		String securityPin = String.format("%06d", new Random().nextInt(999999));
		
	 
		 OrderEntity orderSaveEntity = orderPersistencePort.findById(orderId,restaruantEmployeeId.get().getIdRestaurant())
		    	    .map(order -> {
		    	    	order.setStatus("LISTO");

		    	    	order.setSecurityPin(securityPin);
		    	        return order;
		    	    })
		    	    .orElseThrow(() -> new OrderNotFoundException()); 
		
		Optional<User> userNotify=userPersistencePort.findById(orderSaveEntity.getClientId());
		 
		SmsRequestDto smsRequestDto= new SmsRequestDto();
		
		smsRequestDto.setPhoneNumber(userNotify.get().getPhone());
		
		smsRequestDto.setMessage("Your order code is: " +securityPin);
		smsSenderPersistencePort.sendSmd(smsRequestDto);
		
		orderPersistencePort.saveOrder(orderSaveEntity);
	}


	@Override
	public MessageResponse updateStatusOrder(String secutiryCode, Long orderId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
	    UserEntity userEntity = (UserEntity) auth.getPrincipal(); 
	    Long idEmpleado = userEntity.getId();
	    
	    Optional<RestaurantEmployee>restaruantEmployeeId=restaurantEmployeePersistencePort.findByIdEmployee(idEmpleado);
	    if(restaruantEmployeeId.isEmpty()) {
	        throw new RestaurantEmployeeNotFoundException();
		}
	    
	    OrderEntity orderSaveEntity = orderPersistencePort.findByIdOrderEmployeeId(orderId,
	    		restaruantEmployeeId.get().getIdRestaurant(),
	    		restaruantEmployeeId.get().getIdEmployee())
	    	    .map(order -> {
	    	    	
	    	    	if(secutiryCode.equals(order.getSecurityPin()) && "LISTO".equals(order.getStatus())) {
		    	    	order.setStatus("ENTREGADO");

	    	    	} else {
	    	    		
	    		        throw new NotPermissionuserException();

	    	    	}

	    	        return order;
	    	    })
	    	    .orElseThrow(() -> new OrderNotFoundException()); 
	
		orderPersistencePort.saveOrder(orderSaveEntity);
		
	    return new MessageResponse(String.format("The order status has been updated to Delivered, client ID: ", orderId));
	}
	

}
