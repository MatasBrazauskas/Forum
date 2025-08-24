package com.example.demo.Middleware;

import com.example.demo.Entities.UserProfile;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
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

    public String addPersistentCookie(HttpServletResponse response, String email)
    {
        final var jwtToken = jwtUtils.generatePersistentJWT(email);
        var persistentCookie = new Cookie(MiddlewareUtils.persistentCookieName, jwtToken);

        persistentCookie.setMaxAge(PERSISTENT_COOKIE_MAX_AGE);
        persistentCookie.setPath("/");
        persistentCookie.setHttpOnly(true);
        persistentCookie.setDomain("localhost");
        persistentCookie.setAttribute("SameSite", "Lax");

        response.addCookie(persistentCookie);

        return jwtToken;
    }

    public String addSessionCookie(HttpServletResponse response, UserProfile.Role role)
    {
        final var jwtToken = jwtUtils.generateSessionJWT(role.toString());
        var sessionCookie = new Cookie(MiddlewareUtils.sessionCookieName, jwtToken);

        sessionCookie.setPath("/");
        sessionCookie.setHttpOnly(true);
        sessionCookie.setDomain("localhost");
        sessionCookie.setAttribute("SameSite", "Lax");

        response.addCookie(sessionCookie);

        return jwtToken;
    }

    public ResponseEntity<Void> deletePersistentCookie(HttpServletResponse response){
        this.addSessionCookie(response, UserProfile.Role.GUEST);

        var deleteCookie = new Cookie(MiddlewareUtils.persistentCookieName, "");

        deleteCookie.setPath("/");
        deleteCookie.setHttpOnly(true);
        deleteCookie.setDomain("localhost");
        deleteCookie.setMaxAge(0);

        response.addCookie(deleteCookie);

        return ResponseEntity.ok().build();
    }
}