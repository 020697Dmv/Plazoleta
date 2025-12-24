package com.plazoleta.infrastructure.exceptionhandler;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;

import com.plazoleta.infrastructure.exception.ClientAlreadyExistException;
import com.plazoleta.infrastructure.exception.ClientNotFoundException;
import com.plazoleta.infrastructure.exception.EmployeeAlreadyExistException;
import com.plazoleta.infrastructure.exception.EmployeeNotFoundException;
import com.plazoleta.infrastructure.exception.PlateAlreadyExistException;
import com.plazoleta.infrastructure.exception.PlateNotFoundException;
import com.plazoleta.infrastructure.exception.RestaurantAlreadyExistException;
import com.plazoleta.infrastructure.exception.UserAlreadyExistException;
import com.plazoleta.infrastructure.exception.UserNotFoundException;

@ControllerAdvice
public class ControllerAdvisor {
		
    private static final String MESSAGE = "message";

    
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(
    		UserAlreadyExistException ignoredUserAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.USER_ALREADY_EXISTS.getMessage()));
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(
    		UserNotFoundException ignoredUserNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.USER_NOT_FOUND.getMessage()));
    }
    
    @ExceptionHandler(RestaurantAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleRestaurantAlreadyExistsException(
    		RestaurantAlreadyExistException ignoredRestaurantAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.RESTAURANT_ALREADY_EXISTS.getMessage()));
    }
    
    @ExceptionHandler(PlateNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleAlreadyExistsException(
    		PlateNotFoundException plateNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PLATE_NOT_FOUND.getMessage()));
    }
    
    @ExceptionHandler(PlateAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleRestaurantNotFoundException(
    		PlateAlreadyExistException plateAlreadyExistException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PLATE_ALREADY_EXISTS.getMessage()));
    }
    
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleAlreadyExistsException(
    		EmployeeNotFoundException employeeNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.EMPLOYEE_NOT_FOUND.getMessage()));
    }
    
    @ExceptionHandler(EmployeeAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleRestaurantNotFoundException(
    		EmployeeAlreadyExistException employeeAlreadyExistException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.EMPLOYEE_ALREADY_EXISTS.getMessage()));
    }
    
    @ExceptionHandler(ClientAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleClientNotFoundException(
    		ClientAlreadyExistException clientAlreadyExistException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.CLIENT_ALREADY_EXISTS.getMessage()));
    }
    
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRestaurantNotFoundException(
    		ClientNotFoundException employeeAlreadyExistException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.CLIENT_NOT_FOUND.getMessage()));
    }
}
