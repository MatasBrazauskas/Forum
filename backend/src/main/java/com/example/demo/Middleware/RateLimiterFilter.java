package com.example.demo.Middleware;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(1)
public class RateLimiterFilter extends OncePerRequestFilter {
    private final FixedWindowCounter counter;
    private final JWTutils jwtUtils;

    public RateLimiterFilter(FixedWindowCounter counter, JWTutils jwtUtils) {
        this.counter = counter;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String sessionJWT = MiddlewareUtils.extractTokenFromCookie(request, MiddlewareUtils.sessionCookieName);
        System.out.println("Rate limiter - session cookie:" + sessionJWT);

        if (sessionJWT == null || !jwtUtils.validateToken(sessionJWT)) {
            System.out.println("Rate limiter - JWT is null or invalid. Pass to the JWT filter.");
            filterChain.doFilter(request, response);
            return;
        }

        final String role = jwtUtils.extractRole(sessionJWT);
        final String uuid = jwtUtils.extractUUID(sessionJWT);

        System.out.println("Rate limiter - role:" + role + " uuid:" + uuid);

        if (!counter.isAllowed(uuid, role)) {
            System.out.println("Invalid JWT: " + role + ' ' + uuid);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
