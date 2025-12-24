package com.plazoleta.domain.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.PageRequestDto;
import com.plazoleta.domain.api.IRestaurantServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.model.User;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.domain.validacion.RestaurantValidation;
import com.plazoleta.infrastructure.exception.RestaurantAlreadyExistException;
import com.plazoleta.infrastructure.exception.RestaurantsNotExistsException;
import com.plazoleta.infrastructure.exception.UserNotFoundException;
import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RestaurantUseCase  implements IRestaurantServicePort{

	private final IRestaurantPersistencePort restaurantPersistencePort;
	
	private final IUserPersistencePort userPersistencePort;

	
	@Override
	public MessageResponse saveRestaurant(Restaurant restaurant) {

	    User owner = userPersistencePort
	            .findById(restaurant.getIdentity_document_owner())
	            .orElseThrow(UserNotFoundException::new);	       
	    
	    restaurantPersistencePort.findById(restaurant.getNit())
	            .ifPresent(r -> { throw new RestaurantAlreadyExistException(); });
	    RestaurantValidation.validateRestaurant(restaurant);	    
	    Restaurant restaurantSave = restaurantPersistencePort.saveRestaurant(restaurant, owner);
	    return new MessageResponse(
	            String.format("Restaurant created with id %d", restaurantSave.getNit())
	    );
	}


	@Override
	public List<Restaurant> getAllRestaurants(PageRequestDto pageRequestDto) {
		List<Restaurant> restaurants=restaurantPersistencePort
				.getAllRestaurants(pageRequestDto.getPage(), pageRequestDto.getSize());
		
		if (restaurants == null || restaurants.isEmpty()) {
	        throw new RestaurantsNotExistsException();
	    }
		return restaurants;
	}


}
