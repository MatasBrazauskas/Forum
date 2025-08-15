package com.example.demo.Middleware;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.rmi.server.ServerCloneException;

@Component
@Order(1)
public class RateLimiterFilter extends OncePerRequestFilter
{
    private final FixedWindowCounter counter;
    private final JWTutils jwtUtils;

    public RateLimiterFilter(FixedWindowCounter counter, JWTutils jwtUtils)
    {
        this.counter = counter;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String jwt = extractTokenFromCookie(request);
        System.out.println("Rate limiter - jwt:" + jwt);

        if(jwt == null || !jwtUtils.validateToken(jwt)){
            System.out.println("Rate limiter - JWT is null or invalid");
            request.setAttribute("username", "GUEST");
            request.setAttribute("role", "GUEST");
            filterChain.doFilter(request, response);
            return;
        }

        final String username = jwtUtils.extractUsername(jwt);
        final String role = jwtUtils.extractRole(jwt);
        final String uuid = jwtUtils.extractUUID(jwt);

        System.out.println("Rate limiter - username:" + username + " role:" + role + " uuid:" + uuid);

        if(!counter.isAllowed(uuid, role))
        {
            System.out.println("Invalid JWT: " + username + ' ' + role + ' ' + uuid);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return;
        }else{
            request.setAttribute("username", username);
            request.setAttribute("role", role);
        }

        filterChain.doFilter(request, response);
    }

    private String extractTokenFromCookie(HttpServletRequest request)
    {
        if(request.getCookies() == null)
        {
            return null;
        }

        for(Cookie c:  request.getCookies())
        {
            if("sessionCookie".equals(c.getName()))
            {
                return c.getValue();
            }
        }
        return null;
    }
}
