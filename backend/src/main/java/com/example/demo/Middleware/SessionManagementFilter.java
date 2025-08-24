package com.example.demo.Middleware;

import com.example.demo.Entities.UserProfile;
import com.example.demo.Exceptions.CustomExceptions;
import com.example.demo.Repository.UserProfileRepository;
import com.example.demo.Service.UserProfileService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDate;

@Component
@Order(1)
@Slf4j
public class SessionManagementFilter extends OncePerRequestFilter
{
    private final JWTutils jwtUtils;
    private final UserProfileService userProfileService;
    private final CookieFactory cookieFactory;

    public SessionManagementFilter(JWTutils jwtUtils,UserProfileService userProfileService, CookieFactory cookieFactory){
        this.jwtUtils = jwtUtils;
        this.cookieFactory = cookieFactory;
        this.userProfileService = userProfileService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String sessionJWT = MiddlewareUtils.extractTokenFromCookie(request, MiddlewareUtils.sessionCookieName);
        final String persistentJWT = MiddlewareUtils.extractTokenFromCookie(request, MiddlewareUtils.persistentCookieName);

        log.info("Session JWT : %s", sessionJWT);
        log.info("Persistent JWT : $s", persistentJWT);

        request.setAttribute(MiddlewareUtils.sessionCookieName, sessionJWT);
        request.setAttribute(MiddlewareUtils.persistentCookieName, persistentJWT);

        if(sessionJWT == null || !jwtUtils.validateToken(sessionJWT)){
            var role = UserProfile.Role.GUEST;

            if(persistentJWT != null){
                final String email = jwtUtils.extractEmail(persistentJWT);

                var user = userProfileService.getUser(email);
                user.setLastOnline(LocalDate.now());
                userProfileService.saveUser(user);

                role = user.getRole();
            }

            final String newSessionJWT = cookieFactory.addSessionCookie(response, role);
            request.setAttribute(MiddlewareUtils.sessionCookieName, newSessionJWT);
        }

        filterChain.doFilter(request, response);
    }
}
