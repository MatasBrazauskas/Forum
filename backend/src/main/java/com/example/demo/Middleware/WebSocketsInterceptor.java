package com.example.demo.Middleware;

import com.example.demo.Exceptions.CustomExceptions;
import com.example.demo.Repository.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class WebSocketsInterceptor implements ChannelInterceptor
{
    private final UserProfileRepository userProfileRepo;
    private final JWTutils jwtutils;

    public WebSocketsInterceptor(UserProfileRepository userProfileRepo, JWTutils jwtutils)
    {
        this.userProfileRepo = userProfileRepo;
        this.jwtutils = jwtutils;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel)
    {
        log.info("Pre-Send WebSockets");
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String jwt = accessor.getFirstNativeHeader("Authorization");
            log.warn("This is JWT :{}", jwt);
            if (jwt != null) {

                //final var email = jwtutils.extractEmail(jwt);
                final var user = userProfileRepo.findByEmail(jwt).orElseThrow(() -> new CustomExceptions.UserProfileNotFound("Email"));
                log.warn("This is user :{}", user.getUsername());

                Authentication auth = new UsernamePasswordAuthenticationToken(
                        user, null, List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString().toUpperCase()))
                );

                accessor.setUser(auth);
            }
        }

        return message;
    }
}
