package com.plazoleta.domain.spi;

import java.util.List;
import java.util.Optional;

import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.model.User;

public interface IRestaurantPersistencePort {

	Restaurant saveRestaurant(Restaurant restaurant,User owner);
	
	Optional<Restaurant>  findById(Long id);

	List<Restaurant> getAllRestaurants (int page, int size);

}
