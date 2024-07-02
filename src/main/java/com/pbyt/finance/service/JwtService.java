package com.pbyt.finance.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {
    private static final String SECRET = "faqAqZcxL26aNfhfaym0EnmAWwxLDtMl/VclGa+qW6jS3/GEurfXj1z6SRrcFdf6";
    private Key getSignKey() {
        byte[] keyBytes = SECRET.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String extractId(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public String extractAud(String token) {
        return extractClaim(token, Claims::getAudience);
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private String createToken(Map<String, Object> claims, String id, String aud) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(id)
                .setAudience(aud)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 600))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }
    public String GenerateToken(String id,String aud){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, id,aud);
    }


}
