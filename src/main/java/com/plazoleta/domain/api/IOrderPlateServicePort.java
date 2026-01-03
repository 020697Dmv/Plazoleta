package com.plazoleta.domain.api;

import java.util.List;

import com.plazoleta.application.dto.request.OrderStatusRequestDto;
import com.plazoleta.infrastructure.out.jpa.entity.OrderPlateEntity;

public interface IOrderPlateServicePort {
	
	List<OrderPlateEntity> orders (OrderStatusRequestDto orderStatusRequestDto);

}
