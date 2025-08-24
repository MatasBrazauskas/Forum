package com.example.demo.Controller;

import com.example.demo.DTOs.Request.AddCommentDTO;
import com.example.demo.Entities.Comment;
import com.example.demo.Exceptions.CustomExceptions;
import com.example.demo.Repository.CommentRepository;
import com.example.demo.Repository.ThreadsRepository;
import com.example.demo.Repository.UserProfileRepository;
import com.example.demo.Service.CommentService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@Slf4j
public class WebSocketController
{

    private final CommentService  commentService;

    public WebSocketController(CommentService commentService)
    {
        this.commentService = commentService;
    }

    @Transactional
    @MessageMapping("/comment")
    @SendTo("/topic/comments")
    public Comment comment(AddCommentDTO addCommentDTO, Principal principal){
        log.info("Adding a comment with WebSockets");
        return commentService.addComment(addCommentDTO, principal);
    }

    @MessageMapping("/typing")
    @SendTo("topic/typing")
    public String typing(String username){
        return String.format("{} is typing ...", username);
    }
}
