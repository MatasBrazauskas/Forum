package com.example.demo.Middleware;

import com.example.demo.Entities.UserProfile;
import com.example.demo.Exceptions.CustomExceptions;
import com.example.demo.Repository.UserProfileRepository;
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
    private final UserProfileRepository userProfileRepo;
    private final CookieFactory cookieFactory;

    public SessionManagementFilter(JWTutils jwtUtils, UserProfileRepository userProfileRepo, CookieFactory cookieFactory){
        this.jwtUtils = jwtUtils;
        this.userProfileRepo = userProfileRepo;
        this.cookieFactory = cookieFactory;
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

                var user = userProfileRepo.findByEmail(email).orElseThrow(( ) -> new CustomExceptions.UserProfileNotFound(email));
                user.setLastOnline(LocalDate.now());
                userProfileRepo.save(user);

                role = user.getRole();
            }

            final String newSessionJWT = cookieFactory.addSessionCookie(response, role);
            request.setAttribute(MiddlewareUtils.sessionCookieName, newSessionJWT);
        }

        filterChain.doFilter(request, response);
    }
}
