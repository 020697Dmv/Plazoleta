package com.plazoleta.infrastructure.out.jpa.adapter;

import com.plazoleta.domain.model.Usuario;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.infrastructure.excepcion.UsuarioExisteExcepcion;
import com.plazoleta.infrastructure.out.jpa.entity.UsuarioEntidad;
import com.plazoleta.infrastructure.out.jpa.mapper.IUsuarioEntidadMapeo;
import com.plazoleta.infrastructure.out.jpa.repository.IUsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioAdapter implements IUserPersistencePort  {
	
	private final IUsuarioRepository usuarioRepository;
	private final IUsuarioEntidadMapeo usuarioEntidadMapeo;

	
	@Override
	public Usuario guardarUsuario(Usuario usuario) {

		if(usuarioRepository.findById(usuario.getId()).isPresent()) {
			 throw new UsuarioExisteExcepcion();			
		}
		
		UsuarioEntidad usuarioEntidad=usuarioRepository.save(usuarioEntidadMapeo.cambioEntidad(usuario));
			
		return usuarioEntidadMapeo.cambioUsuario(usuarioEntidad);
	}

}
