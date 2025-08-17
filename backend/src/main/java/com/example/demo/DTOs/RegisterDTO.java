package com.example.demo.DTOs;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterDTO
{
    private String username;
    private String email;
}
