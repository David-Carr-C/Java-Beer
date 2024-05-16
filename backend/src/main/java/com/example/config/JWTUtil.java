package com.example.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.util.Date;

@Slf4j
@PropertySource("classpath:application.properties")
public class JWTUtil {
    @Value("${jwt.secret.key}")
    private String secretKey = "vhkD8yCSz8t+qUwhUlfjnYbCNKCitNJJo/38l+1laOs=";

    @Value("${jwt.expiration.time}")
    private long validityInMilliseconds = 31536000000L;

    public String generateAccessToken(String username) {
        log.info("Generating access token for user: {}", username);
        log.info("Secret key: {}", secretKey);
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(new Date().getTime() + validityInMilliseconds))
                .compact();
    }

    public boolean validateAccessToken(String token) {
        log.info("Validating access token: {}", token);
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim(String token, String claim, Class<T> requiredType) {
        log.info(getClaims(token).get(claim, requiredType).toString());
        return getClaims(token).get(claim, requiredType);
    }
}
