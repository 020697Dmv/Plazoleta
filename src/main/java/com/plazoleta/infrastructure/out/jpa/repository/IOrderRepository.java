package com.plazoleta.infrastructure.out.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import java.util.List;
import java.util.Optional;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    
    boolean existsByClientIdAndStatusIn(Long clientId, List<String> statuses);

    Page<OrderEntity> findAllByStatus(String status, Pageable pageable);
    
    Page<OrderEntity> findAllByRestaurantNitAndStatus(Long restaurantNit, String status, Pageable pageable);
    
    Page<OrderEntity> findAllByRestaurantNit(Long restaurantNit, Pageable pageable);

    Optional<OrderEntity> findByIdAndRestaurantNit(Long id, Long restaurantNit);
    
    Optional<OrderEntity> findByIdAndRestaurantNitAndFkEmployeeId(Long id, Long restaurantNit, Long fkEmployeeId);
    
    Optional<OrderEntity> findByIdAndClientId(Long id, Long clientId);
}



