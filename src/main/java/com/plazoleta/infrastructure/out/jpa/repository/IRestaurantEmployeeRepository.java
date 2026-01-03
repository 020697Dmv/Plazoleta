package com.plazoleta.infrastructure.out.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEmployeeEntity;

public interface IRestaurantEmployeeRepository  extends JpaRepository<RestaurantEmployeeEntity, Long>{

	Optional<RestaurantEmployeeEntity> findByEmployeeId(Long employeeId);
}
