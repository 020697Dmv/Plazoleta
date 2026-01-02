package com.plazoleta.infrastructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.plazoleta.domain.model.RestaurantEmployee;
import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEmployeeEntity;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEmployeeMapper {
	
	@Mapping(target = "restaurant.nit", source = "idRestaurant")
    @Mapping(target = "employee.id", source = "idEmployee")
    RestaurantEmployeeEntity toEntity(RestaurantEmployee restaurantEmployee);

    @Mapping(target = "idRestaurant", source = "restaurant.nit")
    @Mapping(target = "idEmployee", source = "employee.id")
    RestaurantEmployee toDomain(RestaurantEmployeeEntity restaurantEmployeeEntity);

}
