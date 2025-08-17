package com.example.demo.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class GettingThreadsDTO
{
    private String topicsName;
    private String description;

    private List<ThreadsDTO> threads;
}
