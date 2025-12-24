package com.plazoleta.infrastructure.out.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.plazoleta.infrastructure.out.jpa.entity.PlateEntity; 

public interface IPlateRepository extends JpaRepository <PlateEntity, Long> {
	
    Page<PlateEntity> findByRestaurantNitAndCategory(Long nit, String category, Pageable pageable);

    Page<PlateEntity> findByRestaurantNit(Long nit, Pageable pageable);

}
