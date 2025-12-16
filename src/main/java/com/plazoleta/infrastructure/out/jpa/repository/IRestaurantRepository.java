package com.plazoleta.infrastructure.out.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long>{

}
