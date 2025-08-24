package com.example.demo.DTOs.Request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/// ///////////////////////////////
@Data
public class CreateUserCookiesDTO
{
    @NotEmpty
    private String username;
    @NotEmpty
    private String email;
}
