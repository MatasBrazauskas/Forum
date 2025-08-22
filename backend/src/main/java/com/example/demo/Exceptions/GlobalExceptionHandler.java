package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.demo.Exceptions.CustomExceptions;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(CustomExceptions.RateLimitException.class)
    public ResponseEntity<String> handleException(Exception ex)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler(CustomExceptions.UserProfileNotFound.class)
    public ResponseEntity<String> handleUserProfileNotFound(Exception ex)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
