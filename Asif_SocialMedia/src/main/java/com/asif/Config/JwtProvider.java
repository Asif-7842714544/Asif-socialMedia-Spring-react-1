package com.asif.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.logging.Logger;


public class JwtProvider {

    private static SecretKey key = Keys.hmacShaKeyFor(JwtConsants.SECRET_KEY.getBytes());

    public static String generateToken(Authentication authentication) {
        String jwt = Jwts.builder()
                .setIssuer("Asif")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 1000 * 60 * 60 * 24))
                .claim("email", authentication.getName())
                .signWith(key)
                .compact();
        System.out.println("Jwt:: "+jwt);
        return jwt;
    }

    public static String getEmailFromJwtToken(String token) {
        String jwt = token.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        String email = claims.get("email").toString();
        return email;
    }

}
