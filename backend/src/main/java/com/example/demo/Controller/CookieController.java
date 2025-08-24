package com.example.demo.Controller;

import com.example.demo.DTOs.ProfileInfoDTO;
import com.example.demo.DTOs.Request.CreateUserCookiesDTO;
import com.example.demo.DTOs.Response.DefaultProfileInfoDTO;
import com.example.demo.Service.CookieService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cookies")
@Slf4j
public class CookieController
{
    private final CookieService cookieService;

    public CookieController(CookieService cookieService)
    {
        this.cookieService = cookieService;
    }

    @PostMapping("/register")
    public ResponseEntity<ProfileInfoDTO> register(HttpServletResponse response, @RequestBody @Valid CreateUserCookiesDTO createUserCookiesDTO)
    {
        log.info("Logging/registering a user");
        return cookieService.registerUser(response, createUserCookiesDTO);
    }

    @DeleteMapping
    public ResponseEntity<DefaultProfileInfoDTO> deleteCookies(HttpServletResponse response){
        log.info("Deleting persistent cookie");
        return cookieService.deleteCookie(response);
    }
}
