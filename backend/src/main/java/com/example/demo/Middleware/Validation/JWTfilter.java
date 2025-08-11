package com.example.demo.Middleware.Validation;

import com.example.demo.Exceptions.CustomExceptions;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*@Component
public final class JWTfilter extends OncePerRequestFilter
{
    @Value("${jwt.error.message}")
    private String jwtErrorMessage;

    private final JWTutils jwtUtils;

    public JWTfilter(JWTutils jwtUtils)
    {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, CustomExceptions.JWTMissing
    {
        final String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
        {
            throw new CustomExceptions.JWTMissing(jwtErrorMessage);
        }

        final String jwt = authorizationHeader.substring(7);
        final String name = jwtUtils.extractName(jwt);

        if(name != null && jwtUtils.validateToken(jwt))
        {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(name, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        else{
            throw new CustomExceptions.JWTMissing(jwtErrorMessage);
        }

        filterChain.doFilter(request, response);
    }
}*/