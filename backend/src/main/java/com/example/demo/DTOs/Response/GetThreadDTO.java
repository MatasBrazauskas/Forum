package com.example.demo.DTOs.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class GetThreadDTO
{
    private String username;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastOnline;

    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfCreation;
    private int commentCount;
    private int upvoteCount;
}
