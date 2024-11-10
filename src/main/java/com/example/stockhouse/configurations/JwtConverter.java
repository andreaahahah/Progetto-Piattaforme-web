package com.example.stockhouse.configurations;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    private final JwtConverterProperties properties;

    public JwtConverter(JwtConverterProperties properties) {
        this.properties = properties;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        // Combina i ruoli di base con i ruoli aggiuntivi estratti dal JWT
        Collection<GrantedAuthority> authorities = Stream.concat(
                jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                extractResourceRoles(jwt).stream()).collect(Collectors.toSet());

        // Crea il token di autenticazione con i ruoli e altre informazioni
        AbstractAuthenticationToken token = new JwtAuthenticationToken(jwt, authorities, getPrincipalClaimName(jwt));
        return token;
    }

    private String getPrincipalClaimName(Jwt jwt) {
        // Se viene fornito un attributo principale personalizzato, usalo, altrimenti usa 'sub'
        String claimName = JwtClaimNames.SUB;
        if (properties.getPrincipalAttribute() != null) {
            claimName = properties.getPrincipalAttribute();
        }

        return jwt.getClaim(claimName);
    }

    // Metodo aggiornato per estrarre i ruoli da "resource_access" e "realm_access"
    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        // Estrai i ruoli da resource_access
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        Map<String, Object> resourcePino = (Map<String, Object>) resourceAccess.get("pino");
        Collection<String> pinoRoles = resourcePino != null ? (Collection<String>) resourcePino.get("roles") : Set.of();

        // Estrai i ruoli da realm_access in modo sicuro
        Collection<String> realmRoles = Set.of();
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null && realmAccess.containsKey("roles")) {
            realmRoles = (Collection<String>) realmAccess.get("roles");
        }

        // Combina i ruoli da entrambe le fonti (pino e realm_access)
        Set<String> allRoles = Stream.concat(pinoRoles.stream(), realmRoles.stream())
                .collect(Collectors.toSet());

        // Restituisci i ruoli come autorità (ad esempio, ROLE_admin)
        return allRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }

}
