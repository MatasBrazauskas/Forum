package com.example.demo.Service;

import com.example.demo.Config.tempConst;
import com.example.demo.DTOs.ProfileInfoDTO;
import com.example.demo.DTOs.RegisterDTO;
import com.example.demo.Entities.UserProfile;
import com.example.demo.Middleware.CookieFactory;
import com.example.demo.Middleware.Validation.JWTutils;
import com.example.demo.Repository.UserProfileRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Profile;
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
            var date = LocalDate.now();
            user = new UserProfile(registerDTO.getUsername(), registerDTO.getEmail(), LocalDate.now(), LocalDate.now(), 0, 0, UserProfile.Role.USER);
            userProfileRepo.save(user);

        }
        cookieFactory.addCookie(response, registerDTO.getUsername(), tempConst.CookieNames.persistentCookieName);

        cookieFactory.addCookie(response,registerDTO.getUsername(), tempConst.CookieNames.sessionCookieName);

        return ResponseEntity.ok(mapper.map(user, ProfileInfoDTO.class));
    }

    @Transactional
    public ResponseEntity<?> loginUser(String sessionToken, String persistentToken, HttpServletResponse response)
    {
        if(persistentToken != null && sessionToken == null)
        {
            final var username = jWTutils.extractName(persistentToken);
            final var user =  userProfileRepo.findByUsername(username);

            if(user != null)
            {
                return ResponseEntity.ok(mapper.map(user, ProfileInfoDTO.class));
            }
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
