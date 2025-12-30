package com.plazoleta.application.handler.impl;

import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.OrderRequestDto;
import com.plazoleta.application.handler.IOrderHandler;
import com.plazoleta.application.mapper.IUserRequestMapper;
import com.plazoleta.domain.api.IOrderServicePort;
import com.plazoleta.domain.api.IUserServicePort;
import com.plazoleta.domain.model.MessageResponse;

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
	
	

}
