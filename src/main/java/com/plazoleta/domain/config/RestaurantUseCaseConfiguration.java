package com.plazoleta.domain.config;

import com.plazoleta.domain.api.IPlateServicePort;
import com.plazoleta.domain.api.IRestaurantServicePort;
import com.plazoleta.domain.spi.IPlatePersistencePort;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.domain.usecase.PlateUseCase;
import com.plazoleta.domain.usecase.RestaurantUseCase;
import com.plazoleta.infrastructure.out.jpa.adapter.PlateAdapter;
import com.plazoleta.infrastructure.out.jpa.adapter.RestaurantAdapter;
import com.plazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.plazoleta.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.plazoleta.infrastructure.out.jpa.repository.IRestaurantRepository;

public class RestaurantUseCaseConfiguration {
	
	public IRestaurantServicePort  restaurantServicePort(IRestaurantPersistencePort restaurantPersistencePort,IUserPersistencePort userPersistencePort) {
		
		return  new RestaurantUseCase(restaurantPersistencePort,userPersistencePort);
	} 
	
	public IRestaurantPersistencePort restaurantPersistencePort	(IRestaurantRepository restaurantRepository,IRestaurantEntityMapper restaurantEntityMapper
			,IUserEntityMapper userEntityMapper) {
		
		return new RestaurantAdapter(restaurantRepository, userEntityMapper,restaurantEntityMapper);
	}
	
	
}
