package com.plazoleta.domain.api;

import java.util.List;

import com.plazoleta.application.dto.request.PageRequestDto;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Restaurant;

public interface IRestaurantServicePort {

	MessageResponse saveRestaurant(Restaurant restaurant);
	List<Restaurant> getAllRestaurants(PageRequestDto pageRequestDto);
}
