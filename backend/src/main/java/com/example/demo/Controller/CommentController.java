package com.example.demo.Controller;

import com.example.demo.DTOs.Request.AddCommentDTO;
import com.example.demo.DTOs.Response.GetCommentsDTO;
import com.example.demo.Service.CommentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@Slf4j
public class CommentController
{
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping("/{threadsName}")
    @Cacheable(value = "commentCache", key = "#threadsName")
    public ResponseEntity<GetCommentsDTO> getCommentsFromThread(@PathVariable("threadsName") @NotEmpty String threadsName){
        log.info("Getting threads comments: {}", threadsName);
        return commentService.getCommentsFromThread(threadsName);
    }

    /*@PostMapping
    @CacheEvict(value = "commentCache", key = "#addCommentDTO.threadName")
    public ResponseEntity<?> createComment(@RequestBody @Valid AddCommentDTO addCommentDTO){
        log.info("Creating comment");
        return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.OK);
        return commentService.addComment(addCommentDTO);
    }*/
}
