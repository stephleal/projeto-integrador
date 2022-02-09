package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class TokenService {


    private final String expiration;
    private final String secret;

    public TokenService(@Value("${jwt.expiration}") String expiration, @Value("${jwt.secret}") String secret) {
        this.expiration = expiration;
        this.secret = secret;
    }


    public String createToken(User user) {
        Date today = new Date();
        Long exp = new Long(expiration);
        Date expirated = new Date(today.getTime()+exp);

        return Jwts.builder()
                .setIssuer("Team 11")
                .setSubject(user.getUsername())
                .addClaims(new HashMap<>() {{
                    put("profileType", user.getProfileType());
                }})
                .setIssuedAt(today)
                .setExpiration(expirated)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


    public boolean tokenIsValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsername(String token) {
        Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
        Claims body = jwsClaims.getBody();
        return body.getSubject();
    }

}

