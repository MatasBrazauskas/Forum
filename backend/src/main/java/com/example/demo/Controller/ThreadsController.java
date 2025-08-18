package com.example.demo.Controller;

import com.example.demo.DTOs.AddThreadInfoDTO;
import com.example.demo.DTOs.GettingThreadsDTO;
import com.example.demo.Entities.Comment;
import com.example.demo.Entities.Thread;
import com.example.demo.DTOs.ThreadsDTO;
import com.example.demo.Entities.Topics;
import com.example.demo.Repository.ThreadsRepository;
import com.example.demo.Repository.TopicsRepository;
import com.example.demo.Repository.UserProfileRepository;
import com.example.demo.Service.ThreadsService;
import com.example.demo.Service.TopicsService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/threads")
@Slf4j
public class ThreadsController
{

    private final ThreadsService threadsService;

    public ThreadsController(ThreadsService threadsService)
    {
        this.threadsService = threadsService;
    }

    @Transactional
    @GetMapping("/{topicsName}")
    public ResponseEntity<GettingThreadsDTO> getTopicsThreads(@PathVariable("topicsName") String topicsName)
    {
        log.info("Getting threads for topic {}", topicsName);
        var threadInfo = threadsService.getThreadsInfo(topicsName);
        return ResponseEntity.ok(threadInfo);
    }

    @Transactional
    @PostMapping("/{topicsName}")
    public ResponseEntity<?> addNewThread(@PathVariable("topicsName") String topicsName, @RequestBody AddThreadInfoDTO  addThreadInfoDTO)
    {
        log.info("Adding threads for topic {}", topicsName);
        threadsService.addNewThread(topicsName, addThreadInfoDTO);
        return ResponseEntity.ok().build();
    }
}
