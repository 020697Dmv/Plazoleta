package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.Usuario;

public interface IUserPersistencePort {
	Usuario guardarUsuario(Usuario usuario);

}
