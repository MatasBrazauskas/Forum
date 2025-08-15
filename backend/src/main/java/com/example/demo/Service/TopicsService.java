package com.example.demo.Service;

import com.example.demo.Entities.Topics;
import com.example.demo.Repository.TopicsRepository;
import org.springframework.stereotype.Service;

@Service
public class TopicsService
{
    private final TopicsRepository topicsRepository;

    public TopicsService(TopicsRepository topicsRepository) {
        this.topicsRepository = topicsRepository;
    }
}
