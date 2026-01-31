package com.plazoleta.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.PageRequest;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.model.User;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;

@ExtendWith(MockitoExtension.class)
public class RestaurantUseCaseTest {

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private RestaurantUseCase restaurantUseCase;

    @Test
    void saveRestaurant_shouldReturnCreatedMessage() {
        Restaurant restaurant = new Restaurant();
        restaurant.setIdentity_document_owner(123L);
        restaurant.setNit(10L);
        restaurant.setName("Test Restaurant");
        restaurant.setAddress("Test Address");
        restaurant.setPhone("+123456789");
        restaurant.setUrlLogo("http://test.com/logo.png");

        User owner = new User();
        owner.setId(123L);

        Restaurant savedRestaurant = new Restaurant();
        savedRestaurant.setNit(10L);

        when(userPersistencePort.findById(123L)).thenReturn(owner);
        when(restaurantPersistencePort.findById(10L)).thenReturn(null);
        when(restaurantPersistencePort.saveRestaurant(restaurant, owner)).thenReturn(savedRestaurant);

        MessageResponse response = restaurantUseCase.saveRestaurant(restaurant);

        assertEquals("Restaurant created with id 10", response.getMessage());
    }

    @Test
    void getAllRestaurants_shouldReturnListOfRestaurants() {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPage(0);
        pageRequest.setSize(10);

        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant());
        restaurants.add(new Restaurant());

        when(restaurantPersistencePort.getAllRestaurants(0, 10)).thenReturn(restaurants);

        List<Restaurant> result = restaurantUseCase.getAllRestaurants(pageRequest);

        assertEquals(2, result.size());
    }
}
