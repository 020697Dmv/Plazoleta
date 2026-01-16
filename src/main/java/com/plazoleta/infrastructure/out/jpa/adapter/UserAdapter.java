package com.plazoleta.infrastructure.out.jpa.adapter;

import com.plazoleta.domain.model.User;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.infrastructure.exception.UserAlreadyExistException;
import com.plazoleta.infrastructure.exception.UserNotFoundException;
import com.plazoleta.infrastructure.out.jpa.entity.UserEntity;
import com.plazoleta.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.plazoleta.infrastructure.out.jpa.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserAdapter implements IUserPersistencePort  {
	
	private final IUserRepository usuarioRepository;
	private final IUserEntityMapper usuarioEntidadMapeo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User saveUser(User user) {
		//Pasword se debe hacer en el domain, por ser logica de negocio
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		UserEntity userEntity=usuarioRepository.save(usuarioEntidadMapeo.toEntity(user));
		return usuarioEntidadMapeo.toUser(userEntity);
	}


	@Override
	public Optional<User> findById(Long id) {
	    return usuarioRepository.findById(id)
	            .map(usuarioEntidadMapeo::toUser);
	}

}
