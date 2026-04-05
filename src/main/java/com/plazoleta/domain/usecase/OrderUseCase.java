package com.plazoleta.domain.usecase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.plazoleta.infrastructure.out.jpa.util.ConstantsClass;
import com.plazoleta.domain.api.IOrderServicePort;
import com.plazoleta.domain.model.AssignOrderRequest;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.OrderListModel;
import com.plazoleta.domain.model.OrderPlateRequest;
import com.plazoleta.domain.model.OrderRequest;
import com.plazoleta.domain.model.OrderStatusRequest;
import com.plazoleta.domain.model.Orders;
import com.plazoleta.domain.model.Plate;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.model.RestaurantEmployee;
import com.plazoleta.domain.model.SmsRequest;
import com.plazoleta.domain.model.User;
import com.plazoleta.domain.spi.IOrderPersistencePort;
import com.plazoleta.domain.spi.IPlatePersistencePort;
import com.plazoleta.domain.spi.IRestaurantEmployeePersistencePort;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.ISmsSenderPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.domain.exception.NotOrderCancelException;
import com.plazoleta.domain.exception.NotPermissionuserException;
import com.plazoleta.infrastructure.exception.OrderNotFoundException;
import com.plazoleta.infrastructure.exception.UserNotFoundException;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import com.plazoleta.infrastructure.out.jpa.entity.UserEntity;

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

	private static final Random RANDOM = new Random();


	@Override
	public MessageResponse saveOrder(OrderRequest orderRequestDto) {


	    Long clientId=getIdClienteByIdOrder();

	    List<String> inProcessStatuses = List.of(ConstantsClass.STATUSPENDIENTE, ConstantsClass.STATUSPREPARACION, ConstantsClass.STATUSLISTO);
	    if (orderPersistencePort.existsByClientIdAndStatusIn(clientId, inProcessStatuses)) {
	        return new MessageResponse("Ya tienes un pedido en curso."+ clientId);
	    }

	    Restaurant restaurantModel=restaurantPersistencePort.findById(orderRequestDto.getRestaurantId());


	    Orders orders= new Orders();
		orders.setClientId(clientId);
		orders.setDate(LocalDateTime.now());
		orders.setStatus(ConstantsClass.STATUSPENDIENTE);
		orders.setNit(restaurantModel.getNit());

	    List<OrderPlateRequest> platesModel = orderRequestDto.getPlates().stream().map(dto -> {
	    	Plate plate = platePersistencePort.findyByIdModel(dto.getPlateId());

	    	if (!plate.getRestaurant().equals(restaurantModel.getNit())) {
	    		throw new RuntimeException("El plato " + plate.getNamePlate() + " no pertenece a este restaurante.");
	    	}
	    	return dto;
	    }).toList();

		orderPersistencePort.saveOrderPlate(orders,platesModel);
	    return new MessageResponse(String.format("Order created with id Client %d", clientId));
	}


	@Override
	public List<OrderListModel> orders(OrderStatusRequest orderStatusRequest) {
		
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    UserEntity userEntity = (UserEntity) auth.getPrincipal(); 
		    Long idEmpleado = userEntity.getId();
		   
		
		RestaurantEmployee restaruantEmployeeId=restaurantEmployeePersistencePort.findByIdEmployee(idEmpleado);

		
	return orderPersistencePort.toResponseList(orderStatusRequest,restaruantEmployeeId.getIdRestaurant());

	}


	@Override
	public List<OrderListModel> ordersAsignStatus(AssignOrderRequest assignOrderRequest) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    UserEntity userEntity = (UserEntity) auth.getPrincipal(); 
	    Long idEmpleado = userEntity.getId();
	   
	
	    RestaurantEmployee restaruantEmployeeId=restaurantEmployeePersistencePort.findByIdEmployee(idEmpleado);
	   
	    
	    OrderEntity orderSaveEntity = orderPersistencePort.findById(assignOrderRequest.getOrderId(),restaruantEmployeeId.getIdRestaurant())
	    	    .map(order -> {
	    	        order.setFkEmployeeId(idEmpleado); 
	    	        
	    	        order.setStatus(ConstantsClass.STATUSPREPARACION); 
	    	        
	    	        return order;
	    	    })
	    	    .orElseThrow(() -> new OrderNotFoundException()); 
	    
		return orderPersistencePort.asignnedStatusAsign(assignOrderRequest,restaruantEmployeeId.getIdRestaurant(),orderSaveEntity);

	}


	@Override
	public void sendSmdNotify(Long orderId) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    UserEntity userEntity = (UserEntity) auth.getPrincipal(); 
	    Long idEmpleado = userEntity.getId();
	   
	
	    RestaurantEmployee restaruantEmployeeId=restaurantEmployeePersistencePort.findByIdEmployee(idEmpleado);
	    
		
		
		String securityPin = String.format("%06d", RANDOM.nextInt(999999));
		
	 
		 OrderEntity orderSaveEntity = orderPersistencePort.findById(orderId,restaruantEmployeeId.getIdRestaurant())
		    	    .map(order -> {
		    	    	order.setStatus(ConstantsClass.STATUSLISTO);

		    	    	order.setSecurityPin(securityPin);
		    	        return order;
		    	    })
		    	    .orElseThrow(() -> new OrderNotFoundException()); 
		
		User userNotify=userPersistencePort.findById(orderSaveEntity.getClientId());
		 
		SmsRequest smsRequestDto= new SmsRequest();
		
		smsRequestDto.setPhoneNumber(userNotify.getPhone());
		
		smsRequestDto.setMessage("Your order code is: " +securityPin);
		smsSenderPersistencePort.sendSmd(smsRequestDto);
		
		orderPersistencePort.saveOrder(orderSaveEntity);
	}


	@Override
	public MessageResponse updateStatusOrder(String secutiryCode, Long orderId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    UserEntity userEntity = (UserEntity) auth.getPrincipal(); 
	    Long idEmpleado = userEntity.getId();
	    
	    RestaurantEmployee restaruantEmployeeId=restaurantEmployeePersistencePort.findByIdEmployee(idEmpleado);
	   
	    
	    OrderEntity orderSaveEntity = orderPersistencePort.findByIdOrderEmployeeId(orderId,
	    		restaruantEmployeeId.getIdRestaurant(),
	    		restaruantEmployeeId.getIdEmployee())
	    	    .map(order -> {
	    	    	
	    	    	if(secutiryCode.equals(order.getSecurityPin()) && ConstantsClass.STATUSLISTO.equals(order.getStatus())) {
		    	    	order.setStatus(ConstantsClass.STATUSENTREGADO);

	    	    	} else {
	    	    		
	    		        throw new NotPermissionuserException();

	    	    	}

	    	        return order;
	    	    })
	    	    .orElseThrow(() -> new OrderNotFoundException()); 
	
		orderPersistencePort.saveOrder(orderSaveEntity);
		
	    return new MessageResponse("The order status has been updated to Delivered, client ID: "+ orderId);
	}


	@Override
	public MessageResponse cancelStatusOrder(Long orderId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    UserEntity userEntity = (UserEntity) auth.getPrincipal(); 
	    Long idEmpleado = userEntity.getId();
	        
	    OrderEntity orderSaveEntity = orderPersistencePort.findByIdAndClientId(orderId, idEmpleado)
	    	    .map(order -> {
	    	    	
	    	    	if(ConstantsClass.STATUSPENDIENTE.equals(order.getStatus())) {
		    	    	order.setStatus("CANCELADO");

	    	    	} else {
	    	    		
	    		        throw new NotOrderCancelException();

	    	    	}

	    	        return order;
	    	    })
	    	    .orElseThrow(() -> new OrderNotFoundException()); 
	
		orderPersistencePort.saveOrder(orderSaveEntity);
		
	    return new MessageResponse("The order status has been updated to Delivered, client ID: "+ orderId);
	}

	@Override
	public Long getIdClienteByIdOrder() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserEntity userEntity = (UserEntity) auth.getPrincipal();
		Long clientId=null;
		boolean isClient = auth.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("CLIENT"));
		if(isClient) {
			clientId = userEntity.getId();
			return clientId;
		} else {
			throw new UserNotFoundException();
		}
	}


}
