package com.africa.semicolon.passwordmanagerapplication.exceptions;

public class InvalidUserDetailsException extends RuntimeException {
    public InvalidUserDetailsException(String message) {
        super(message);
    }
}
