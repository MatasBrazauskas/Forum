package com.example.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileInfoDTO
{
    private String username;
    private String role;
    private String dateOfCreation;
    private String lastOnline;
    private String postCount;
    private String reputation;
}
