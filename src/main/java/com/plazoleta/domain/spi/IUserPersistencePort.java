package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.User;

public interface IUserPersistencePort {
	User saveUser(User usuario);

}
