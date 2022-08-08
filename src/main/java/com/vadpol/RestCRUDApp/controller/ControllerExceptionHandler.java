package com.vadpol.RestCRUDApp.controller;

import com.vadpol.RestCRUDApp.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final String EXСEPTION = "Пошло что-то не так...";
    private static final String USER_NOT_FOUND = "User not fount with id = %s";

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleInvalidTopUpTypeException(Exception ex) {
        return new ResponseEntity<>(Collections.singleton(EXСEPTION), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<?> handleException(Exception ex) {
        return new ResponseEntity<>(Collections.singleton(USER_NOT_FOUND), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<?> handleUserNotFoundException(Exception ex) {
        return new ResponseEntity<>(Collections.singleton(String.format(USER_NOT_FOUND, ex.getMessage())), HttpStatus.BAD_REQUEST);
    }
}