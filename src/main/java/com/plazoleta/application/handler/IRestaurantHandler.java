package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.PageRequestDto;
import com.plazoleta.application.dto.request.RestaurantRequestDto;
import com.plazoleta.application.dto.response.RestaurantPageResponseDto;
import com.plazoleta.domain.model.MessageResponse;

import java.util.List;

public interface IRestaurantHandler {
	
	MessageResponse saveRestaurant(RestaurantRequestDto restaurantRequestDto);
	List<RestaurantPageResponseDto> getAllRestaurants(PageRequestDto pageRequestDto);
}
