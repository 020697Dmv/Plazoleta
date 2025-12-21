package com.plazoleta.infrastructure.out.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plazoleta.infrastructure.out.jpa.entity.PlateEntity;

public interface IPlateRepository extends JpaRepository <PlateEntity, Long> {

}
