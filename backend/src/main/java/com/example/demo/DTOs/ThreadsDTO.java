package com.example.demo.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ThreadsDTO
{
    private String username;
    private LocalDate lastOnline;

    private String title;
    private LocalDate dateOfCreation;
    private int commentCount;
    private int upvoteCount;
}
