package com.example.demo.DTOs;

import com.example.demo.Entities.Topics;
import lombok.Data;

@Data
public class AddThreadInfoDTO
{
    private String title;
    private String content;
}
