package com.example.demo.Middleware;

import com.example.demo.Exceptions.CustomExceptions;
import com.example.demo.Repository.UserProfileRepository;
import com.example.demo.Service.CookieService;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.Middleware.MiddlewareUtils;
import java.util.List;

@Component
@Order(3)
@Slf4j
public final class JWTfilter extends OncePerRequestFilter
{
    private final JWTutils jwtUtils;

    public JWTfilter(JWTutils jwtUtils)
    {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, CustomExceptions.JWTMissing, java.io.IOException {
        final var persistentCookieJWT = request.getAttribute(MiddlewareUtils.persistentCookieName);
        final String sessionCookieJWT = request.getAttribute(MiddlewareUtils.sessionCookieName).toString();

        log.info("persistentCookieJWT: {}", persistentCookieJWT);
        log.info("sessionCookieJWT: {}", sessionCookieJWT);

        final String role = jwtUtils.extractRole(sessionCookieJWT);
        String email = null;

        if(persistentCookieJWT != null){
            email =  jwtUtils.extractEmail(persistentCookieJWT.toString());
        }

        log.info("Role and email: {}, {}", role, email);

        final var auth = new UsernamePasswordAuthenticationToken(email, role, List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())));
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }
}