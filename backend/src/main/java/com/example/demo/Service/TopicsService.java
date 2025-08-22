package com.example.demo.Service;

import com.example.demo.DTOs.AddTopicsDTO;
import com.example.demo.DTOs.GetTopicsDTO;
import com.example.demo.Entities.Topics;
import com.example.demo.Exceptions.CustomExceptions;
import com.example.demo.Repository.TopicsRepository;
import com.example.demo.Repository.UserProfileRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class TopicsService
{
    private final TopicsRepository topicsRepo;
    private final UserProfileRepository userProfileRepo;
    private final ModelMapper mapper;

    public TopicsService(TopicsRepository topicsRepository, ModelMapper modelMapper, UserProfileRepository userProfileRepository) {
        this.topicsRepo = topicsRepository;
        this.mapper = modelMapper;
        this.userProfileRepo = userProfileRepository;
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

        var profile = userProfileRepo.findByEmail(email).orElseThrow(() -> new CustomExceptions.UserProfileNotFound(email));

        var newTopic = new Topics();
        newTopic.setCreator(profile);
        newTopic.setTopicsName(addTopicsInfo.getTopicsName());
        newTopic.setCreated(LocalDateTime.now());
        newTopic.setDescription(addTopicsInfo.getDescription());
        newTopic.setThreadCount(0);
        newTopic.setPostCount(0);
        newTopic.setTopicType(addTopicsInfo.getTopicType());

        profile.setLastOnline(LocalDate.now());

        topicsRepo.save(newTopic);
        userProfileRepo.save(profile);

        return ResponseEntity.ok().build();
    }
}
