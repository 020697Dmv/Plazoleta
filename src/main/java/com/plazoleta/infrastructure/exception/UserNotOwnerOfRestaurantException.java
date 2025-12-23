package com.plazoleta.infrastructure.exception;

public class UserNotOwnerOfRestaurantException extends RuntimeException {
    public UserNotOwnerOfRestaurantException() {
        super("The user is not the owner of the restaurant this plate belongs to.");
    }
}
