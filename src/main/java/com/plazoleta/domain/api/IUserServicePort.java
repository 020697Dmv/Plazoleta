package com.plazoleta.domain.api;

import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Usuario;

public interface IUserServicePort {
	
	MessageResponse guardarUsuario(Usuario usuario);


}
