package com.example.demo.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class GetThreadsDTO
{
    private String topicsName;
    private String description;

    private List<ThreadsDTO> threads;
}
