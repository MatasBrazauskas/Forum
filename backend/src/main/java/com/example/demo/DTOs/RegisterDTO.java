package com.example.demo.DTOs;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterDTO
{
    @NotEmpty
    @Max(value=32)
    private String username;

    @NotEmpty
    @Email
    @Max(value=255)
    private String email;
}
