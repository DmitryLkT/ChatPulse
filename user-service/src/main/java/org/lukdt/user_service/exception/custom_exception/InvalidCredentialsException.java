package org.lukdt.user_service.exception.custom_exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Invalid email or password");
    }
}
