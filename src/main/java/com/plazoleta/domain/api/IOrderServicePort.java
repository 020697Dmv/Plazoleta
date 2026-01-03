package com.plazoleta.domain.api;

import java.util.List;

import com.plazoleta.application.dto.request.AssignOrderRequestDto;
import com.plazoleta.application.dto.request.OrderRequestDto;
import com.plazoleta.application.dto.request.OrderStatusRequestDto;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.OrderListModel;
import com.plazoleta.domain.model.Orders;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import com.plazoleta.infrastructure.out.jpa.entity.OrderPlateEntity;


public interface IOrderServicePort {
	
	MessageResponse saveOrder(OrderRequestDto orderRequestDto);
	List<OrderListModel> orders(OrderStatusRequestDto orderStatusRequestDto);
	List<OrderListModel> ordersAsignStatus(AssignOrderRequestDto assignOrderRequestDto);

}

