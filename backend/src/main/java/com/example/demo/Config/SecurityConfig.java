package com.example.demo.Config;

import com.example.demo.Middleware.*;
import com.example.demo.Repository.UserProfileRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity// Ensures @PreAuthorize works
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,SessionManagementFilter sessionManagementFilter, RateLimiterFilter rateLimiterFilter, JWTfilter jwtFilter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/cookies/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cookies").permitAll()
                        .requestMatchers(HttpMethod.POST, "/topics").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/topics").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(sessionManagementFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(rateLimiterFilter,  UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}