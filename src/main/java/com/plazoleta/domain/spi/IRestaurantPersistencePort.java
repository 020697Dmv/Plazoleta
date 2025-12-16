package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.model.User;

public interface IRestaurantPersistencePort {

	Restaurant saveRestaurant(Restaurant restaurant,User owner);
}
