package com.plazoleta.infrastructure.out.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plazoleta.infrastructure.out.jpa.entity.UserEntity;
import java.util.List;


public interface IUserRepository  extends JpaRepository<UserEntity, Long>{
	Optional<UserEntity> findById(Long id);
	Optional<UserEntity> findByEmail(String email);

}
