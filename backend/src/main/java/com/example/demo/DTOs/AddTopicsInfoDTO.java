package com.example.demo.DTOs;

import com.example.demo.Entities.Topics;
import com.example.demo.Entities.UserProfile;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class AddTopicsInfoDTO
{
    @NotEmpty
    @Size(min = 1, max = 256)
    private String topicsName;

    @NotEmpty
    @Size(min = 1, max = 256)
    private String description;

    private Topics.TOPIC_TYPE topicType;
}
