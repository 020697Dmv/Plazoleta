package com.plazoleta.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.plazoleta.application.dto.response.RestaurantPageResponseDto;
import com.plazoleta.application.dto.response.RestaurantResponseDto;
import com.plazoleta.domain.model.Restaurant;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantResponseMapper {
		
	RestaurantResponseDto toRestaurant(Restaurant restaurant);
	
    @Mapping(target = "name", source = "name")
    @Mapping(target = "urlLogo", source = "urlLogo")
    RestaurantPageResponseDto toResponse(Restaurant restaurant);

    List<RestaurantPageResponseDto> toResponseList(List<Restaurant> restaurants);
		
}
