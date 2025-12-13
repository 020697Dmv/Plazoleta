package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.Usuario;

public interface UsuarioPersistenciasPuerto {
	Usuario guardarUsuario(Usuario usuario);
	
}
