package com.plazoleta.application.handler.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.PageRequestDto;
import com.plazoleta.application.dto.request.RestaurantRequestDto;
import com.plazoleta.application.dto.response.RestaurantPageResponseDto;
import com.plazoleta.application.dto.response.StringResponseDto;
import com.plazoleta.application.handler.IRestaurantHandler;
import com.plazoleta.application.mapper.IMensaggeResponseMapper;
import com.plazoleta.application.mapper.IRestaurantRequestMapper;
import com.plazoleta.application.mapper.IRestaurantResponseMapper;
import com.plazoleta.application.mapper.IUserRequestMapper;
import com.plazoleta.application.mapper.IUserResponseMapper;
import com.plazoleta.domain.api.IRestaurantServicePort;
import com.plazoleta.domain.api.IUserServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Restaurant;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler{
	
	private final IRestaurantServicePort restaurantServicePort;
	private final IRestaurantRequestMapper restaurantRequestMapper;
	private final IRestaurantResponseMapper  restaurantResponseMapper;
	private final IMensaggeResponseMapper StringMessageResponse;
	
	
	@Override
	public MessageResponse saveRestaurant(RestaurantRequestDto restaurantRequestDto) {
		Restaurant restaurant=restaurantRequestMapper.toRestaurant(restaurantRequestDto);
		MessageResponse  messageResponse=restaurantServicePort.saveRestaurant(restaurant);
		return messageResponse;
	}


	@Override
	public List<RestaurantPageResponseDto> getAllRestaurants(PageRequestDto pageRequestDto) {
		List<Restaurant> restaurants=restaurantServicePort.getAllRestaurants(pageRequestDto);
		
		return restaurantResponseMapper.toResponseList(restaurants);
		}
	
	
	
}
