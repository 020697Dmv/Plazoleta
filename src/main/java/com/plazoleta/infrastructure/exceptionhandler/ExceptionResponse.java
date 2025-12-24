package com.plazoleta.infrastructure.exceptionhandler;

public enum ExceptionResponse {
	USER_NOT_FOUND("No User was found"),
    USER_ALREADY_EXISTS("There is already a user with that ID"),
    RESTAURANT_NOT_FOUND("No Restaurant was found"),
    RESTAURANT_NOT_EXIST("No EXIST Restaurants"),
    RESTAURANT_ALREADY_EXISTS("There is already a Restaurant with that ID"),
    PLATE_NOT_FOUND("No Plate was found"),
    PLATE_ALREADY_EXISTS("There is already a Plate with that ID"),
    EMPLOYEE_NOT_FOUND("No Employee was found"),
    EMPLOYEE_ALREADY_EXISTS("There is already a Employee with that ID"),
	CLIENT_NOT_FOUND("No Client was found"),
	CLIENT_ALREADY_EXISTS("There is already a Client with that ID");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public String getFormatMessage(RuntimeException runtimeException) {
        return String.format(this.message, runtimeException.getMessage());
    }
}
