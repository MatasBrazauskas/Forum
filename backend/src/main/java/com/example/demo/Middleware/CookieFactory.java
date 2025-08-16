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
    private final int PERSISTENT_COOKIE_MAX_AGE = 60 * 60 * 24 * 30;

    public JWTutils jwtUtils;

    public CookieFactory(JWTutils jwtUtils)
    {
        this.jwtUtils = jwtUtils;
    }

    public void addPersistentCookie(HttpServletResponse response, String email)
    {
        final var jwtToken = jwtUtils.generatePersistentJWT(email);
        var persistentCookie = new Cookie(MiddlewareUtils.persistentCookieName, jwtToken);

        persistentCookie.setMaxAge(PERSISTENT_COOKIE_MAX_AGE);
        persistentCookie.setPath("/");
        persistentCookie.setHttpOnly(true);
        persistentCookie.setDomain("localhost");
        persistentCookie.setAttribute("SameSite", "Lax");

        response.addCookie(persistentCookie);
    }

    public void addSessionCookie(HttpServletResponse response, String role)
    {
        final var jwtToken = jwtUtils.generateSessionJWT(role);
        var sessionCookie = new Cookie(MiddlewareUtils.sessionCookieName, jwtToken);

        sessionCookie.setPath("/");
        sessionCookie.setHttpOnly(true);
        sessionCookie.setDomain("localhost");
        sessionCookie.setAttribute("SameSite", "Lax");

        response.addCookie(sessionCookie);
    }
}