package com.plazoleta.domain.usecase;
import java.util.List;

import org.springframework.stereotype.Service;

import com.plazoleta.domain.api.IUserServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Usuario;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.domain.validacion.UsuarioValidacion;

public class UserUseCase  implements IUserServicePort {
	
	private final IUserPersistencePort usuarioPersistenciasPuerto;
	
	public UserUseCase(IUserPersistencePort usuarioPersistenciasPuerto) {
		this.usuarioPersistenciasPuerto=usuarioPersistenciasPuerto;
	}

	@Override
	public MessageResponse guardarUsuario(Usuario usuario) {
		UsuarioValidacion.validarUsuario(usuario);
		Usuario guardarUsuario=usuarioPersistenciasPuerto.guardarUsuario(usuario);
        return new MessageResponse(String.format("Neighborhood created with id %d", guardarUsuario.getId()));
	}

}
