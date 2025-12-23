package com.plazoleta.domain.config;

import com.plazoleta.domain.api.IPlateServicePort;
import com.plazoleta.domain.spi.IPlatePersistencePort;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.usecase.PlateUseCase;

public class PlateUseCaseConfiguration {
	
	
	public IPlateServicePort  plateServicePort( IPlatePersistencePort platePersistencePort,IRestaurantPersistencePort restaurantPersistencePort) {
		return new PlateUseCase(platePersistencePort, restaurantPersistencePort);
	}

}
