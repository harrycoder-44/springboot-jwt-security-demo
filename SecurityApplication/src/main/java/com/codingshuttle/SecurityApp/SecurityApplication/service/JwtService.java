package com.codingshuttle.SecurityApp.SecurityApplication.service;

import com.codingshuttle.SecurityApp.SecurityApplication.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {

    @Value("${jwt.secretekey}")
    private String jwtSecreteKey;

    private SecretKey getsecretkey(){
        return Keys.hmacShaKeyFor(jwtSecreteKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(User user){
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("roles", Set.of("ADMIN","USER"))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60))
                .signWith(getsecretkey())
                .compact();
    }

    public Long getUserIdFromTokens(String tokens){
        Claims claims = Jwts.parser()
                .verifyWith(getsecretkey())
                .build()
                .parseSignedClaims(tokens)
                .getPayload();
        return Long.valueOf(claims.getSubject());

    }

}
