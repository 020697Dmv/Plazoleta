package com.plazoleta.infrastructure.out.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEmployeeEntity;

public interface IRestaurantEmployeeRepository  extends JpaRepository<RestaurantEmployeeEntity, Long>{

}
