package com.plazoleta.domain.api;

import org.springframework.stereotype.Service;

import com.plazoleta.application.mapper.IMensajeRespuestaMapper;
import com.plazoleta.application.mapper.IUsuarioRequestMapper;
import com.plazoleta.application.mapper.IUsuarioResponseMapper;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Usuario;

import lombok.RequiredArgsConstructor;

public interface IUserServicePort {
	
	MessageResponse guardarUsuario(Usuario usuario);


}
