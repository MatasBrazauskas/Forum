package com.example.demo.Service;

import com.example.demo.DTOs.ProfileInfoDTO;
import com.example.demo.DTOs.Request.CreateUserCookiesDTO;
import com.example.demo.DTOs.Response.PartialProfileInfoDTO;
import com.example.demo.Entities.UserProfile;
import com.example.demo.Exceptions.CustomExceptions;
import com.example.demo.Middleware.CookieFactory;
import com.example.demo.Middleware.JWTutils;
import com.example.demo.Repository.UserProfileRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CookieService
{
    private final UserProfileRepository userProfileRepo;
    private final CookieFactory cookieFactory;
    private final ModelMapper mapper;

    public CookieService(UserProfileRepository userProfileRepository, CookieFactory cookieFactory, ModelMapper modelMapper)
    {
        this.userProfileRepo = userProfileRepository;
        this.cookieFactory = cookieFactory;
        this.mapper = modelMapper;
    }

    @Transactional
    public ResponseEntity<ProfileInfoDTO> registerUser(HttpServletResponse response, CreateUserCookiesDTO createUserCookiesDTO)
    {
        var user = userProfileRepo.findByEmail(createUserCookiesDTO.getEmail()).orElseThrow(() -> new CustomExceptions.UserProfileNotFound(createUserCookiesDTO.getEmail()));

        if(user == null)
        {
            user = new UserProfile(createUserCookiesDTO.getUsername(), createUserCookiesDTO.getEmail(), LocalDate.now(), LocalDate.now(), 0, 0, UserProfile.Role.USER);
            userProfileRepo.save(user);
        }

        cookieFactory.addPersistentCookie(response, user.getEmail());
        cookieFactory.addSessionCookie(response, user.getRole());

        return ResponseEntity.ok(mapper.map(user, ProfileInfoDTO.class));
    }

    public ResponseEntity<PartialProfileInfoDTO> deleteCookie(HttpServletResponse response){
        cookieFactory.deletePersistentCookie(response);
        final var defaultUserInfo = new PartialProfileInfoDTO();
        return ResponseEntity.ok(defaultUserInfo);
    }
}
