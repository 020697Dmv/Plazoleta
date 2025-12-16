package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.RestaurantRequestDto;
import com.plazoleta.application.dto.response.StringResponseDto;

public interface IRestaurantHandler {
	
	StringResponseDto saveRestaurant(RestaurantRequestDto restaurantRequestDto);

}
