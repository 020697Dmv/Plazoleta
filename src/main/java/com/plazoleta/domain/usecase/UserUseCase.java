package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IUserServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.model.User;
import com.plazoleta.domain.spi.IRestaurantEmployeePersistencePort;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.domain.validacion.UserValidation;
import com.plazoleta.infrastructure.out.jpa.util.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserUseCase  implements IUserServicePort {
	
	private final IUserPersistencePort userPersistencePort;
	
	private final IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort;
	
	private final IRestaurantPersistencePort restaurantPersistencePort;	

	
	@Autowired
	private  PasswordEncoder passwordEncoder;



	@Override
	public MessageResponse saveUser(User user,Long idRestaurant) {
		
		
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
	    System.out.println(Role.OWNER);
	    System.out.println(authorities.stream());

	    boolean isAuthAdmin = authorities.stream()
	            .anyMatch(a -> a.getAuthority().equals(Role.ADMINISTRATOR.name()));
	    boolean isAuthOwner = authorities.stream()
	            .anyMatch(a -> a.getAuthority().equals(Role.OWNER.name()));
	    boolean isAuthEmployee = authorities.stream()
	            .anyMatch(a -> a.getAuthority().equals(Role.EMPLOYEE.name()));
	
	    
	    Role roleToCreate = user.getRole();

	    if (roleToCreate == Role.OWNER && !isAuthAdmin) {
	    	return new MessageResponse(
	    			"You do not have permission to perform this action");
	    }

	    if (roleToCreate == Role.EMPLOYEE && !isAuthOwner) {
	    	return new MessageResponse(
	    			"You do not have permission to perform this action");
	    }

	    if (roleToCreate == Role.ADMINISTRATOR) {
	    	return new MessageResponse(
	    			"You do not have permission to perform this action");
	    }

	    if (userPersistencePort.existsById(user.getId())) {
	        return new MessageResponse("There is already a user with that ID");
	    }
	    
	    UserValidation.validateUser(user);
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    User savedUser = userPersistencePort.saveUser(user);

	    
	    if (roleToCreate == Role.EMPLOYEE) {
	        
	        Restaurant restaurant= restaurantPersistencePort.findById(idRestaurant);
	  
	        User ownerRestaurantSave = userPersistencePort
		            .findById(restaurant.getIdentity_document_owner());	        
	 
	        	restaurantEmployeePersistencePort.saveRestaurantEmployee( restaurant,ownerRestaurantSave, user);	        
	        
	    }
	    
	    
	    return new MessageResponse(String.format("User created with id %d", savedUser.getId()));
	}

}
