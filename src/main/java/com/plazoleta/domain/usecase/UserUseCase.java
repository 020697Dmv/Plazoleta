package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IUserServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.User;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.domain.validacion.UserValidation;
import com.plazoleta.infrastructure.excepcion.RestaurantExistExcepcion;
import com.plazoleta.infrastructure.excepcion.UserNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserUseCase  implements IUserServicePort {
	
	private final IUserPersistencePort userPersistencePort;

	@Override
	public MessageResponse saveUser(User user) {
			
		userPersistencePort.findById(user.getId()).
		ifPresent(r -> { throw new UserNotFoundException(); });
		UserValidation.validateUser(user);
		user.setRole("PROPIETARIO");
		User saveUsers=userPersistencePort.saveUser(user);
        return new MessageResponse(String.format("User created with id %d", saveUsers.getId()));
	}

}
