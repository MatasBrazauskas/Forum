package com.example.demo.Controller;

import com.example.demo.DTOs.Request.AddThreadDTO;
import com.example.demo.DTOs.Response.GetThreadsDTO;
import com.example.demo.Service.ThreadsService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @GetMapping("/{topicsName}")
    public ResponseEntity<GetThreadsDTO> getTopicsThreads(@PathVariable("topicsName") @NotEmpty String topicsName)
    {
        log.info("Getting threads for topic %s", topicsName);
        return threadsService.getThreadsInfo(topicsName);
    }

    @PostMapping
    public ResponseEntity<Void> addNewThread(@RequestBody @Valid AddThreadDTO addThreadDTO)
    {
        log.info("Adding threads for topic %s", addThreadDTO.getTopicsName());
        return threadsService.addNewThread(addThreadDTO);
    }
}
