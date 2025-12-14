package com.plazoleta.application.handler.impl;

import org.springframework.stereotype.Service;


import com.plazoleta.application.dto.request.UsuarioRequesteDto;
import com.plazoleta.application.dto.response.StringRespuestaDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.plazoleta.application.handler.IUsuarioHandler;
import com.plazoleta.application.mapper.IMensajeRespuestaMapper;
import com.plazoleta.application.mapper.IUsuarioRequestMapper;
import com.plazoleta.application.mapper.IUsuarioResponseMapper;
import com.plazoleta.domain.api.IUserServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Usuario;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioHandler implements  IUsuarioHandler{
	
	public final IUserServicePort usuarioServicioPort;
	public final IUsuarioRequestMapper usuarioRequestMapper;
	public final IUsuarioResponseMapper usuarioResponseMapper;
	public final IMensajeRespuestaMapper StringMessageResponse;


	
	@Override
	public StringRespuestaDto guardarUsuario(UsuarioRequesteDto usuarioRequesteDto) {
		Usuario usuario=usuarioRequestMapper.aUsuario(usuarioRequesteDto);
		MessageResponse messageResponse=usuarioServicioPort.guardarUsuario(usuario);
		return StringMessageResponse.toResponse(messageResponse);
	}

}
