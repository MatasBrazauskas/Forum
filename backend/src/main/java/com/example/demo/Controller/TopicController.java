package com.example.demo.Controller;

import com.example.demo.DTOs.AddTopicsInfoDTo;
import com.example.demo.DTOs.TopicsDTO;
import com.example.demo.Entities.Topics;
import com.example.demo.Entities.UserProfile;
import com.example.demo.Repository.TopicsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController
{
    private final TopicsRepository topicsRepo;
    private final ModelMapper mapper;

    public TopicController(TopicsRepository topicsRepository, ModelMapper modelMapper)
    {
        this.topicsRepo = topicsRepository;
        this.mapper = modelMapper;
    }

    @GetMapping
    @Cacheable(value = "topicsCache", key = "'allTopics'")
    public ResponseEntity<List<TopicsDTO>> getTopics()
    {
        List<TopicsDTO> topics = topicsRepo.findAll().stream().map(tp -> mapper.map(tp, TopicsDTO.class)).toList();
        return ResponseEntity.ok(topics);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> getTopicsAuth(@RequestBody AddTopicsInfoDTo addTopicsInfo, @RequestBody UserProfile profile)
    {
        Topics newTopic = new Topics();
        newTopic.setCreator(profile);
        newTopic.setTopicsName(addTopicsInfo.getTopicsName());
        newTopic.setCreated(LocalDateTime.now());
        newTopic.setDescription(addTopicsInfo.getDescription());
        newTopic.setThreadCount(0);
        newTopic.setPostCount(0);
        newTopic.setTopicType(addTopicsInfo.getTopicType().getTopicType());

        topicsRepo.save(newTopic);
        return ResponseEntity.ok().build();
    }
}
