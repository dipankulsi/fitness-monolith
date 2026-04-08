package com.project.fitness.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    String jwtSecret = "YS1zdHJpbmctc2VjcmV0LWF0LWxlYXN0LTI1Ni1iaXRzLWxvbmc=";
    int jwtExpirationInMilliSec = 86400000;

    public String generateJwtToken(String userId, String roles) {
        return Jwts.builder().subject(userId).issuedAt(new Date()).claim("roles",roles).
                expiration(new Date(new Date().getTime()+ jwtExpirationInMilliSec)).
                signWith(getJwtSecretKey()).
                compact();
    }

    public String getJwtFromHeader(HttpServletRequest request){

        String bearerToken =request.getHeader("Authorization");
        if(bearerToken !=null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        else
            return null;
    }

    public boolean validateJwtToken(String token){
        try {
            Jwts.parser().verifyWith((SecretKey) getJwtSecretKey()).build().parseSignedClaims(token);
        }
        catch (Exception e){
            throw new JwtException("Invalid JWT token");
        }
        return true;
    }

    public Key getJwtSecretKey(){

        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String getUserIdFromToken(String jwt) {

        return Jwts.parser().verifyWith((SecretKey) getJwtSecretKey()).
                build().parseSignedClaims(jwt).
                getPayload().getSubject();
    }

    public Claims getAllClaimsFromToken(String jwt) {
        return Jwts.parser().verifyWith((SecretKey) getJwtSecretKey()).
                build().parseSignedClaims(jwt).
                getPayload();

    }
}
