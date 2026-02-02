package com.plazoleta.infrastructure.out.jpa.repository;

import com.plazoleta.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IUserRepository  extends JpaRepository<UserEntity, Long>{
	Optional<UserEntity> findById(Long id);
	Optional<UserEntity> findByEmail(String email);

}
