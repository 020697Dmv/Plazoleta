package com.plazoleta.infrastructure.out.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;

public interface IOrderRepository  extends JpaRepository <OrderEntity,Long>{
	
	boolean existsByClientIdAndStatusIn(Long clientId, List<String> statuses);

}
