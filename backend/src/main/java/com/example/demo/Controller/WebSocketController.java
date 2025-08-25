package com.example.demo.Controller;

import com.example.demo.DTOs.Request.AddCommentDTO;
import com.example.demo.Service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@Slf4j
public class WebSocketController
{

    private final CommentService  commentService;

    public WebSocketController(CommentService commentService)
    {
        this.commentService = commentService;
    }

    @MessageMapping("/comment")
    @SendTo("/topic/comments")
    public String comment(AddCommentDTO addCommentDTO, Principal principal){
        log.info("Adding a comment with WebSockets");
        return commentService.addComment(addCommentDTO, principal);
    }

    @MessageMapping("/typing")
    @SendTo("topic/typing")
    public String typing(@Payload String username){
        log.error("Typing {}", username);
        return username + " is typing ...";
    }
}
