package com.plazoleta.domain.api;

import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.PageRequest;
import com.plazoleta.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantServicePort {

	MessageResponse saveRestaurant(Restaurant restaurant);
	List<Restaurant> getAllRestaurants(PageRequest toPageRequest);
}
