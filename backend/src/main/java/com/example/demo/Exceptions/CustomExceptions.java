package com.example.demo.Exceptions;

public final class CustomExceptions
{
    public static final class RateLimitException extends RuntimeException{
        private static final String message = "Rate limit exceeded";
        public RateLimitException(final String credentials){
            super(String.format("{}, users role: {}", message, credentials));
        }
    }

    public static final class UserProfileNotFound extends RuntimeException{
        private static final String message = "User is not found";
        public UserProfileNotFound(final String credentials){
            super(String.format("{}, credentials: {}", message, credentials));
        }
    }
}
