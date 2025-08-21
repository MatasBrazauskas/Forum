package com.example.demo.Exceptions;

public final class CustomExceptions
{
    public static final class RateLimitException extends RuntimeException{
        public RateLimitException(String message){
            super(message);
        }
    }
}
