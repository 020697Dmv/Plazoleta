package com.plazoleta.infrastructure.out.jpa.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.model.User;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.infrastructure.exception.RestaurantNotFoundException;
import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import com.plazoleta.infrastructure.out.jpa.entity.UserEntity;
import com.plazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.plazoleta.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.plazoleta.infrastructure.out.jpa.repository.IRestaurantRepository;


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
	public Restaurant findById(Long id) {
		Optional<RestaurantEntity>restaurant=restaurantRepository.findById(id);
		
		if(restaurant.isEmpty()) {
			throw new RestaurantNotFoundException();
		}
		return restaurantEntityMapper.toRestaurant(restaurant.get());
	}

	@Override
	public List<Restaurant> getAllRestaurants(int page, int size) {
	    Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
	    
	    Page<RestaurantEntity> restaurantPage = restaurantRepository.findAll(pageable);
	    
	    if (restaurantPage.isEmpty()) {
	        throw new RestaurantNotFoundException();
	    }
	    
	    return restaurantPage
	            .map(restaurantEntityMapper::toRestaurant)
	            .getContent();
	}

	@Override
	public Optional<RestaurantEntity> findByIdEntity(Long id) {
		return restaurantRepository.findById(id);
	}

	
	
	
	

}
