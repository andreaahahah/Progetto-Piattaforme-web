package com.example.stockhouse.configurations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.text.ParseException;

@Component
public class BearerTokenExtractor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //Presa del token dall'header della richiesta
        String authorizationHeader = request.getHeader("Authorization");

        //Controllo che l'header non sia nulla e sia effettivamente un token bearer
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            //Presa della stringa effettiva che rappresenta il token
            String bearerToken = authorizationHeader.substring(7);
            try {

                //Estrazione dell'username dal token
                String preferredUsername = extractPreferredUsernameFromToken(bearerToken);

                //Impostazione di un attributo della chiamata che rappresenta l'username dell'utente che ha effettuato la chiamata
                request.setAttribute("preferred_username", preferredUsername);
            } catch (ParseException e) {

                //Gestione di un token non valido
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return false;
            }
        }
        return true;
    }

    private String extractPreferredUsernameFromToken(String bearerToken) throws ParseException {
        //Decodifica del token e presa del claim preferred_username
        JwtDecoder jwtDecoder= NimbusJwtDecoder.withJwkSetUri("http://localhost:8080/realms/piattaforme/protocol/openid-connect/certs").build();
        Jwt jwt = jwtDecoder.decode(bearerToken);

        return jwt.getClaim("preferred_username");
    }
}