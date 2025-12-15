package com.plazoleta.infrastructure.out.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plazoleta.infrastructure.out.jpa.entity.UserEntity;

public interface IUsuarioRepository  extends JpaRepository<UserEntity, Long>{

}
