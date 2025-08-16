package com.example.demo.Controller;

import com.example.demo.DTOs.ProfileInfoDTO;
import com.example.demo.Repository.UserProfileRepository;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserProfileService
{
    private final UserProfileRepository userProfileRepo;
    private final ModelMapper mapper;

    public UserProfileService(UserProfileRepository userProfileRepo, ModelMapper mapper) {
        this.userProfileRepo = userProfileRepo;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<ProfileInfoDTO> getUserData()
    {
        System.out.println("getUserData");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String username = authentication.getPrincipal().toString();

        var profile = userProfileRepo.findByUsername(username);
        if(profile == null)
        {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(mapper.map(profile,  ProfileInfoDTO.class));
    }
}
