package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IUserServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.User;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.domain.validacion.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserUseCase  implements IUserServicePort {
	
	private final IUserPersistencePort userPersistencePort;

	@Override
	public MessageResponse guardarUsuario(User user) {
		UserValidation.validateUser(user);
		user.setRole("PROPIETARIO");
		User saveUsers=userPersistencePort.saveUser(user);
        return new MessageResponse(String.format("Neighborhood created with id %d", saveUsers.getId()));
	}

}
