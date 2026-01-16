package org.lukdt.user_service.exception.custom_exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(String.format("Email already exists: %s", message));
    }
}
