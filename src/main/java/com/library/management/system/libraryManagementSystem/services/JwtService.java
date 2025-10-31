package com.library.management.system.libraryManagementSystem.services;

import com.library.management.system.libraryManagementSystem.utils.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    private final Utils utils;

    public JwtService(Utils utils) {
        this.utils = utils;
    }

    private SecretKey generateKey() {
        byte[] streams = Decoders.BASE64.decode(utils.getJwt().getSecret());
        return Keys.hmacShaKeyFor(streams);
    }

    public String generateJwt(String username) {
        return Jwts.builder().subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + utils.getJwt().getExpiry() * 60L * 1000))
                .claim("claim", "Placeholder")
                .signWith(generateKey())
                .compact();
    }

    public Claims extracttAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        Claims claims = extracttAllClaims(token);
        return claims.getSubject();
    }

    public boolean isExpired(String token) {
        Claims claims = extracttAllClaims(token);
        return claims.getExpiration().before(new Date(System.currentTimeMillis()));
    }

    public boolean verifyJwt(String token, UserDetails userDetails) {
        Claims claims = extracttAllClaims(token);
        return !isExpired(token) && claims.getSubject().equals(userDetails.getUsername());
    }
}
