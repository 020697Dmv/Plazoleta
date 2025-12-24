package com.plazoleta.infrastructure.out.jpa.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;

import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long>{

	Page<RestaurantEntity> findAll(Pageable pageable);
}
