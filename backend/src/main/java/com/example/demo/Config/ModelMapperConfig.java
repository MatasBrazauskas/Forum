package com.example.demo.Config;

import com.example.demo.DTOs.*;
import com.example.demo.DTOs.Response.GetCommentDTO;
import com.example.demo.DTOs.Response.GetThreadDTO;
import com.example.demo.DTOs.Response.GetTopicsDTO;
import com.example.demo.DTOs.Response.PartialProfileInfoDTO;
import com.example.demo.Entities.Comment;
import com.example.demo.Entities.Thread;
import com.example.demo.Entities.Topics;
import com.example.demo.Entities.UserProfile;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

        modelMapper.createTypeMap(Topics.class, GetTopicsDTO.class)
                .addMapping(tp -> tp.getTopicsName(), GetTopicsDTO::setTopicsName)
                .addMapping(tp -> tp.getCreator().getUsername(), GetTopicsDTO::setUsername)
                .addMapping(tp -> tp.getCreated(), GetTopicsDTO::setCreated)
                .addMapping(tp -> tp.getDescription(), GetTopicsDTO::setDescription)
                .addMapping(tp -> tp.getThreadCount(), GetTopicsDTO::setThreadCount)
                .addMapping(tp -> tp.getPostCount(), GetTopicsDTO::setPostCount)
                .addMapping(tp -> tp.getTopicType(), GetTopicsDTO::setTopicType);

        modelMapper.createTypeMap(Thread.class, GetThreadDTO.class)
                .addMapping(th -> th.getUserProfile().getUsername(), GetThreadDTO::setUsername)
                .addMapping(th -> th.getUserProfile().getLastOnline(), GetThreadDTO::setLastOnline)
                .addMapping(th -> th.getTitle(), GetThreadDTO::setTitle)
                .addMapping(th -> th.getDateOfCreation(), GetThreadDTO::setDateOfCreation)
                .addMapping(th -> th.getCommentCount(), GetThreadDTO::setCommentCount)
                .addMapping(th -> th.getUpvoteCount(), GetThreadDTO::setUpvoteCount);

        modelMapper.createTypeMap(UserProfile.class, PartialProfileInfoDTO.class)
                .addMapping(user -> user.getUsername(), PartialProfileInfoDTO::setUsername)
                .addMapping(user -> user.getDateOfCreation(), PartialProfileInfoDTO::setJoined)
                .addMapping(user -> user.getPostCount(), PartialProfileInfoDTO::setPostCount)
                .addMapping(user -> user.getReputation(), PartialProfileInfoDTO::setReputation);


        /*modelMapper.createTypeMap(Comment.class, GetCommentDTO.class)
                .addMapping(t -> t.getCommentatorProfile().getUsername(), GetCommentDTO::setUsername)
                .addMapping(t -> t.getDateOfComment(), GetCommentDTO::setDateOfCreation)
                .addMapping(t -> t.getReply(), GetCommentDTO::setReply)
                .addMapping(t -> t.getThread().getUserProfile().getPostCount(), GetCommentDTO::setPostCount)
                .addMapping(t -> t.getThread().getUserProfile().getReputation(), GetCommentDTO::setReputation)
                .addMapping(t -> t.getComment(), GetCommentDTO::setComment)
                .addMapping(t -> t.getId(), GetCommentDTO::setReplyId);*/



        return modelMapper;
    }
}
