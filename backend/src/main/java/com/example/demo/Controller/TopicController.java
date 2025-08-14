package com.example.demo.Controller;

import com.example.demo.DTOs.TopicsDTO;
import com.example.demo.Repository.TopicsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("topics")
public class TopicController
{
    private final TopicsRepository topicsRepo;
    private final ModelMapper mapper;

    public TopicController(TopicsRepository topicsRepository, ModelMapper modelMapper)
    {
        this.topicsRepo = topicsRepository;
        this.mapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity<List<TopicsDTO>> getTopics()
    {
        List<TopicsDTO> topics = topicsRepo.findAll().stream().map(tp -> mapper.map(tp, TopicsDTO.class)).toList();
        return ResponseEntity.ok(topics);
    }
}
