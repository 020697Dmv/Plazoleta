package com.plazoleta.application.handler.impl;

import org.springframework.stereotype.Service;


import com.plazoleta.application.dto.request.UserRequesteDto;
import com.plazoleta.application.dto.response.StringResponseDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.plazoleta.application.handler.IUserHandler;
import com.plazoleta.application.mapper.IMensaggeResponseMapper;
import com.plazoleta.application.mapper.IUserRequestMapper;
import com.plazoleta.application.mapper.IUserResponseMapper;
import com.plazoleta.domain.api.IUserServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.User;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements  IUserHandler {
	
	public final IUserServicePort usuarioServicioPort;
	public final IUserRequestMapper userRequestMapper;
	
	@Override
	public MessageResponse saveUser(UserRequesteDto usuarioRequesteDto) {
		User usuario=userRequestMapper.aUsuario(usuarioRequesteDto);
		MessageResponse messageResponse=usuarioServicioPort.saveUser(usuario);
		return messageResponse;
	}

}
