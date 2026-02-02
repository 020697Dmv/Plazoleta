package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.AssignOrderRequest;
import com.plazoleta.domain.model.OrderListModel;
import com.plazoleta.domain.model.OrderStatusRequest;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;

import java.util.List;
import java.util.Optional;


public interface IOrderPersistencePort {
	
	OrderEntity saveOrder(OrderEntity orderEntity);
	
	boolean existsByClientIdAndStatusIn(Long clientId, List<String> statuses);
	
	List<OrderListModel> toResponseList(OrderStatusRequest orderStatusRequestDto, Long id);
	
	List<OrderListModel> asignnedStatusAsign(AssignOrderRequest assignOrderRequest, Long id,OrderEntity orderEntity);

	Optional<OrderEntity> findById(Long idOrder,Long idRestaurant);
	
	Optional<OrderEntity> findByIdOrder(Long idOrder);
	
	Optional<OrderEntity> findByIdOrderEmployeeId(Long id, Long restaurantNit, Long fkEmployeeId);

	Optional<OrderEntity> findByIdAndClientId(Long id, Long clientId);
}
