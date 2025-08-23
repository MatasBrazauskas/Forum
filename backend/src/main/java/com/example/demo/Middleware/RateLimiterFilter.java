package com.example.demo.Middleware;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.demo.Exceptions.CustomExceptions;

import java.io.IOException;

@Component
@Order(2)
@Slf4j
public class RateLimiterFilter extends OncePerRequestFilter {
    private final FixedWindowCounter counter;
    private final JWTutils jwtUtils;

    public RateLimiterFilter(FixedWindowCounter counter, JWTutils jwtUtils) {
        this.counter = counter;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String sessionJWT = request.getAttribute(MiddlewareUtils.sessionCookieName).toString();

        final String role = jwtUtils.extractRole(sessionJWT);
        final String uuid = jwtUtils.extractUUID(sessionJWT);

        log.info("Rate limiter - role: {}, uuid, {}", role, uuid);

        if (!counter.isAllowed(uuid, role)) {
            log.warn("Illegal Rate limiter - role: {}, uuid: {}", role, uuid);
            throw new CustomExceptions.RateLimitException(role);
        }

        filterChain.doFilter(request, response);
    }
}
