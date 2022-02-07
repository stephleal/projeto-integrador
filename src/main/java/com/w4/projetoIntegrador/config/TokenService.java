package com.w4.projetoIntegrador.config;

import com.w4.projetoIntegrador.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class TokenService {

        @Value("${jwt.expiration}")
        private String expiration;

        @Value("${jwt.secret}")
        private String secret;


        public String createToken(User user) {
            Date today = new Date();
            Long exp = new Long(expiration);
            Date expirated = new Date(today.getTime()+exp);

            return Jwts.builder()
                    .setIssuer("Team 11")
                    .setSubject(user.getUser())
                    .addClaims(new HashMap<>() {{
                        put("profileType", user.getProfileType());
                    }})
                    .setIssuedAt(today)
                    .setExpiration(expirated)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();
        }


        public boolean validToken(String token) {
            try {
                Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
                return true;
            }catch(Exception e) {
                return false;
            }
        }

        public String getUsername(String token) {
            Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            Claims body = jwsClaims.getBody();
            return body.getSubject();
        }

    }

