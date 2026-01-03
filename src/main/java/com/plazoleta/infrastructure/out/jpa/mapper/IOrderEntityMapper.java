package com.plazoleta.infrastructure.out.jpa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.Mappings;

import com.plazoleta.domain.model.OrderListModel;
import com.plazoleta.domain.model.OrderPlateDetails;
import com.plazoleta.domain.model.Orders;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import com.plazoleta.infrastructure.out.jpa.entity.OrderPlateEntity;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderEntityMapper {
	
	@Mapping(target = "restaurant", ignore = true)
	OrderEntity toEntity(Orders order);

    @Mapping(source = "restaurant.nit", target = "nit")
    Orders toOrder(OrderEntity orderEntity);
    
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "clientId", target = "clientId"),
        @Mapping(source = "date", target = "date"),
        @Mapping(source = "status", target = "status"),
        @Mapping(source = "restaurant.name", target = "restaurantName"),
        @Mapping(source = "orderPlates", target = "details")
    })
    OrderListModel toListModel(OrderEntity orderEntity);

    // Mapeo para los items de la lista de platos
    @Mappings({
        @Mapping(source = "plate.namePlate", target = "plateName"),
        @Mapping(source = "quantity", target = "quantity")
    })
    OrderPlateDetails toDetails(OrderPlateEntity orderPlateEntity);

    List<OrderListModel> toOrderList(List<OrderEntity> orderEntityList);
    
}
