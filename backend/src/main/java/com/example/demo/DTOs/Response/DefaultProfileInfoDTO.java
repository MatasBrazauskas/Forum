package com.example.demo.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class DefaultProfileInfoDTO
{
    private final String username = "GUEST";
    private String role = "GUEST";
}
