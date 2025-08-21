package com.example.demo.DTOs;

import com.example.demo.Entities.Topics;
import lombok.Data;
import lombok.NoArgsConstructor;

/// ///////////////////////
@Data
@NoArgsConstructor
public class AddTopicsDTO
{
    private String topicsName;
    private String description;
    private Topics.TOPIC_TYPE topicType;
}
