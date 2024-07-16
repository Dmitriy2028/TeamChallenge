package com.teamchallege.cartservice.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    public Authentication toAuthentication(String authHeader) {
        String jwt = authHeader.substring(7);
        String username = getEmail(jwt);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, getRoles(jwt).stream().map(SimpleGrantedAuthority::new).toList());
        return token;
    }

    private Key getSigningKey() {
        byte[] keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Long getUserId(String token) {
        return Long.parseLong(getClaims(token).get("id", String.class));
    }

    private String getEmail(String token) {
        return getClaims(token).get("email", String.class);
    }

    private List<String> getRoles(String token) {
        return getClaims(token).get("roles", List.class);
    }
}
