package com.plazoleta.application.handler;

import java.util.List;

import com.plazoleta.application.dto.request.PageRequestDto;
import com.plazoleta.application.dto.request.RestaurantRequestDto;
import com.plazoleta.application.dto.response.RestaurantPageResponseDto;
import com.plazoleta.application.dto.response.StringResponseDto;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Restaurant;

public interface IRestaurantHandler {
	
	MessageResponse saveRestaurant(RestaurantRequestDto restaurantRequestDto);
	List<RestaurantPageResponseDto> getAllRestaurants(PageRequestDto pageRequestDto);
}
