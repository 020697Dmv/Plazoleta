package com.plazoleta.domain.spi;

import java.util.Optional;

import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.model.RestaurantEmployee;
import com.plazoleta.domain.model.User;
import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;

public interface IRestaurantEmployeePersistencePort {
	
	RestaurantEmployee saveRestaurantEmployee(
			RestaurantEntity restaurant,User user);
	
	Optional<RestaurantEmployee> findBy(Long id);
	
	Optional<RestaurantEmployee> findByIdEmployee(Long id);


}
