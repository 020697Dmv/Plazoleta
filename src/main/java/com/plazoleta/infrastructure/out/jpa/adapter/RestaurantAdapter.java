package com.plazoleta.infrastructure.out.jpa.adapter;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.model.User;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.infrastructure.exception.RestaurantAlreadyExistException;
import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import com.plazoleta.infrastructure.out.jpa.entity.UserEntity;
import com.plazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.plazoleta.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.plazoleta.infrastructure.out.jpa.repository.IRestaurantRepository;
import com.plazoleta.infrastructure.out.jpa.repository.IUserRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class RestaurantAdapter  implements IRestaurantPersistencePort{
	
	private final IRestaurantRepository  restaurantRepository;
	
	private final IUserEntityMapper userEntityMapper;

	private final IRestaurantEntityMapper  restaurantEntityMapper;

	@Override
	public Restaurant saveRestaurant(Restaurant restaurant, User owner) {	    
		UserEntity userEntity = userEntityMapper.toEntity(owner);     
	    RestaurantEntity restaurantEntity = restaurantEntityMapper.toEntity(restaurant);     	    
	    restaurantEntity.setPropietario(userEntity); 	    
	    RestaurantEntity savedEntity = restaurantRepository.save(restaurantEntity); 	    
	    return restaurantEntityMapper.toRestaurant(savedEntity);
	}

	@Override
	public Optional<Restaurant> findById(Long id) {
		return restaurantRepository.findById(id)
				.map(restaurantEntityMapper::toRestaurant);
	}
	
	
	

}
