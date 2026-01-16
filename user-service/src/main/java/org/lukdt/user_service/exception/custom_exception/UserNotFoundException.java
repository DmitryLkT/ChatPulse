package org.lukdt.user_service.exception.custom_exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super(String.format("User not found: %s", email));
    }

    public UserNotFoundException(Long id) {
        super(String.format("User not found: %d", id));
    }
}
