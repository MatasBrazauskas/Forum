package com.example.demo.Config;

import com.example.demo.DTOs.ProfileInfoDTO;
import com.example.demo.DTOs.TopicsDTO;
import com.example.demo.Entities.Topics;
import com.example.demo.Entities.UserProfile;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ModelMapperConfig
{
    @Bean
    public ModelMapper modelMapper()
    {
        var modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setSkipNullEnabled(true);

        modelMapper.createTypeMap(UserProfile.class, ProfileInfoDTO.class)
                .addMapping(user -> user.getUsername(), ProfileInfoDTO::setUsername)
                .addMapping(user -> user.getRole(), ProfileInfoDTO::setRole)
                .addMapping(user -> user.getDateOfCreation(), ProfileInfoDTO::setDateOfCreation)
                .addMapping(user -> user.getLastOnline(), ProfileInfoDTO::setLastOnline)
                .addMapping(user -> user.getPostCount(), ProfileInfoDTO::setPostCount)
                .addMapping(user -> user.getReputation(), ProfileInfoDTO::setReputation);

        modelMapper.createTypeMap(Topics.class, TopicsDTO.class)
                .addMapping(tp -> tp.getTopicsName(), TopicsDTO::setTopicsName)
                .addMapping(tp -> tp.getCreator().getUsername(), TopicsDTO::setUsername)
                .addMapping(tp -> tp.getCreated(), TopicsDTO::setCreated)
                .addMapping(tp -> tp.getDescription(), TopicsDTO::setDescription)
                .addMapping(tp -> tp.getThreadCount(), TopicsDTO::setThreadCount)
                .addMapping(tp -> tp.getPostCount(), TopicsDTO::setPostCount)
                .addMapping(tp -> tp.getTopicType(), TopicsDTO::setTopicType);

        return modelMapper;
    }
}
