package com.plazoleta.infrastructure.exceptionhandler;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;

import com.plazoleta.infrastructure.exception.NotOrderCancelException;
import com.plazoleta.infrastructure.exception.NotPermissionuserException;
import com.plazoleta.infrastructure.exception.NotorderDeliveryException;
import com.plazoleta.infrastructure.exception.OrderNotFoundException;
import com.plazoleta.infrastructure.exception.PlateAlreadyExistException;
import com.plazoleta.infrastructure.exception.PlateNotFoundException;
import com.plazoleta.infrastructure.exception.RestaurantAlreadyExistException;
import com.plazoleta.infrastructure.exception.RestaurantEmployeeNotFoundException;
import com.plazoleta.infrastructure.exception.RestaurantsNotExistsException;
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
    
    
    @ExceptionHandler(RestaurantsNotExistsException.class)
    public ResponseEntity<Map<String, String>> handleRestaurantNotExistsException(
    		RestaurantsNotExistsException restaurantsNotExistsException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.USER_NOT_PERMISSION.getMessage()));
    }
    
    @ExceptionHandler(NotPermissionuserException.class)
    public ResponseEntity<Map<String, String>> handleNotPermissionsException(
    		NotPermissionuserException notPermissionuserException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.USER_NOT_PERMISSION.getMessage()));
    }
    
    @ExceptionHandler(RestaurantEmployeeNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRestaurantEmployeeNotFoundException(
    		RestaurantEmployeeNotFoundException restaurantEmployeeNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.RESTAURANT_EMPLOYEE_NOT_FOUND.getMessage()));
    }
    
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleOrderNotFoundException(
    		OrderNotFoundException orderNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ORDER_NOT_FOUND.getMessage()));
    }
    
    @ExceptionHandler(NotorderDeliveryException.class)
    public ResponseEntity<Map<String, String>> handleOrderNotAvailableException(
    		NotorderDeliveryException orderNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ORDER_NOT_AVAILABLE.getMessage()));
    }
    
    @ExceptionHandler(NotOrderCancelException.class)
    public ResponseEntity<Map<String, String>> handleOrderNotCancelException(
    		NotOrderCancelException orderNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ORDER_NOT_APREPARED.getMessage()));
    }
}
