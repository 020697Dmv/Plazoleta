package com.plazoleta.domain.validacion;

import com.plazoleta.domain.model.Restaurant;
import static com.plazoleta.domain.validacion.ValidationUtils.requiredValidarNit;
import static com.plazoleta.domain.validacion.ValidationUtils.requiredValidarNombre;

public class RestaurantValidation {
	
	private RestaurantValidation() {
		
	}
	
	public static void validateRestaurant( final Restaurant restaurant) {
		requiredValidarNit(restaurant.getNit(), "Nit");
		requiredValidarNombre(restaurant.getName(),"Name");
	}

}
