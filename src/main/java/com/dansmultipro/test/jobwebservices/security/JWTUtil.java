package com.dansmultipro.test.jobwebservices.security;

import com.dansmultipro.test.jobwebservices.entity.LoginAbleUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

@Component
public class JWTUtil {

    @Value("${job.service.jjwt.secret}")
    private String secret;

    @Value("${job.service.jjwt.expiration}")
    private String expirationTime;

    public Claims getAllClaimsFromToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(Base64.getEncoder().encodeToString(secret.getBytes()))
                .build()
                .parseClaimsJws(token).getBody();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            return false;
        }
    }

    public String generateToken(LoginAbleUser user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("nickname", user.getNickname());
        return doGenerateToken(claims, user.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {
        long expirationTimeLong = Long.parseLong(expirationTime); //in second

        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }


}
