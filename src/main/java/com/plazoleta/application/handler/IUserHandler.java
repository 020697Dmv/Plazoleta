package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.UserRequesteDto;
import com.plazoleta.application.dto.response.StringResponseDto;
import com.plazoleta.domain.model.MessageResponse;

public interface IUserHandler {

	MessageResponse saveUser(UserRequesteDto usuarioRequesteDto);
	
}
