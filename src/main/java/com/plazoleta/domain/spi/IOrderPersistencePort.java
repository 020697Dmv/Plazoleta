package com.plazoleta.domain.spi;

import java.util.List;
import java.util.Optional;

import com.plazoleta.application.dto.request.AssignOrderRequestDto;
import com.plazoleta.application.dto.request.OrderRequestDto;
import com.plazoleta.application.dto.request.OrderStatusRequestDto;
import com.plazoleta.domain.model.OrderListModel;
import com.plazoleta.domain.model.Orders;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import com.plazoleta.infrastructure.out.jpa.entity.PlateEntity;


public interface IOrderPersistencePort {
	
	OrderEntity saveOrder(OrderEntity orderEntity);
	
	boolean existsByClientIdAndStatusIn(Long clientId, List<String> statuses);
	
	List<OrderListModel> toResponseList(OrderStatusRequestDto orderStatusRequestDto, Long id);
	
	List<OrderListModel> asignnedStatusAsign(AssignOrderRequestDto assignOrderRequestDto, Long id,OrderEntity orderEntity);

	Optional<OrderEntity> findById(Long idOrder,Long idRestaurant);
	
	Optional<OrderEntity> findByIdOrder(Long idOrder);
	
	Optional<OrderEntity> findByIdOrderEmployeeId(Long id, Long restaurantNit, Long fkEmployeeId);

	Optional<OrderEntity> findByIdAndClientId(Long id, Long clientId);
}
