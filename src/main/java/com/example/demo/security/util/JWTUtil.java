package com.example.demo.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.Map;

@Component
@Log4j2
public class JWTUtil {
    private static String key = "7923429348927423421234325345328734523427698";
    public String createToken(Map<String, Object> valueMap, int min) {
        SecretKey key = null;
        try {
            key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return Jwts.builder().header()
                .add("typ", "JWT")
                .add("alg", "HS256")
                .and()
                .issuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .expiration((Date.from(ZonedDateTime.now()
                        .plusMinutes(min).toInstant())))
                .claims(valueMap)
                .signWith(key)
                .compact();
    }
    public Map<String, Object> validateToken(String token) {
        SecretKey key = null;
        try {
            key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        Claims claims = Jwts.parser().verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims;
    }
}
