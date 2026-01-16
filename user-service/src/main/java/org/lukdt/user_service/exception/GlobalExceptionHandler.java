package org.lukdt.user_service.exception;

import org.lukdt.user_service.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.lukdt.user_service.exception.custom_exception.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handlerUserAlreadyExists(UserAlreadyExistsException e) {
        return new ApiError(
                409,
                e.getMessage(),
                LocalDateTime.now()
        );
    }
}
