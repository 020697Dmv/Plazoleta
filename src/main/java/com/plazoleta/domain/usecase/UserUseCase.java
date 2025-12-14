package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IUserServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Usuario;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.domain.validacion.UsuarioValidacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserUseCase  implements IUserServicePort {
	
	private final IUserPersistencePort usuarioPersistenciasPuerto;

	@Override
	public MessageResponse guardarUsuario(Usuario usuario) {
		UsuarioValidacion.validarUsuario(usuario);
        usuario.setRol("PROPIETARIO");
		Usuario guardarUsuario=usuarioPersistenciasPuerto.guardarUsuario(usuario);
        return new MessageResponse(String.format("Neighborhood created with id %d", guardarUsuario.getId()));
	}

}
