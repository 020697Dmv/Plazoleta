package com.plazoleta.infrastructure.out.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    
    boolean existsByClientIdAndStatusIn(Long clientId, List<String> statuses);

    Page<OrderEntity> findAllByStatus(String status, Pageable pageable);
}