package com.plazoleta.infrastructure.out.jpa.adapter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.OrderRequestDto;
import com.plazoleta.domain.model.Orders;
import com.plazoleta.domain.spi.IOrderPersistencePort;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
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
	
	@Override
	public OrderEntity saveOrder(OrderEntity orderEntity) {

		return orderRepository.save(orderEntity);
	}

	@Override
	public boolean existsByClientIdAndStatusIn(Long clientId, List<String> statuses) {
		return orderRepository.existsByClientIdAndStatusIn(clientId,statuses);
	}

}
