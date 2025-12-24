package com.plazoleta.infrastructure.out.jpa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.plazoleta.application.dto.response.RestaurantPageResponseDto;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEntityMapper {

	@Mapping(target = "propietario", ignore = true)
	RestaurantEntity  toEntity(Restaurant restaurant);
	
    @Mapping(source = "propietario.id", target = "identity_document_owner")
	Restaurant toRestaurant(RestaurantEntity  restaurantEntity);
    
    @Mapping(target = "name", source = "name")
    @Mapping(target = "urlLogo", source = "urlLogo")
    RestaurantPageResponseDto toResponse(Restaurant restaurant);

    List<RestaurantPageResponseDto> toResponseList(List<Restaurant> restaurants);
}
