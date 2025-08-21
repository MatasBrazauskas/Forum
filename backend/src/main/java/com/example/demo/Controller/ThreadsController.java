package com.example.demo.Controller;

import com.example.demo.DTOs.AddThreadDTO;
import com.example.demo.DTOs.GetThreadsDTO;
import com.example.demo.Service.ThreadsService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<GetThreadsDTO> getTopicsThreads(@PathVariable("topicsName") String topicsName)
    {
        log.info("Getting threads for topic {}", topicsName);
        var threadInfo = threadsService.getThreadsInfo(topicsName);
        return ResponseEntity.ok(threadInfo);
    }

    @Transactional
    @PostMapping("/{topicsName}")
    public ResponseEntity<?> addNewThread(@RequestBody AddThreadDTO addThreadDTO)
    {
        log.info("Adding threads for topic {}", addThreadDTO.getTopicsName());
        threadsService.addNewThread(addThreadDTO);
        return ResponseEntity.ok().build();
    }
}
