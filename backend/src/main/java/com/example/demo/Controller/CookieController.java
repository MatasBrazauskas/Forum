package com.example.demo.Controller;

import com.example.demo.Middleware.CookieFactory;
import com.example.demo.Middleware.Validation.JWTutils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cookies")
public class CookieController
{

   private final int SESSION_COOKIE_MAX_AGE = 60 * 30;
   private final int PERSISTENT_COOKIE_MAX_AGE = 60 * 60 * 24 * 30;

    public static final String sessionCookieName = "sessionCookie";
    public static final String persistentCookieName = "persistentCookie";

    private final CookieFactory cookieFactory;

    public CookieController(CookieFactory cookieFactory)
    {
        this.cookieFactory = cookieFactory;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@CookieValue(value = "sessionCookie", required = false) String sessionToken,
                                @CookieValue(value = "persistentCookie", required = false) String persistentToken,
                                HttpServletResponse response, @RequestBody String username)
    {
        System.out.println("login");
        if(persistentToken == null)
        {
            cookieFactory.addCookie(response, username, persistentCookieName, PERSISTENT_COOKIE_MAX_AGE);
        }

        if(sessionToken == null)
        {
            cookieFactory.addCookie(response, username, sessionCookieName, -1);
        }

        return ResponseEntity.ok().build();
    }
}
