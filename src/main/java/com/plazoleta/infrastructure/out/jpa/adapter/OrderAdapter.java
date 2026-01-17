package com.plazoleta.infrastructure.out.jpa.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.AssignOrderRequestDto;
import com.plazoleta.application.dto.request.OrderStatusRequestDto;
import com.plazoleta.domain.model.AssignOrderRequest;
import com.plazoleta.domain.model.OrderListModel;
import com.plazoleta.domain.model.OrderStatusRequest;
import com.plazoleta.domain.spi.IOrderPersistencePort;
import com.plazoleta.infrastructure.exception.OrderNotFoundException;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import com.plazoleta.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.plazoleta.infrastructure.out.jpa.repository.IOrderPlateRepository;
import com.plazoleta.infrastructure.out.jpa.repository.IOrderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderAdapter implements IOrderPersistencePort{
	
	private  final IOrderPlateRepository orderPlateRepository;
	private  final IOrderRepository orderRepository;
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
	    
	    Page<OrderEntity> orderPage = orderRepository.findAllByRestaurantNitAndStatus(
	        idRestaurant, 
	        orderStatusRequestDto.getStatus(), 
	        pageableResponse
	    );
	    
	    if (orderPage.isEmpty()) {
	        throw new OrderNotFoundException();
	    }
	    
	    List<OrderListModel> roLista = orderEntityMapper.toOrderList(orderPage.getContent());
	    
	    return roLista; 
	}

	@Override
	public List<OrderListModel> asignnedStatusAsign(AssignOrderRequest assignOrderRequest, Long idRestaurant,OrderEntity orderEntity) {
		
		Pageable pageableResponse = PageRequest.of(
				assignOrderRequest.getPageRequest().getPage(),
				assignOrderRequest.getPageRequest().getSize()
		    );
		 Page<OrderEntity> orderPage = orderRepository.findAllByRestaurantNit(
			        idRestaurant, pageableResponse);
		
		 if (orderPage.isEmpty()) {
		        throw new OrderNotFoundException();
		    }
		orderRepository.save(orderEntity);

		 
		List<OrderListModel> roLista = orderEntityMapper.toOrderList(orderPage.getContent());

		return roLista;
	}

	@Override
	public Optional<OrderEntity> findById(Long id,Long idRestaurant) {
		return orderRepository.findByIdAndRestaurantNit(id,idRestaurant);
	}

	@Override
	public Optional<OrderEntity> findByIdOrder(Long idOrder) {
		return orderRepository.findById(idOrder);
	}

	@Override
	public Optional<OrderEntity> findByIdOrderEmployeeId(Long id, Long restaurantNit, Long fkEmployeeId) {
		return orderRepository.findByIdAndRestaurantNitAndFkEmployeeId(id,restaurantNit,fkEmployeeId);
	}

	@Override
	public Optional<OrderEntity> findByIdAndClientId(Long id, Long clientId) {
		return orderRepository.findByIdAndClientId(id, clientId);
	}

}
