package com.example.demo.Controller;

import com.example.demo.DTOs.ProfileInfoDTO;
import com.example.demo.DTOs.RegisterDTO;
import com.example.demo.Service.CookieService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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
}
