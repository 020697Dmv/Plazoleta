package com.plazoleta.infrastructure.out.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plazoleta.infrastructure.out.jpa.entity.ClientEntity;
import com.plazoleta.infrastructure.out.jpa.entity.EmployeeEntity;

public interface IClientRepository extends JpaRepository <ClientEntity,Long> {

}
