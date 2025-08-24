package com.example.demo.Service;

import com.example.demo.DTOs.Request.AddCommentDTO;
import com.example.demo.DTOs.Response.GetCommentDTO;
import com.example.demo.DTOs.Response.GetCommentsDTO;
import com.example.demo.DTOs.Response.PartialProfileInfoDTO;
import com.example.demo.Entities.Comment;
import com.example.demo.Repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;

@Slf4j
@Service
public class CommentService
{
    private final UserProfileService userProfileService;
    private final ThreadsService threadsService;
    private final CommentRepository commentRepo;
    private final ModelMapper modelMapper;

    public CommentService(UserProfileService userProfileService, ThreadsService threadsService, CommentRepository commentRepo, ModelMapper modelMapper)
    {
        this.userProfileService = userProfileService;
        this.threadsService = threadsService;
        this.commentRepo = commentRepo;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public ResponseEntity<GetCommentsDTO> getCommentsFromThread(String threadsName){
        final var thread = threadsService.findByTitle(threadsName);
        final var user = thread.getUserProfile();
        final var com = thread.getComments();

        var postsDTO = new GetCommentsDTO();
        final var partialProfileInfoDTO = modelMapper.map(user, PartialProfileInfoDTO.class);
        postsDTO.setPartialProfile(partialProfileInfoDTO);

        postsDTO.setTitle(thread.getTitle());
        postsDTO.setContent(thread.getContent());
        postsDTO.setContentDateOfCreation(thread.getDateOfCreation());

        postsDTO.setComments(com.stream().map(th -> modelMapper.map(th, GetCommentDTO.class)).toList());

        return ResponseEntity.ok(postsDTO);
    }

    @CacheEvict(value = "commentCache", key = "#addCommentDTO.threadName")
    @Transactional
    public Comment addComment(AddCommentDTO addCommentDTO, Principal principal) {
        final var email = principal.getName();

        log.warn("This is users email: {}", email);

        final var threads = threadsService.findByTitle(addCommentDTO.getThreadName());
        final var user = userProfileService.getUser(email);
        final var reply = commentRepo.findById(addCommentDTO.getReplyId()).orElse(null);

        var com = new Comment();

        com.setCommentatorProfile(user);
        com.setThread(threads);
        com.setReply(reply);
        com.setComment(addCommentDTO.getComment());
        com.setDateOfComment(LocalDate.now());

        commentRepo.save(com);
        return com;
    }
}
