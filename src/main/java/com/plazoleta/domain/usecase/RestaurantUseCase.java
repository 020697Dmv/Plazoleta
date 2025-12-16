package com.plazoleta.domain.usecase;

import org.springframework.stereotype.Service;

import com.plazoleta.domain.api.IRestaurantServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.model.User;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.domain.validacion.RestaurantValidation;
import com.plazoleta.infrastructure.excepcion.UserNotFoundException;
import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RestaurantUseCase  implements IRestaurantServicePort{

	private final IRestaurantPersistencePort restaurantPersistencePort;
	
	private final IUserPersistencePort userPersistencePort;

	
	@Override
	public MessageResponse guardarRestaurant(Restaurant restaurant) {

	    User owner = userPersistencePort
	            .findById(restaurant.getIdentity_document_owner())
	            .orElseThrow(UserNotFoundException::new);	    
	    RestaurantValidation.validateRestaurant(restaurant);	    
	    Restaurant restaurantSave =restaurantPersistencePort.saveRestaurant(restaurant,owner);
	    return new MessageResponse(
	            String.format("Restaurant created with id %d", restaurantSave.getNit())
	    );
	}


}
