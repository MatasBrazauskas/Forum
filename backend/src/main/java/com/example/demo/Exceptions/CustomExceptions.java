package com.example.demo.Exceptions;

public final class CustomExceptions
{
    public static final class JWTMissing extends RuntimeException
    {
        public JWTMissing(String message)
        {
            super(message);
        }
    }
}
