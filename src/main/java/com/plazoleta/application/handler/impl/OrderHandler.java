package com.plazoleta.application.handler.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.AssignOrderRequestDto;
import com.plazoleta.application.dto.request.OrderRequestDto;
import com.plazoleta.application.dto.request.OrderStatusRequestDto;
import com.plazoleta.application.handler.IOrderHandler;
import com.plazoleta.application.mapper.IOrderRequestMapper;
import com.plazoleta.domain.api.IOrderServicePort;
import com.plazoleta.domain.model.AssignOrderRequest;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.OrderListModel;
import com.plazoleta.domain.model.OrderRequest;
import com.plazoleta.domain.model.OrderStatusRequest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderHandler implements IOrderHandler{
	
	public final IOrderServicePort orderServicePort;
	
	public final IOrderRequestMapper orderRequestMapper;

	
	@Override
	public MessageResponse saveOrder(OrderRequestDto orderRequestDto) {
		OrderRequest orderRequest=orderRequestMapper.toOrderRequest(orderRequestDto);
		return orderServicePort.saveOrder(orderRequest);
	}


	@Override
	public List<OrderListModel> orders(OrderStatusRequestDto orderStatusRequestDto) {
		OrderStatusRequest orderStatusRequest = orderRequestMapper.toOrderStatusRequest(orderStatusRequestDto);
		return orderServicePort.orders(orderStatusRequest);
	}



	@Override
	public List<OrderListModel> ordersAsignStatus(AssignOrderRequestDto assignOrderRequestDto) {
		AssignOrderRequest assignOrderRequest=orderRequestMapper.toAssignOrderRequest(assignOrderRequestDto);
		return orderServicePort.ordersAsignStatus(assignOrderRequest);
	}



	@Override
	public void sendSmsNotify(Long idOrder) {
		orderServicePort.sendSmdNotify(idOrder);
	}


	@Override
	public MessageResponse updateStatusOrder(String secutiryCode,Long idOrder) {
		return orderServicePort.updateStatusOrder(secutiryCode, idOrder);
	}


	@Override
	public MessageResponse cancelStatusOrder(Long idOrder) {
		return orderServicePort.cancelStatusOrder(idOrder);
	}
	

}
