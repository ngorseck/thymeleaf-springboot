package com.samanecorp.thymeleaf1.auth;

import com.samanecorp.thymeleaf1.dao.IUserDao;
import com.samanecorp.thymeleaf1.entity.UserEntity;
import com.samanecorp.thymeleaf1.exception.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserDao repository;

    public UserDetailsServiceImpl(IUserDao repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {

        UserEntity user = repository.findByEmail(email).orElseThrow(() ->
                new EntityNotFoundException(String.format("User does not exist, email: %s", email)));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
