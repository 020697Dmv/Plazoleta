package com.plazoleta.domain.api;

import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.User;

public interface IUserServicePort {
	
	MessageResponse guardarUsuario(User usuario);


}
