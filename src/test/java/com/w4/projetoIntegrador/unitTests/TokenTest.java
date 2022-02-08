package com.w4.projetoIntegrador.unitTests;


import com.w4.projetoIntegrador.config.TokenService;
import com.w4.projetoIntegrador.entities.User;
import com.w4.projetoIntegrador.enums.ProfileTypes;
import com.w4.projetoIntegrador.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TokenTest {

    private User user = User.builder().username("Buyer").password("buyer").enabled(true).profileType(ProfileTypes.BUYER).build();

    @Test
    public void deveCriarUmToken(){

        TokenService tokenService = new TokenService("86400", "estaehnossasecret");

        String token = tokenService.createToken(user);

        Assertions.assertThat(tokenService.tokenIsValid(token)).isTrue();
    }
}
