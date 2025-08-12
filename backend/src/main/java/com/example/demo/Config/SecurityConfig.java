package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        // Allow OPTIONS requests to pass through without authentication.
                        // This is the key line to fix your 401 preflight issue.
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // You should also allow your login endpoint.
                        // Make sure your login endpoint is publicly accessible.
                        .requestMatchers(HttpMethod.POST, "/cookies/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cookies/**").permitAll()
                        // All other requests require authentication
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}