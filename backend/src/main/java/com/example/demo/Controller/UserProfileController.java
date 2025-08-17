package com.example.demo.Controller;

import com.example.demo.DTOs.ProfileInfoDTO;
import com.example.demo.Repository.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
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
        log.info("Getting users data");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final var principal = authentication.getPrincipal();

        if(principal == null){
            log.error("Principal is null, there is no email");
            return  ResponseEntity.noContent().build();
        }

        var profile = userProfileRepo.findByEmail(principal.toString());
        return ResponseEntity.ok().body(mapper.map(profile,  ProfileInfoDTO.class));
    }
}
