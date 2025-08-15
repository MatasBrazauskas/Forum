package com.example.demo.Service;

import com.example.demo.DTOs.PartialProfileInfoDTO;
import com.example.demo.DTOs.ProfileInfoDTO;
import com.example.demo.DTOs.RegisterDTO;
import com.example.demo.Entities.UserProfile;
import com.example.demo.Middleware.CookieFactory;
import com.example.demo.Middleware.JWTutils;
import com.example.demo.Repository.UserProfileRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CookieService
{
    private final UserProfileRepository userProfileRepo;
    private final CookieFactory cookieFactory;
    private final JWTutils jWTutils;
    private final ModelMapper mapper;

    public CookieService(UserProfileRepository userProfileRepository, CookieFactory cookieFactory, JWTutils jWTutils, ModelMapper modelMapper)
    {
        this.userProfileRepo = userProfileRepository;
        this.cookieFactory = cookieFactory;
        this.jWTutils = jWTutils;
        this.mapper = modelMapper;
    }

    @Transactional
    public ResponseEntity<ProfileInfoDTO> registerUser(HttpServletResponse response, RegisterDTO registerDTO)
    {
        var user = userProfileRepo.findByUsername(registerDTO.getUsername());

        if(user == null)
        {
            user = new UserProfile(registerDTO.getUsername(), registerDTO.getEmail(), LocalDate.now(), LocalDate.now(), 0, 0, UserProfile.Role.USER);
            userProfileRepo.save(user);

        }

        cookieFactory.addPersistentCookie(response, registerDTO.getUsername(), user.getRole().toString());
        cookieFactory.addSessionCookie(response, registerDTO.getUsername(), user.getRole().toString());

        return ResponseEntity.ok(mapper.map(user, ProfileInfoDTO.class));
    }

    @Transactional
    public ResponseEntity<?> loginUser(String sessionToken, String persistentToken, HttpServletResponse response)
    {
        if(persistentToken == null)
        {
            System.out.println("Cookie service - There is no persistent token, creating session GUEST token!");
            cookieFactory.addSessionCookie(response, "GUEST", "GUEST");
            return ResponseEntity.ok(new PartialProfileInfoDTO("GUEST", "GUEST"));
        }
        else
        {
            System.out.println("Cookie service - Creating session and persistent token for USER");
            final var username = jWTutils.extractUsername(persistentToken);
            final var user =  userProfileRepo.findByUsername(username);

            if(sessionToken == null){
                cookieFactory.addSessionCookie(response, username,  user.getRole().toString());
            }

            return ResponseEntity.ok(mapper.map(user, ProfileInfoDTO.class));
        }
    }
}
