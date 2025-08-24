package com.example.demo.DTOs.Request;

import com.example.demo.Entities.Topics;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/// ///////////////////////
@Data
@NoArgsConstructor
public class AddTopicsDTO
{
    @Size(min = 1, max = 64, message = "Topics name invalid: length between 1 and 64")
    private String topicsName;
    @Size(min = 1, max = 128, message = "Topics description invalid: length between 1 and 128")
    private String description;
    private Topics.TOPIC_TYPE topicType;
}
