package com.plazoleta.infrastructure.out.jpa.adapter;

import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.model.User;
import com.plazoleta.infrastructure.exception.RestaurantNotFoundException;
import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import com.plazoleta.infrastructure.out.jpa.entity.UserEntity;
import com.plazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.plazoleta.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.plazoleta.infrastructure.out.jpa.repository.IRestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantAdapterTest {

    @Mock
    private IRestaurantRepository restaurantRepository;

    @Mock
    private IUserEntityMapper userEntityMapper;

    @Mock
    private IRestaurantEntityMapper restaurantEntityMapper;

    @InjectMocks
    private RestaurantAdapter restaurantAdapter;

    @Test
    void saveRestaurant_mapsAndSaves() {
        Restaurant r = new Restaurant();
        User owner = new User();
        UserEntity ue = new UserEntity();
        RestaurantEntity re = new RestaurantEntity();
        RestaurantEntity saved = new RestaurantEntity();
        Restaurant out = new Restaurant();

        when(userEntityMapper.toEntity(owner)).thenReturn(ue);
        when(restaurantEntityMapper.toEntity(r)).thenReturn(re);
        when(restaurantRepository.save(re)).thenReturn(saved);
        when(restaurantEntityMapper.toRestaurant(saved)).thenReturn(out);

        Restaurant result = restaurantAdapter.saveRestaurant(r, owner);
        assertSame(out, result);
    }

    @Test
    void findById_missing_throws() {
        when(restaurantRepository.findById(10L)).thenReturn(Optional.empty());
        assertThrows(RestaurantNotFoundException.class, () -> restaurantAdapter.findById(10L));
    }

    @Test
    void getAllRestaurants_mapsPage() {
        RestaurantEntity e = new RestaurantEntity();
        List<RestaurantEntity> content = List.of(e);
        Page<RestaurantEntity> page = new PageImpl<>(content, PageRequest.of(0,10), 1);

        when(restaurantRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(page);
        when(restaurantEntityMapper.toRestaurant(e)).thenReturn(new Restaurant());

        List<Restaurant> result = restaurantAdapter.getAllRestaurants(0,10);
        assertEquals(1, result.size());
    }
}
