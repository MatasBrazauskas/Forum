package com.example.demo.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@EnableCaching
@PropertySource("constants.properties")
public class CORSconfig implements WebMvcConfigurer
{
    @Value("${cors.allowed.origins}")
    private String allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry)
    {
        corsRegistry.addMapping("/**").allowedOriginPatterns("http://localhost:5173")
            .allowedMethods("*")
            .allowedHeaders("*")
                .allowCredentials(true)
            .allowCredentials(true)
            .maxAge(3600);
    }
}
