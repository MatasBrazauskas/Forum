package com.example.demo.Controller;

import com.example.demo.DTOs.GetCommentsDTO;
import com.example.demo.DTOs.GetPostsDTO;
import com.example.demo.Repository.CommentRepository;
import com.example.demo.Repository.ThreadsRepository;
import com.example.demo.Repository.UserProfileRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController
{
    private final CommentRepository commentRepo;
    private final ThreadsRepository threadsRepo;
    private final UserProfileRepository userProfileRepo;
    private final ModelMapper modelMapper;

    public PostController(CommentRepository commentRepo, ThreadsRepository threadsRepo, UserProfileRepository userProfileRepo, ModelMapper modelMapper){
        this.commentRepo = commentRepo;
        this.threadsRepo = threadsRepo;
        this.userProfileRepo = userProfileRepo;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @GetMapping("/{threadsName}")
    public ResponseEntity<?> getPosts(@PathVariable("threadsName") String threadsName){
        var thread = threadsRepo.findThreadByTitle(threadsName).orElse(null);
        var user = thread.getUserProfile();
        var com = thread.getComments();

        var postsDTO = new  GetPostsDTO();
        postsDTO.setUsername(user.getUsername());
        postsDTO.setTitle(thread.getTitle());
        postsDTO.setDateOfCreation(thread.getDateOfCreation());
        postsDTO.setComments(com.stream().map(th -> modelMapper.map(th, GetCommentsDTO.class)).toList());

        return ResponseEntity.ok(postsDTO);
    }
}
