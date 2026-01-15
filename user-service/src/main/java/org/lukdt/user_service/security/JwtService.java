package org.lukdt.user_service.security;

import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
  @Value("${JWT_SECRET_KEY}")
  private final String JWT_SECRET_KEY;   

  public String generateToken(String email) {
    return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
            .signWith(
              Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes()),
              SignatureAlgorithm.Hs256)
            .compact();
  }

  public String extractUsername(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes()))
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
  }
}
