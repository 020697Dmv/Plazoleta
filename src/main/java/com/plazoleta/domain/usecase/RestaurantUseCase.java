package com.plazoleta.domain.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.plazoleta.domain.api.IRestaurantServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.PageRequest;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.model.User;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.domain.validacion.RestaurantValidation;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RestaurantUseCase  implements IRestaurantServicePort{

	private final IRestaurantPersistencePort restaurantPersistencePort;
	
	private final IUserPersistencePort userPersistencePort;

	
	@Override
	public MessageResponse saveRestaurant(Restaurant restaurant) {

	    User owner = userPersistencePort.findById(restaurant.getIdentity_document_owner());	       
	    
	    restaurantPersistencePort.findById(restaurant.getNit());
	    RestaurantValidation.validateRestaurant(restaurant);	    
	    Restaurant restaurantSave = restaurantPersistencePort.saveRestaurant(restaurant, owner);
	    return new MessageResponse(
	            String.format("Restaurant created with id %d", restaurantSave.getNit())
	    );
	}


	@Override
	public List<Restaurant> getAllRestaurants(PageRequest pageRequest) {
		List<Restaurant> restaurants=restaurantPersistencePort
				.getAllRestaurants(pageRequest.getPage(), pageRequest.getSize());
		
		return restaurants;
	}


}
