package com.example.demo.DTOs.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PartialProfileInfoDTO
{
    private String username;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate joined;
    private int postCount;
    private int reputation;
}
