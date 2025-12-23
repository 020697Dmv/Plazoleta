package com.plazoleta.infrastructure.out.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plazoleta.infrastructure.out.jpa.entity.EmployeeEntity;

public interface IEmployeeRepository extends JpaRepository <EmployeeEntity,Long>{

}
