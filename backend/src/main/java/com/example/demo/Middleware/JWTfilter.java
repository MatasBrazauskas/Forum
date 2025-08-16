package com.example.demo.Middleware;

import com.example.demo.Exceptions.CustomExceptions;
import com.example.demo.Repository.UserProfileRepository;
import com.example.demo.Service.CookieService;
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

import com.example.demo.Middleware.MiddlewareUtils;
import java.util.List;

@Component
@Order(2)
public final class JWTfilter extends OncePerRequestFilter
{
    private final JWTutils jwtUtils;
    private final UserProfileRepository userProfileRepo;
    private final CookieFactory cookieFactory;

    public JWTfilter(JWTutils jwtUtils,  UserProfileRepository userProfileRepo, CookieFactory cookieFactory)
    {
        this.jwtUtils = jwtUtils;
        this.userProfileRepo = userProfileRepo;
        this.cookieFactory = cookieFactory;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, CustomExceptions.JWTMissing, java.io.IOException {
        final String persistentCookieJWT = MiddlewareUtils.extractTokenFromCookie(request, MiddlewareUtils.persistentCookieName);
        final String sessionCookieJWT = MiddlewareUtils.extractTokenFromCookie(request, MiddlewareUtils.sessionCookieName);

        System.out.println("Persistent cookies JWT - " + persistentCookieJWT);
        System.out.println("Session cookies JWT - " + sessionCookieJWT);

        if(sessionCookieJWT == null)
        {
            System.out.println("Session cookies JWT is null");

            String role = "GUEST";
            if(persistentCookieJWT != null)
            {
                final var userProfile =  userProfileRepo.findByEmail(jwtUtils.extractEmail(persistentCookieJWT));
                cookieFactory.addPersistentCookie(response, userProfile.getEmail());
                role = userProfile.getRole().toString();
            }

            cookieFactory.addSessionCookie(response, role);
            var auth = new UsernamePasswordAuthenticationToken(null, null, List.of(new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase())));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        else{
            final String role = jwtUtils.extractRole(sessionCookieJWT);
            final var userProfile =  userProfileRepo.findByEmail(jwtUtils.extractEmail(persistentCookieJWT));
            var auth = new UsernamePasswordAuthenticationToken(userProfile.getUsername(), null, List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}