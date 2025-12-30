package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.OrderRequestDto;
import com.plazoleta.domain.model.MessageResponse;

public interface IOrderHandler {
	MessageResponse saveOrder(OrderRequestDto orderRequestDto);
}
