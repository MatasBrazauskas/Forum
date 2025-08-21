package com.example.demo.Controller;

import com.example.demo.DTOs.AddTopicsDTO;
import com.example.demo.DTOs.GetTopicsDTO;
import com.example.demo.Service.TopicsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<GetTopicsDTO>> getTopics()
    {
        log.info("Getting all of topics");
        return topicsService.getTopics();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @CacheEvict(value = "topicsCache", key="'allTopics'")
    public ResponseEntity<Void> addNewTopic(@RequestBody AddTopicsDTO addTopicsInfo)
    {
        log.info("Adding new topic");
        return topicsService.addNewTopic(addTopicsInfo);
    }
}
