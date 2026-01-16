package com.plazoleta.domain.spi;

import java.util.Optional;

import com.plazoleta.domain.model.User;

public interface IUserPersistencePort {
	User saveUser(User usuario);
	
	User  findById(Long id);
	
	boolean existsById(Long id);
}
