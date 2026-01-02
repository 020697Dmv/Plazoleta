package com.plazoleta.infrastructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.plazoleta.domain.model.Orders;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderEntityMapper {
	
	@Mapping(target = "restaurant", ignore = true)
	OrderEntity toEntity(Orders order);

    @Mapping(source = "restaurant.nit", target = "nit")
    Orders toOrder(OrderEntity orderEntity);
    
}
