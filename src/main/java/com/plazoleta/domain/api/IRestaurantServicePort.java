package com.plazoleta.domain.api;

import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Restaurant;

public interface IRestaurantServicePort {

	MessageResponse saveRestaurant(Restaurant restaurant);

}
