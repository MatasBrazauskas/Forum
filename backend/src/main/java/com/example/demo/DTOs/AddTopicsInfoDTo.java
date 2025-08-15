package com.example.demo.DTOs;

import com.example.demo.Entities.Topics;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddTopicsInfoDTo
{
    private String topicsName;
    private String description;
    private Topics topicType;
}
