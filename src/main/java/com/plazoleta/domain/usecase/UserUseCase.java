package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IUserServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.User;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.domain.validacion.UserValidation;
import com.plazoleta.infrastructure.exception.NotPermissionuserException;
import com.plazoleta.infrastructure.exception.RestaurantAlreadyExistException;
import com.plazoleta.infrastructure.exception.UserAlreadyExistException;
import com.plazoleta.infrastructure.exception.UserNotFoundException;
import com.plazoleta.infrastructure.out.jpa.util.Role;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Collection;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserUseCase  implements IUserServicePort {
	
	private final IUserPersistencePort userPersistencePort;

	@Override
	public MessageResponse saveUser(User user) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

	    boolean isAuthAdmin = authorities.stream()
	            .anyMatch(a -> a.getAuthority().equals("ADMINISTRATOR"));
	    boolean isAuthOwner = authorities.stream()
	            .anyMatch(a -> a.getAuthority().equals("OWNER"));
	    boolean isAuthEmployee = authorities.stream()
	            .anyMatch(a -> a.getAuthority().equals("EMPLOYEE"));
	
	    
	    Role roleToCreate = user.getRole();

	    if (roleToCreate == Role.OWNER && !isAuthAdmin) {
	    	throw new NotPermissionuserException();
	    }

	    if (roleToCreate == Role.EMPLOYEE && !isAuthOwner) {
	    	throw new NotPermissionuserException();
	    }

	    if (roleToCreate == Role.ADMINISTRATOR) {
	    	throw new NotPermissionuserException();
	    }

	    if(userPersistencePort.findById(user.getId()).isPresent()) {
            throw new UserAlreadyExistException();
         }
	    
	    UserValidation.validateUser(user);
	    User savedUser = userPersistencePort.saveUser(user);
	    
	    return new MessageResponse(String.format("User created with id %d", savedUser.getId()));
	}

}
