package com.example.demo.Middleware;

import com.example.demo.Middleware.Validation.JWTutils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CookieFactory
{
    public JWTutils jwtUtils;

    public CookieFactory(JWTutils jwtUtils)
    {
        this.jwtUtils = jwtUtils;
    }

    public void addCookie(HttpServletResponse response, String username, String tokenName, int maxAge)
    {
        final var token = jwtUtils.generateToken(username, maxAge);

        var sessionCookie = new Cookie(tokenName, token);

        if (maxAge > 0) {
            sessionCookie.setMaxAge(maxAge);
        } else {
            sessionCookie.setMaxAge(-1); // tells browser: delete when session ends
        }


        sessionCookie.setSecure(true);
        sessionCookie.setHttpOnly(true);
        //sessionCookie.setDomain("localhost");
        sessionCookie.setPath("/");
        sessionCookie.setAttribute("SameSite", "Lax");

        response.addCookie(sessionCookie);
    }
}