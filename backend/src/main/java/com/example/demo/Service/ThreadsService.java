package com.example.demo.Service;

import com.example.demo.DTOs.Request.AddThreadDTO;
import com.example.demo.DTOs.Response.GetThreadDTO;
import com.example.demo.DTOs.Response.GetThreadsDTO;
import com.example.demo.Entities.Thread;
import com.example.demo.Repository.ThreadsRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
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
    private final ThreadsRepository threadsRepo;

    private final UserProfileService userProfileService;
    private final TopicsService topicsService;

    public ThreadsService(ModelMapper mapper, UserProfileService userProfileService, ThreadsRepository threadsRepo, TopicsService topicsService)
    {
        this.mapper = mapper;
        this.threadsRepo = threadsRepo;
        this.userProfileService = userProfileService;
        this.topicsService = topicsService;
    }

    @Transactional
    public ResponseEntity<GetThreadsDTO> getThreadsInfo(String topicsName)
    {
        final var topic = topicsService.getTopics(topicsName);
        topic.getThreads();

        var threadInfo = new GetThreadsDTO();

        threadInfo.setTopicsName(topic.getTopicsName());
        threadInfo.setDescription(topic.getDescription());
        threadInfo.setThreads(topic.getThreads().stream().map(th -> mapper.map(th, GetThreadDTO.class)).toList());

        return ResponseEntity.ok(threadInfo);
    }

    @Transactional
    public ResponseEntity<Void> addNewThread(AddThreadDTO addThreadDTO)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final var email =  authentication.getPrincipal().toString();

        var user = userProfileService.getUser(email);
        var topic = topicsService.getTopics(addThreadDTO.getTopicsName());

        var thread = new Thread();
        thread.setUserProfile(user);
        thread.setTopics(topic);
        thread.setComments(new ArrayList<>());
        thread.setTitle(addThreadDTO.getTitle());
        thread.setContent(addThreadDTO.getContent());
        thread.setDateOfCreation(LocalDate.now());
        thread.setCommentCount(0);
        thread.setUpvoteCount(0);

        threadsRepo.save(thread);
        topicsService.incrementThreads(addThreadDTO.getTopicsName());


        return ResponseEntity.ok().build();
    }

    public void saveThread(final Thread thread){
        threadsRepo.save(thread);
    }
}
