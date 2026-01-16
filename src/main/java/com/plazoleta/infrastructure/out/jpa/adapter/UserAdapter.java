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
	
	private final IUserRepository userRepository;
	private final IUserEntityMapper userEntidadMapeo;
	
	
	
	@Override
	public User saveUser(User user) {
		UserEntity userEntity=userRepository.save(userEntidadMapeo.toEntity(user));
		return userEntidadMapeo.toUser(userEntity);
	}


	@Override
	public User findById(Long id) {
		Optional<UserEntity>userEntity=userRepository.findById(id);
		
		if(userEntity.isEmpty()) {
			throw new  UserNotFoundException();
		}		
	    return  userEntidadMapeo.toUser(userEntity.get());
	}


	@Override
	public boolean existsById(Long id) {
		return userRepository.existsById(id);
	}

}
