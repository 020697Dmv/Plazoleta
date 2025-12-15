package com.plazoleta.application.handler.impl;

import org.springframework.stereotype.Service;


import com.plazoleta.application.dto.request.UserRequesteDto;
import com.plazoleta.application.dto.response.StringResponseDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.plazoleta.application.handler.IUserHandler;
import com.plazoleta.application.mapper.IMensajeRespuestaMapper;
import com.plazoleta.application.mapper.IUsuarioRequestMapper;
import com.plazoleta.application.mapper.IUsuarioResponseMapper;
import com.plazoleta.domain.api.IUserServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.User;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements  IUserHandler {
	
	public final IUserServicePort usuarioServicioPort;
	public final IUsuarioRequestMapper usuarioRequestMapper;
	public final IUsuarioResponseMapper usuarioResponseMapper;
	public final IMensajeRespuestaMapper StringMessageResponse;
	
	@Override
	public StringResponseDto guardarUsuario(UserRequesteDto usuarioRequesteDto) {
		User usuario=usuarioRequestMapper.aUsuario(usuarioRequesteDto);
		MessageResponse messageResponse=usuarioServicioPort.guardarUsuario(usuario);
		return StringMessageResponse.toResponse(messageResponse);
	}

}
