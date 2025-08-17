package com.example.demo.Controller;

import com.example.demo.DTOs.AddTopicsInfoDTO;
import com.example.demo.DTOs.TopicsDTO;
import com.example.demo.Entities.Topics;
import com.example.demo.Entities.UserProfile;
import com.example.demo.Repository.TopicsRepository;
import com.example.demo.Repository.UserProfileRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/topics")
@Slf4j
public class TopicController
{
    private final TopicsRepository topicsRepo;
    private final ModelMapper mapper;
    private final UserProfileRepository userProfileRepo;

    public TopicController(TopicsRepository topicsRepository, ModelMapper modelMapper, UserProfileRepository userProfileRepository)
    {
        this.topicsRepo = topicsRepository;
        this.mapper = modelMapper;
        this.userProfileRepo = userProfileRepository;
    }

    @GetMapping
    @Cacheable(value = "topicsCache", key = "'allTopics'")
    public ResponseEntity<List<TopicsDTO>> getTopics()
    {
        log.info("Getting all of topics");
        List<TopicsDTO> topics = topicsRepo.findAll().stream().map(tp -> mapper.map(tp, TopicsDTO.class)).toList();
        return ResponseEntity.ok(topics);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @CacheEvict(value = "topicsCache", key="'allTopics'")
    public ResponseEntity<Void> addNewTopic(@RequestBody @Valid AddTopicsInfoDTO addTopicsInfo)
    {
        log.info("Adding new topic");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final var authUser =  authentication.getPrincipal().toString();

        var profile = userProfileRepo.findByEmail(authUser);

        var newTopic = new Topics();
        newTopic.setCreator(profile);
        newTopic.setTopicsName(addTopicsInfo.getTopicsName());
        newTopic.setCreated(LocalDateTime.now());
        newTopic.setDescription(addTopicsInfo.getDescription());
        newTopic.setThreadCount(0);
        newTopic.setPostCount(0);
        newTopic.setTopicType(addTopicsInfo.getTopicType());

        topicsRepo.save(newTopic);
        return ResponseEntity.ok().build();
    }
}
