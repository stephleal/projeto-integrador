package com.w4.projetoIntegrador.config;

import com.w4.projetoIntegrador.entities.User;
import com.w4.projetoIntegrador.exceptions.UserDoesNotExistException;
import com.w4.projetoIntegrador.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthenticationByTokenFilter extends OncePerRequestFilter {


    private TokenService tokenService;

    private UserRepository repository;

    public AuthenticationByTokenFilter(TokenService tokenService, UserRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = takenToken(request);

        boolean tokenIsValid = tokenService.tokenIsValid(token);

        if (tokenIsValid) {
            springAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }

    private void springAuthentication(String token) {
        String userName = tokenService.getUsername(token);
        User user = this.repository.findById(userName).orElseThrow(UserDoesNotExistException::new);
        List<SimpleGrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority(user.getProfileType()));
        SecurityContextHolder
                .getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(user, null, authorities));
    }

    private String takenToken(HttpServletRequest request) {
        String token = "";
        String bearer = "Bearer ";
        String authorization = request.getHeader("Authorization");
        if (authorization == null || authorization.isEmpty() || !authorization.startsWith(bearer)) {
            return null;
        } else {
            token = authorization.split(bearer)[1];
        }
        return token;
    }

}

