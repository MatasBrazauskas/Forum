package com.example.demo.Middleware;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CookieFactory
{
    private final int SESSION_COOKIE_MAX_AGE = -1;
    private final int PERSISTENT_COOKIE_MAX_AGE = 60 * 5;

    public JWTutils jwtUtils;

    public CookieFactory(JWTutils jwtUtils)
    {
        this.jwtUtils = jwtUtils;
    }

    public void addPersistentCookie(HttpServletResponse response, String username, String role)
    {
        final var token = jwtUtils.generateToken(username, PERSISTENT_COOKIE_MAX_AGE, role);
        var persistentCookie = new Cookie("persistentCookie", token);

        persistentCookie.setMaxAge(PERSISTENT_COOKIE_MAX_AGE);
        persistentCookie.setPath("/");
        persistentCookie.setHttpOnly(true);
        persistentCookie.setDomain("localhost");
        persistentCookie.setAttribute("SameSite", "Lax");

        response.addCookie(persistentCookie);
    }

    public void addSessionCookie(HttpServletResponse response, String username, String role)
    {
        final var token = jwtUtils.generateToken(username, 60 * 5, role);
        var persistentCookie = new Cookie("sessionCookie", token);

        persistentCookie.setPath("/");
        persistentCookie.setHttpOnly(true);
        persistentCookie.setDomain("localhost");
        persistentCookie.setAttribute("SameSite", "Lax");

        response.addCookie(persistentCookie);
    }
}