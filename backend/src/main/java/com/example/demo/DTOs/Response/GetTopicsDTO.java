package com.example.demo.DTOs.Response;

import com.example.demo.Entities.Topics;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTopicsDTO
{
    private String topicsName;
    private String username;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate created;
    private String description;
    private int threadCount;
    private int postCount;
    private Topics.TOPIC_TYPE topicType;
}
