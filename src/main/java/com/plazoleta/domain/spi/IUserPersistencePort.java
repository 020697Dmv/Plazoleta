package com.plazoleta.domain.spi;

import java.util.Optional;

import com.plazoleta.domain.model.User;

public interface IUserPersistencePort {
	User saveUser(User usuario);
	
	Optional<User>  findById(Long id);
}
