package com.example.demo.DTOs;

import com.example.demo.Entities.Topics;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTopicsDTO
{
    private String topicsName;
    private String username;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime created;
    private String description;
    private int threadCount;
    private int postCount;
    private Topics.TOPIC_TYPE topicType;
}
