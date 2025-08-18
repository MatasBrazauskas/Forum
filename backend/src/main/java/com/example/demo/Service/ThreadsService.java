package com.example.demo.Service;

import com.example.demo.DTOs.AddThreadInfoDTO;
import com.example.demo.DTOs.GettingThreadsDTO;
import com.example.demo.DTOs.ThreadsDTO;
import com.example.demo.Entities.Comment;
import com.example.demo.Entities.Thread;
import com.example.demo.Entities.Topics;
import com.example.demo.Repository.ThreadsRepository;
import com.example.demo.Repository.TopicsRepository;
import com.example.demo.Repository.UserProfileRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
@Slf4j
public class ThreadsService
{
    private final ModelMapper mapper;
    private final TopicsRepository topicsRepo;
    private final UserProfileRepository userProfileRepo;
    private final ThreadsRepository threadsRepo;

    public ThreadsService(ModelMapper mapper,  TopicsRepository topicsRepo, UserProfileRepository userProfileRepo, ThreadsRepository threadsRepo)
    {
        this.mapper = mapper;
        this.topicsRepo = topicsRepo;
        this.userProfileRepo = userProfileRepo;
        this.threadsRepo = threadsRepo;
    }

    @Transactional
    public GettingThreadsDTO getThreadsInfo(String topicsName)
    {
        final var topic = topicsRepo.findByTopicsName(topicsName).orElseThrow(() -> new RuntimeException("Topics not found"));
        topic.getThreads();

        var threadInfo = new GettingThreadsDTO();

        threadInfo.setTopicsName(topic.getTopicsName());
        threadInfo.setDescription(topic.getDescription());
        threadInfo.setThreads(topic.getThreads().stream().map(th -> mapper.map(th, ThreadsDTO.class)).toList());

        return threadInfo;
    }

    @Transactional
    public void addNewThread(String topicsName, AddThreadInfoDTO  addThreadInfoDTO)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final var email =  authentication.getPrincipal().toString();

        var userProfile = userProfileRepo.findByEmail(email);
        var topic = topicsRepo.findByTopicsName(topicsName).orElseThrow(() -> new RuntimeException("Topics not found"));

        var thread = new Thread();
        thread.setUserProfile(userProfile);
        thread.setTopics(topic);
        thread.setComments(new ArrayList<>());
        thread.setTitle(addThreadInfoDTO.getTitle());
        thread.setContent(addThreadInfoDTO.getContent());
        thread.setDateOfCreation(LocalDate.now());
        thread.setCommentCount(0);
        thread.setUpvoteCount(0);

        threadsRepo.save(thread);
    }
}
