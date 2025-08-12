package com.example.demo.Middleware;

import com.example.demo.Config.tempConst;
import com.example.demo.Middleware.Validation.JWTutils;
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

    public void addCookie(HttpServletResponse response, String username, tempConst.CookieNames cookieName)
    {
        if(cookieName == tempConst.CookieNames.persistentCookieName){
            addCookie(response, username, cookieName, PERSISTENT_COOKIE_MAX_AGE);
        }else {
            addCookie(response, username, cookieName, SESSION_COOKIE_MAX_AGE);
        }
    }

    private void addCookie(HttpServletResponse response, String username, tempConst.CookieNames cookieName, int maxAge)
    {
        final var token = jwtUtils.generateToken(username, maxAge);

        String cookieHeader = String.format(
                "sessionCookie=%s; Path=%s; Domain=%s; Max-Age=%d; HttpOnly; SameSite=Lax",
                token,
                "/",
                "localhost",
                maxAge
        );

        //var sessionCookie = new Cookie(cookieName.getName(), token);

        /*sessionCookie.setSecure(false);
        sessionCookie.setMaxAge(maxAge);
        sessionCookie.setHttpOnly(true);
        sessionCookie.setPath("/");
        sessionCookie.setDomain("localhost");
        sessionCookie.setAttribute("SameSite", "Lax");*/

        //sessionCookie.setSame("Lax");

        //response.addCookie(sessionCookie);

        response.addHeader("Set-Cookie", cookieHeader);
    }
}