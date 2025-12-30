package com.plazoleta.domain.usecase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.OrderRequestDto;
import com.plazoleta.domain.api.IOrderServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Orders;
import com.plazoleta.domain.model.Plate;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.spi.IOrderPersistencePort;
import com.plazoleta.domain.spi.IPlatePersistencePort;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.infrastructure.exception.PlateNotFoundException;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import com.plazoleta.infrastructure.out.jpa.entity.OrderPlateEntity;
import com.plazoleta.infrastructure.out.jpa.entity.PlateEntity;
import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import com.plazoleta.infrastructure.out.jpa.mapper.IPlateEntityMapper;
import com.plazoleta.infrastructure.out.jpa.repository.IOrderPlateRepository;
import com.plazoleta.infrastructure.out.jpa.repository.IOrderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderUseCase  implements IOrderServicePort{
	
	private final IOrderPersistencePort orderPersistencePort;

	private final IPlatePersistencePort platePersistencePort;

	private final IPlateEntityMapper plateEntityMapper;
	
	private final IRestaurantPersistencePort restaurantPersistencePort;

	
	@Override
	public MessageResponse saveOrder(OrderRequestDto orderRequestDto) {
	    Long clientId = orderRequestDto.getIdClient();

	    // 1. Validar pedidos en proceso
	    List<String> inProcessStatuses = List.of("PENDIENTE", "EN_PREPARACION", "LISTO");
	    if (orderPersistencePort.existsByClientIdAndStatusIn(clientId, inProcessStatuses)) {
	        return new MessageResponse("Ya tienes un pedido en curso.");
	    }

	    // 2. Obtener el restaurante directamente
	    RestaurantEntity restaurant = restaurantPersistencePort.findByIdEntity(orderRequestDto.getRestaurantId())
	            .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));

	    // 3. Crear encabezado de la orden
	    OrderEntity orderEntity = new OrderEntity();
	    orderEntity.setClientId(clientId);
	    orderEntity.setDate(LocalDateTime.now());
	    orderEntity.setStatus("PENDIENTE"); //
	    orderEntity.setRestaurant(restaurant); // Asignación de FK restaurant_owner

	    // 4. Mapear platos y cantidades
	    List<OrderPlateEntity> plates = orderRequestDto.getPlates().stream().map(dto -> {
	        // Buscar el plato real
	        PlateEntity plate = platePersistencePort.findById(dto.getPlateId())
	                .orElseThrow(() -> new RuntimeException("Plato no encontrado: " + dto.getPlateId()));

	        // VALIDACIÓN HU: ¿El plato pertenece al restaurante del pedido?
	        if (!plate.getRestaurant().getNit().equals(restaurant.getNit())) {
	            throw new RuntimeException("El plato " + plate.getNamePlate() + " no pertenece a este restaurante.");
	        }

	        OrderPlateEntity plateEntry = new OrderPlateEntity();
	        plateEntry.setPlate(plate); // ¡FUNDAMENTAL! Asigna la FK plate_id
	        plateEntry.setQuantity(dto.getQuantity()); //
	        plateEntry.setOrder(orderEntity); // Vinculación para FK order_id
	        return plateEntry;
	    }).toList();

	    orderEntity.setOrderPlates(plates);

	    // 5. Guardar (gracias al CascadeType.ALL se guardan órdenes y platos)
	    orderPersistencePort.saveOrder(orderEntity);

	    return new MessageResponse(String.format("Order created with id Client %d", clientId));
	}
	

}
