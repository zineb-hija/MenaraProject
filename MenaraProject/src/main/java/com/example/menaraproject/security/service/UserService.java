package com.example.menaraproject.security.service;

import com.example.menaraproject.security.jwt.JwtResponse;
import com.example.menaraproject.security.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;

public interface UserService extends UserDetailsService {
    JwtResponse signIn(User user);
    User save(User user);
    List<User> findAll();

    Collection<User> findByRole(String role);

    int deleteByUsername(String username);



}