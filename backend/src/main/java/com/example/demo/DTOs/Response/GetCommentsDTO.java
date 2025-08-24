package com.example.demo.DTOs.Response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//?????????????????????????????????
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
    private Long replyId;
}
