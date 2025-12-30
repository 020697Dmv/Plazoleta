package com.plazoleta.infrastructure.out.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plazoleta.infrastructure.out.jpa.entity.OrderPlateEntity;

public interface IOrderPlateRepository extends JpaRepository <OrderPlateEntity,Long>{

}
