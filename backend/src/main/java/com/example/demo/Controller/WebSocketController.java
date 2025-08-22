package com.example.demo.Controller;

import com.example.demo.DTOs.AddCommentDTO;
import com.example.demo.Entities.Comment;
import com.example.demo.Exceptions.CustomExceptions;
import com.example.demo.Repository.CommentRepository;
import com.example.demo.Repository.ThreadsRepository;
import com.example.demo.Repository.UserProfileRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@Slf4j
public class WebSocketController
{
    private final UserProfileRepository userProfileRepo;
    private final ThreadsRepository threadsRepo;
    private final CommentRepository commentRepo;

    public WebSocketController(UserProfileRepository userProfileRepo, ThreadsRepository threadsRepo, CommentRepository commentRepo) {
        this.userProfileRepo = userProfileRepo;
        this.threadsRepo = threadsRepo;
        this.commentRepo = commentRepo;
    }

    @Transactional
    @MessageMapping("/comment")
    @SendTo("/topic/comments")
    public Comment comment(AddCommentDTO addCommentDTO, Principal principal){
        log.info("Adding a comment with WebSockets");
        final var email =  principal.getName();

        log.warn("This is users email: {}", email);


        final var threads = threadsRepo.findThreadByTitle(addCommentDTO.getThreadName()).orElse(null);
        final var user = userProfileRepo.findByEmail(email).orElseThrow(() -> new CustomExceptions.UserProfileNotFound("User not found"));
        final var reply = commentRepo.findById(addCommentDTO.getReplyId()).orElse(null);

        var com = new Comment();

        com.setCommentatorProfile(user);
        com.setThread(threads);
        com.setReply(reply);
        com.setComment(addCommentDTO.getComment());
        com.setDateOfComment(LocalDate.now());

        return commentRepo.save(com);
    }

    @MessageMapping("/typing")
    @SendTo("topic/typing")
    public String typing(String username){
        return String.format("{} is typing ...", username);
    }
}
