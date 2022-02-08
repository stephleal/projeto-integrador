package com.w4.projetoIntegrador.unitTests;


import com.w4.projetoIntegrador.service.TokenService;
import com.w4.projetoIntegrador.entities.User;
import com.w4.projetoIntegrador.enums.ProfileTypes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;

public class TokenTest {

    private User user = User.builder().username("Buyer").password("buyer").enabled(true).profileType(ProfileTypes.BUYER).build();

    @Test
    public void deveCriarUmTokenValido() {
        TokenService tokenService = new TokenService("86400", "estaehnossasecret");

        String token = tokenService.createToken(user);

        assertThat(tokenService.tokenIsValid(token)).isTrue();

    }

    @Test
    public void deveRetornarUsername() {
        TokenService tokenService = new TokenService("86400000", "estaehnossasecret");

        String token = tokenService.createToken(user);

        assertThat(tokenService.getUsername(token)).isEqualTo(user.getUsername());
    }

    @Test
    public void deveCriarUmTokenComUmDiaDeExpiracao() {
        TokenService tokenService = new TokenService("86400000", "estaehnossasecret");

        LocalDateTime beforeDate = now().plusDays(1).minusMinutes(5);

        String token = tokenService.createToken(user);

        LocalDateTime afterDate = now().plusDays(1).plusMinutes(5);
        assertThat((getExpirationToken(token)))
                .isBetween(
                        Date.from(beforeDate.atZone(ZoneId.systemDefault()).toInstant()),
                        Date.from(afterDate.atZone(ZoneId.systemDefault()).toInstant()));

    }

    public Date getExpirationToken(String token) {
        Jws<Claims> jwsClaims = Jwts.parser().setSigningKey("estaehnossasecret").parseClaimsJws(token);
        Claims body = jwsClaims.getBody();
        return body.getExpiration();
    }
}
