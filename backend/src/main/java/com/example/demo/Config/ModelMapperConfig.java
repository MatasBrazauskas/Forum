package com.example.demo.Config;

import com.example.demo.DTOs.ProfileInfoDTO;
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

        return modelMapper;
    }
}
