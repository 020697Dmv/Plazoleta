package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.model.RestaurantEmployee;
import com.plazoleta.domain.model.User;

import java.util.Optional;

public interface IRestaurantEmployeePersistencePort {
	
	RestaurantEmployee saveRestaurantEmployee(
			Restaurant restaurant,User userOwner, User userCreate);
	
	Optional<RestaurantEmployee> findBy(Long id);
	
	RestaurantEmployee findByIdEmployee(Long id);


}
