package com.w4.projetoIntegrador.service;


import com.w4.projetoIntegrador.entities.User;
import com.w4.projetoIntegrador.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository repository;

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        return user;
    }


}
