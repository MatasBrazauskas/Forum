package com.example.demo.Controller;

import com.example.demo.DTOs.ProfileInfoDTO;
import com.example.demo.Repository.UserProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserProfileController
{
    private final UserProfileRepository userProfileRepo;
    private final ModelMapper mapper;

    public UserProfileController(UserProfileRepository userProfileRepo, ModelMapper mapper) {
        this.userProfileRepo = userProfileRepo;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<ProfileInfoDTO> getUserData()
    {
        System.out.println("getUserData");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final var principal = authentication.getPrincipal();

        if(principal == null)
            return  ResponseEntity.badRequest().build();

        var profile = userProfileRepo.findByEmail(principal.toString());
        return ResponseEntity.ok().body(mapper.map(profile,  ProfileInfoDTO.class));
    }
}
