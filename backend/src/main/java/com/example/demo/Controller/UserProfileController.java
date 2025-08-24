package com.example.demo.Controller;

import com.example.demo.DTOs.ProfileInfoDTO;
import com.example.demo.DTOs.Response.PartialProfileInfoDTO;
import com.example.demo.Exceptions.CustomExceptions;
import com.example.demo.Repository.UserProfileRepository;
import com.example.demo.Service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserProfileController
{
    private final UserProfileService userProfileService;
    private final ModelMapper mapper;

    public UserProfileController(UserProfileService userProfileService, ModelMapper mapper) {
        this.userProfileService = userProfileService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<?> getUserData()
    {
        log.info("Getting users data");
        final var authentication = SecurityContextHolder.getContext().getAuthentication();
        final var email = authentication.getPrincipal();

        if(email == null){
            log.error("Principal is null, there is no email");
            final var defaultUserInfo = new PartialProfileInfoDTO();
            return ResponseEntity.ok(defaultUserInfo);
        }

        final var profile = userProfileService.getUser(email.toString());
        return ResponseEntity.ok().body(mapper.map(profile,  ProfileInfoDTO.class));
    }
}
