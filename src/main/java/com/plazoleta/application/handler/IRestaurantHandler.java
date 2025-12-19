package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.RestaurantRequestDto;
import com.plazoleta.application.dto.response.StringResponseDto;
import com.plazoleta.domain.model.MessageResponse;

public interface IRestaurantHandler {
	
	MessageResponse saveRestaurant(RestaurantRequestDto restaurantRequestDto);

}
