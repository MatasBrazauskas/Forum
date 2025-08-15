package com.example.demo.Middleware;

import com.example.demo.Exceptions.CustomExceptions;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

@Component
@Order(2)
public final class JWTfilter extends OncePerRequestFilter
{
    private final JWTutils jwtUtils;

    public JWTfilter(JWTutils jwtUtils)
    {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, CustomExceptions.JWTMissing, java.io.IOException {
        final String username = request.getAttribute("username").toString();
        final String role = request.getAttribute("role").toString();

        if(!username.equals("GUEST") && !role.equals("GUEST"))
        {
            System.out.println("JWT filter - this is USER, username: " + username + "role: " + role);
            System.out.println("JWT filter - username:" + username + " role:" + role);

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(username, null,
                            List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())));

            SecurityContextHolder.getContext().setAuthentication(auth);
        }else{
            System.out.println("JWT filter - this is GUEST.");
        }

        filterChain.doFilter(request, response);
    }
}