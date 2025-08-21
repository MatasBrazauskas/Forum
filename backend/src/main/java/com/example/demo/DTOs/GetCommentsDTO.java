package com.example.demo.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class GetCommentsDTO
{
    private String username;
    private LocalDate joined;
    private int postCount;
    private int reputation;

    private LocalDate dateOfCreation;
    private String comment;

    private String reply;
    private long commentId;
}
