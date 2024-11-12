package com.example.stockhouse.configurations;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;
import java.util.Vector;

import static org.keycloak.models.PasswordPolicy.build;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private  final JwtConverter jwtConverter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity


                .authorizeHttpRequests(auth -> auth

                        //AUTHCONTROLLER
                        .requestMatchers(HttpMethod.GET, "/v1/auth/login").authenticated()
                        .requestMatchers(HttpMethod.POST, "/v1/auth/signup").permitAll()
                        .requestMatchers(HttpMethod.GET,"/prodottoCategoria/elenca").permitAll()
                        .requestMatchers(HttpMethod.GET,"/prodotto/getProdotto").permitAll()
                        .requestMatchers(HttpMethod.GET,"/prodotto/elencaVetrina").permitAll()
                        .requestMatchers(HttpMethod.GET,"/prodottoCategoria/getProdotti").permitAll()
                        .requestMatchers(HttpMethod.GET,"/prodotto/getProdotti").permitAll()
                        .requestMatchers(HttpMethod.GET,"/prodotto/elencaProdByMarca").permitAll()
                        .requestMatchers(HttpMethod.POST,"/crea").permitAll()
                        .requestMatchers(HttpMethod.GET,"/utente/getPagamento").authenticated()
                        .requestMatchers(HttpMethod.GET,"/utente/getIndirizzo").authenticated()

                        .requestMatchers(HttpMethod.POST,"/utente/addIndirizzo").authenticated()
                        .requestMatchers(HttpMethod.POST,"/utente/addPagamento").authenticated()
                        .requestMatchers(HttpMethod.POST,"/prodotto/crea").hasRole("admin")

//hasRole(nomeRuolo)
                        //hasAnyRole(piu ruoli)
                        .anyRequest().authenticated())



                .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter)))
                .build();
    }
}

//se vuoi lo username String username= httpServletRequest.getAttribute("preferred_username").toString(); nel controller