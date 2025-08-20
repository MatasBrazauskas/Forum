package com.example.demo.Controller;

import com.example.demo.DTOs.AddCommentDTO;
import com.example.demo.Entities.Comment;
import com.example.demo.Repository.CommentRepository;
import com.example.demo.Repository.ThreadsRepository;
import com.example.demo.Repository.UserProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
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
    public void comment(AddCommentDTO addCommentDTO){
        var threads = threadsRepo.findThreadByTitle(addCommentDTO.getThreadName()).orElse(null);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final var email =  authentication.getPrincipal().toString();
        var user = userProfileRepo.findByEmail(email);

        var comment = commentRepo.findById(addCommentDTO.getCommentId()).orElse(null);

        var com = new Comment();

        com.setCommentatorProfile(user);
        com.setThread(threads);
        com.setReply(comment);
        com.setComment(addCommentDTO.getComment());
        com.setDateOfComment(LocalDate.now());
    }

    @MessageMapping("/typing")
    @SendTo("topic/typing")
    public String typing(String username){
        return String.format("{} is typing ...", username);
    }
}
