package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IUserServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.User;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.domain.validacion.UserValidation;
import com.plazoleta.infrastructure.exception.RestaurantAlreadyExistException;
import com.plazoleta.infrastructure.exception.UserAlreadyExistException;
import com.plazoleta.infrastructure.exception.UserNotFoundException;
import com.plazoleta.infrastructure.out.jpa.util.Role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserUseCase  implements IUserServicePort {
	
	private final IUserPersistencePort userPersistencePort;

	@Override
	public MessageResponse saveUser(User user) {
		
		if(userPersistencePort.findById(user.getId()).isPresent()) {
			throw new UserAlreadyExistException();
		}
		UserValidation.validateUser(user);
		user.setRole(Role.OWNER);
		User saveUsers=userPersistencePort.saveUser(user);
        return new MessageResponse(String.format("User created with id %d", saveUsers.getId()));
	}

}
