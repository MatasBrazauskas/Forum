package com.example.demo.Service;

import com.example.demo.DTOs.Request.AddTopicsDTO;
import com.example.demo.DTOs.Response.GetTopicsDTO;
import com.example.demo.Entities.Topics;
import com.example.demo.Exceptions.CustomExceptions;
import com.example.demo.Repository.TopicsRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class TopicsService
{
    private final ModelMapper mapper;
    private final TopicsRepository topicsRepo;
    private final UserProfileService userProfileService;

    public TopicsService(TopicsRepository topicsRepository, ModelMapper modelMapper, UserProfileService userProfileService) {
        this.topicsRepo = topicsRepository;
        this.mapper = modelMapper;
        this.userProfileService =  userProfileService;
    }

    public ResponseEntity<List<GetTopicsDTO>> getTopics() {
        List<GetTopicsDTO> topics = topicsRepo.findAll().stream().map(tp -> mapper.map(tp, GetTopicsDTO.class)).toList();
        return ResponseEntity.ok().body(topics);
    }

    @Transactional
    public ResponseEntity<Void> addNewTopic(AddTopicsDTO addTopicsInfo)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final var email = authentication.getPrincipal().toString();

        var profile = userProfileService.getUser(email);

        var newTopic = new Topics();
        newTopic.setCreator(profile);
        newTopic.setTopicsName(addTopicsInfo.getTopicsName());
        newTopic.setCreated(LocalDate.now());
        newTopic.setDescription(addTopicsInfo.getDescription());
        newTopic.setThreadCount(0);
        newTopic.setPostCount(0);
        newTopic.setTopicType(addTopicsInfo.getTopicType());

        profile.setLastOnline(LocalDate.now());

        topicsRepo.save(newTopic);
        userProfileService.saveUser(profile);

        return ResponseEntity.ok().build();
    }

    public void incrementThreads(final String topicsName){
        var topics = topicsRepo.findByTopicsName(topicsName).orElseThrow(() -> new CustomExceptions.TopicsNotFound(topicsName));
        topics.setThreadCount(topics.getThreadCount() + 1);
        topicsRepo.save(topics);
    }

    public void incrementPosts(final String topicsName){
        var topics = topicsRepo.findByTopicsName(topicsName).orElseThrow(() -> new CustomExceptions.TopicsNotFound(topicsName));
        topics.setPostCount(topics.getThreadCount() + 1);
        topicsRepo.save(topics);
    }

    public Topics getTopics(String topicsName){
        return topicsRepo.findByTopicsName(topicsName).orElseThrow(() -> new CustomExceptions.TopicsNotFound(topicsName));
    }
}
