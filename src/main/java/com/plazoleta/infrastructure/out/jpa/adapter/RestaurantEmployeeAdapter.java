package com.plazoleta.infrastructure.out.jpa.adapter;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.model.RestaurantEmployee;
import com.plazoleta.domain.model.User;
import com.plazoleta.domain.spi.IRestaurantEmployeePersistencePort;
import com.plazoleta.infrastructure.exception.RestaurantEmployeeNotFoundException;
import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEmployeeEntity;
import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import com.plazoleta.infrastructure.out.jpa.entity.UserEntity;
import com.plazoleta.infrastructure.out.jpa.mapper.IRestaurantEmployeeMapper;
import com.plazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.plazoleta.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.plazoleta.infrastructure.out.jpa.repository.IRestaurantEmployeeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RestaurantEmployeeAdapter implements IRestaurantEmployeePersistencePort {
	
	private final IRestaurantEmployeeRepository restaurantEmployeeRepository;
	
	private final IUserEntityMapper userEntityMapper;


	private final IRestaurantEntityMapper  restaurantEntityMapper;
	
	private final IRestaurantEmployeeMapper  restaurantEmployeeMapper;


	
	@Override
	public RestaurantEmployee saveRestaurantEmployee( Restaurant restaurant,User ownerRestaurantSave, User userCreate) {
		
		 
        UserEntity userEntityOwner = userEntityMapper.toEntity(ownerRestaurantSave);     
	    RestaurantEntity restaurantEntity = restaurantEntityMapper.toEntity(restaurant);   
	    restaurantEntity.setPropietario(userEntityOwner); 		
		UserEntity userEntity = userEntityMapper.toEntity(userCreate);
	    RestaurantEmployeeEntity restaurantEmployeeEntity= new RestaurantEmployeeEntity();
	    restaurantEmployeeEntity.setEmployee(userEntity);
	    restaurantEmployeeEntity.setRestaurant(restaurantEntity);
	    RestaurantEmployeeEntity saveRestaurantEmployeeEntity=restaurantEmployeeRepository.save(restaurantEmployeeEntity);
		return restaurantEmployeeMapper.toDomain(saveRestaurantEmployeeEntity);
	}



	@Override
	public Optional<RestaurantEmployee> findBy(Long id) {
		return restaurantEmployeeRepository.findById(id).map(restaurantEmployeeMapper::toDomain);
	}



	@Override
	public RestaurantEmployee findByIdEmployee(Long id) {
		Optional<RestaurantEmployeeEntity> restaurantEmployeeEntity=restaurantEmployeeRepository.findByEmployeeId(id);
		if(restaurantEmployeeEntity.isEmpty()) {
			throw new RestaurantEmployeeNotFoundException();
		}
		return restaurantEmployeeMapper.toDomain(restaurantEmployeeEntity.get());
	}
	
	
	
	
	

}
