package com.plazoleta.domain.spi;

import java.util.List;

import com.plazoleta.application.dto.request.OrderRequestDto;
import com.plazoleta.application.dto.request.OrderStatusRequestDto;
import com.plazoleta.domain.model.Orders;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;


public interface IOrderPersistencePort {
	
	OrderEntity saveOrder(OrderEntity orderEntity);
	
	boolean existsByClientIdAndStatusIn(Long clientId, List<String> statuses);
	
	List<Orders> toResponseList(OrderStatusRequestDto orderStatusRequestDto);
}
