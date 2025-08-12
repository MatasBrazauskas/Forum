package com.example.demo.Controller;

import com.example.demo.DTOs.ProfileInfoDTO;
import com.example.demo.DTOs.RegisterDTO;
import com.example.demo.Middleware.CookieFactory;
import com.example.demo.Middleware.Validation.JWTutils;
import com.example.demo.Service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cookies")
public class CookieController
{
    private final CookieService cookieService;

    public CookieController(CookieService cookieService)
    {
        this.cookieService = cookieService;
    }

    @PostMapping("/register")
    public ResponseEntity<ProfileInfoDTO> register(HttpServletResponse response, /*@Valid*/ @RequestBody RegisterDTO registerDTO)
    {
        System.out.println(registerDTO.getUsername());
        System.out.println(registerDTO.getEmail());
        return cookieService.registerUser(response, registerDTO);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(
            @CookieValue(value = "sessionCookie", required = false) String sessionToken,
            @CookieValue(value = "persistentCookie", required = false) String persistentToken,
            HttpServletRequest request,
            HttpServletResponse response)
    {
        System.out.println(persistentToken);
        System.out.println(sessionToken);
        System.out.println(request.getCookies().length);
        return  cookieService.loginUser(sessionToken, persistentToken, response);
    }
}
