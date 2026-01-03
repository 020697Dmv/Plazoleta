package com.plazoleta.application.handler;

import java.util.List;

import com.plazoleta.application.dto.request.OrderPlateRequestDto;
import com.plazoleta.application.dto.request.OrderRequestDto;
import com.plazoleta.application.dto.request.OrderStatusRequestDto;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.OrderListModel;
import com.plazoleta.domain.model.Orders;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;

public interface IOrderHandler {
	MessageResponse saveOrder(OrderRequestDto orderRequestDto);
	
	List<OrderListModel> orders(OrderStatusRequestDto orderStatusRequestDto);
}
