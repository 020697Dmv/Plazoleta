package com.plazoleta.domain.api;

import com.plazoleta.application.dto.request.OrderRequestDto;
import com.plazoleta.domain.model.MessageResponse;


public interface IOrderServicePort {
	
	MessageResponse saveOrder(OrderRequestDto orderRequestDto);

}
