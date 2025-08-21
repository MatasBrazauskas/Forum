package com.example.demo.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

//?????????????????????????????????
@Data
@NoArgsConstructor
public class GetPostDTO
{
    private String username;
    private String title;
    private LocalDate dateOfCreation;

    private List<GetCommentsDTO> comments;
}
