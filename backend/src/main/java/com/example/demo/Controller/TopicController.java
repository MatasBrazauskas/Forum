package com.example.demo.Controller;

import com.example.demo.DTOs.AddTopicsInfoDTO;
import com.example.demo.DTOs.TopicsDTO;
import com.example.demo.Entities.Topics;
import com.example.demo.Entities.UserProfile;
import com.example.demo.Repository.TopicsRepository;
import com.example.demo.Repository.UserProfileRepository;
import com.example.demo.Service.TopicsService;
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
    private final TopicsService topicsService;

    public TopicController(TopicsService topicsService)
    {
        this.topicsService = topicsService;
    }

    @GetMapping
    @Cacheable(value = "topicsCache", key = "'allTopics'")
    public ResponseEntity<List<TopicsDTO>> getTopics()
    {
        log.info("Getting all of topics");
        return topicsService.getTopics();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @CacheEvict(value = "topicsCache", key="'allTopics'")
    public ResponseEntity<Void> addNewTopic(@RequestBody @Valid AddTopicsInfoDTO addTopicsInfo)
    {
        log.info("Adding new topic");
        return topicsService.addNewTopic(addTopicsInfo);
    }
}
