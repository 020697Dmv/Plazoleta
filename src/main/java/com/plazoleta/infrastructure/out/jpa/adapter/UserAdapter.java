package com.plazoleta.infrastructure.out.jpa.adapter;

import com.plazoleta.domain.model.User;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.infrastructure.excepcion.UserExistExcepcion;
import com.plazoleta.infrastructure.out.jpa.entity.UserEntity;
import com.plazoleta.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.plazoleta.infrastructure.out.jpa.repository.IUserRepository;
import com.plazoleta.infrastructure.excepcion.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserAdapter implements IUserPersistencePort  {
	
	private final IUserRepository usuarioRepository;
	private final IUserEntityMapper usuarioEntidadMapeo;

	
	@Override
	public User saveUser(User user) {

		if(usuarioRepository.findById(user.getId()).isPresent()) {
			 throw new UserExistExcepcion();			
		}
		user.setRole("PROPIETARIO");
		UserEntity userEntity=usuarioRepository.save(usuarioEntidadMapeo.toEntity(user));
		return usuarioEntidadMapeo.toUser(userEntity);
	}


	@Override
	public Optional<User> findById(Long id) {
	    return usuarioRepository.findById(id)
	            .map(usuarioEntidadMapeo::toUser);
	}

}
