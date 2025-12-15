package com.plazoleta.infrastructure.out.jpa.adapter;

import com.plazoleta.domain.model.User;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.infrastructure.excepcion.UsuarioExistExcepcion;
import com.plazoleta.infrastructure.out.jpa.entity.UserEntity;
import com.plazoleta.infrastructure.out.jpa.mapper.IUsuarioEntidadMapeo;
import com.plazoleta.infrastructure.out.jpa.repository.IUsuarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuarioAdapter implements IUserPersistencePort  {
	
	private final IUsuarioRepository usuarioRepository;
	private final IUsuarioEntidadMapeo usuarioEntidadMapeo;

	
	@Override
	public User saveUser(User user) {

		if(usuarioRepository.findById(user.getId()).isPresent()) {
			 throw new UsuarioExistExcepcion();			
		}
		user.setRole("PROPIETARIO");
		UserEntity userEntity=usuarioRepository.save(usuarioEntidadMapeo.toEntity(user));
			
		return usuarioEntidadMapeo.toUser(userEntity);
	}

}
