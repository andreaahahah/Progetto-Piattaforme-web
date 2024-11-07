package com.example.stockhouse.configurations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permetti CORS per tutti gli endpoint
                .allowedOrigins("http://localhost:5173") // Origine consentita
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Metodi consentiti
                .allowCredentials(true); // Permetti credenziali, opzionale
    }
}
