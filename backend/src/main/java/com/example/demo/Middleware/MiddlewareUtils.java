package com.example.demo.Middleware;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public final class MiddlewareUtils
{
    public final static String sessionCookieName = "sessionCookie";
    public final static String persistentCookieName = "persistentCookie";

    public static String extractTokenFromCookie(HttpServletRequest request, String cookiesName)
    {
        if(request.getCookies() == null)
            return null;

        for(Cookie c:  request.getCookies())
        {
            if(cookiesName.equals(c.getName()))
                return c.getValue();
        }
        return null;
    }
}
