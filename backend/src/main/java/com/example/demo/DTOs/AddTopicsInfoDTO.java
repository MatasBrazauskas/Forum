package com.example.demo.DTOs;

import com.example.demo.Entities.Topics;
import com.example.demo.Entities.UserProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddTopicsInfoDTO
{
    private UserProfile userProfile;
    private String topicsName;
    private String description;
    private Topics.TOPIC_TYPE topicType;
}
