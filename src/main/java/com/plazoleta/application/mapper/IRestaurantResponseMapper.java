package com.plazoleta.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.plazoleta.application.dto.response.RestaurantResponseDto;
import com.plazoleta.domain.model.Restaurant;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantResponseMapper {
		
	RestaurantResponseDto toRestaurant(Restaurant restaurant);
		
}
