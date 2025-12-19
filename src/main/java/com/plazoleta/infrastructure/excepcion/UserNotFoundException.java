package com.plazoleta.infrastructure.excepcion;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super();
    }
    public UserNotFoundException(String message) {
    	
    	super(message);
    }
    
}
