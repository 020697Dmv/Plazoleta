package com.plazoleta.infrastructure.out.jpa.adapter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.OrderRequestDto;
import com.plazoleta.application.dto.request.OrderStatusRequestDto;
import com.plazoleta.domain.model.Orders;
import com.plazoleta.domain.spi.IOrderPersistencePort;
import com.plazoleta.infrastructure.exception.PlateNotFoundException;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import com.plazoleta.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.plazoleta.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.plazoleta.infrastructure.out.jpa.repository.IOrderPlateRepository;
import com.plazoleta.infrastructure.out.jpa.repository.IOrderRepository;
import com.plazoleta.infrastructure.out.jpa.repository.IUserRepository;

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
	public List<Orders> toResponseList(OrderStatusRequestDto orderStatusRequestDto) {
		
		Pageable pageableResponse= PageRequest.of(orderStatusRequestDto.getPageRequestDto().getPage(),orderStatusRequestDto.getPageRequestDto().getSize());
		
		Page<OrderEntity> orderPage;
		
		orderPage=orderRepository.findAllByStatus(orderStatusRequestDto.getStatus(), pageableResponse);
		
		if (orderPage.isEmpty()) {
			//Ajustar
	        throw new PlateNotFoundException();
	    }
		return orderPage.map(orderEntityMapper::toOrder).getContent();
	}

}
