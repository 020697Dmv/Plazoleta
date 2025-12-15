package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.UserRequesteDto;
import com.plazoleta.application.dto.response.StringResponseDto;

public interface IUserHandler {

	StringResponseDto guardarUsuario(UserRequesteDto usuarioRequesteDto);
	
}
