package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.UsuarioRequesteDto;
import com.plazoleta.application.dto.response.StringRespuestaDto;

public interface IUsuarioHandler {

	StringRespuestaDto guardarUsuario(UsuarioRequesteDto usuarioRequesteDto);
	
}
