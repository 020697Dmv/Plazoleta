package com.plazoleta.application.handler.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.OrderRequestDto;
import com.plazoleta.application.dto.request.OrderStatusRequestDto;
import com.plazoleta.application.handler.IOrderHandler;
import com.plazoleta.application.mapper.IUserRequestMapper;
import com.plazoleta.domain.api.IOrderServicePort;
import com.plazoleta.domain.api.IUserServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.OrderListModel;
import com.plazoleta.domain.model.Orders;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderHandler implements IOrderHandler{
	
	public final IOrderServicePort orderServicePort;
	
	
	
	@Override
	public MessageResponse saveOrder(OrderRequestDto orderRequestDto) {

		MessageResponse messageResponse=orderServicePort.saveOrder(orderRequestDto);
		return messageResponse;
	}



	@Override
	public List<OrderListModel> orders(OrderStatusRequestDto orderStatusRequestDto) {
		
		return orderServicePort.orders(orderStatusRequestDto);
	}
	
	

}
