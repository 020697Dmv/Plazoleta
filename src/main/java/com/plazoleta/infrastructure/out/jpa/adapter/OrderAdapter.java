package com.plazoleta.infrastructure.out.jpa.adapter;

import java.util.List;
import java.util.Optional;

import com.plazoleta.domain.model.*;
import com.plazoleta.infrastructure.out.jpa.entity.OrderPlateEntity;
import com.plazoleta.infrastructure.out.jpa.entity.PlateEntity;
import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import com.plazoleta.infrastructure.out.jpa.repository.IRestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.plazoleta.domain.spi.IOrderPersistencePort;
import com.plazoleta.infrastructure.exception.OrderNotFoundException;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import com.plazoleta.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.plazoleta.infrastructure.out.jpa.repository.IOrderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderAdapter implements IOrderPersistencePort{
	
	private  final IOrderRepository orderRepository;
	private final IRestaurantRepository restaurantRepository;
	private final IOrderEntityMapper orderEntityMapper;
	
	@Override
	public OrderEntity saveOrder(OrderEntity orderEntity) {

		return orderRepository.save(orderEntity);
	}

	@Override
	public boolean existsByClientIdAndStatusIn(Long clientId, List<String> statuses) {
		return orderRepository.existsByClientIdAndStatusIn(clientId,statuses);
	}

	@Override
	public List<OrderListModel> toResponseList(OrderStatusRequest orderStatusRequestDto, Long idRestaurant) {
	    
	    Pageable pageableResponse = PageRequest.of(
	        orderStatusRequestDto.getPageRequest().getPage(),
	        orderStatusRequestDto.getPageRequest().getSize()
	    );
	    
	    Page<OrderEntity> orderPage = orderRepository.findAllByRestaurantAndStatus(
	        idRestaurant, 
	        orderStatusRequestDto.getStatus(), 
	        pageableResponse
	    );
	    
	    if (orderPage.isEmpty()) {
	        throw new OrderNotFoundException();
	    }
	    
		return orderEntityMapper.toOrderList(orderPage.getContent());
	    
	}

	@Override
	public List<OrderListModel> asignnedStatusAsign(AssignOrderRequest assignOrderRequest, Long idRestaurant,OrderEntity orderEntity) {
		
		Pageable pageableResponse = PageRequest.of(
				assignOrderRequest.getPageRequest().getPage(),
				assignOrderRequest.getPageRequest().getSize()
		    );
		 Page<OrderEntity> orderPage = orderRepository.findAllByRestaurant(
			        idRestaurant, pageableResponse);
		
		 if (orderPage.isEmpty()) {
		        throw new OrderNotFoundException();
		    }
		orderRepository.save(orderEntity);

		 
		return orderEntityMapper.toOrderList(orderPage.getContent());
	}

	@Override
	public Optional<OrderEntity> findById(Long id,Long idRestaurant) {
		return orderRepository.findByIdAndRestaurant(id,idRestaurant);
	}

	@Override
	public Optional<OrderEntity> findByIdOrder(Long idOrder) {
		return orderRepository.findById(idOrder);
	}

	@Override
	public Optional<OrderEntity> findByIdOrderEmployeeId(Long id, Long restaurantNit, Long fkEmployeeId) {
		return orderRepository.findByIdAndRestaurantAndFkEmployeeId(id,restaurantNit,fkEmployeeId);
	}

	@Override
	public Optional<OrderEntity> findByIdAndClientId(Long id, Long clientId) {
		return orderRepository.findByIdAndClientId(id, clientId);
	}

	@Override
	public OrderEntity saveOrderPlate(Orders orden, List<OrderPlateRequest> platos) {

		Optional<RestaurantEntity> restaurantEntity = restaurantRepository.findById(orden.getNit());

		OrderEntity orderEntity=orderEntityMapper.toEntity(orden);

		List<OrderPlateEntity> plates = platos.stream().map(dto -> {
			OrderPlateEntity plateEntry = new OrderPlateEntity();
			PlateEntity plateRef = new PlateEntity();
			plateRef.setId(dto.getPlateId());
			plateEntry.setPlate(plateRef);
			plateEntry.setQuantity(dto.getQuantity());
			plateEntry.setOrder(orderEntity);
			return plateEntry;
		}).toList();

		orderEntity.setOrderPlates(plates);
		orderEntity.setRestaurant(restaurantEntity.get().getNit());
		return orderRepository.save(orderEntity);
	}

}
