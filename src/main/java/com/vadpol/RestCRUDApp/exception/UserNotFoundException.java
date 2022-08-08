package com.vadpol.RestCRUDApp.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int message) {
        super(String.valueOf(message));
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
