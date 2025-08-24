package com.example.demo.DTOs.Response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GetThreadsDTO
{
    private String topicsName;
    private String description;

    private List<GetThreadDTO> threads;
}


