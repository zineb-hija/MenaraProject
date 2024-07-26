package com.example.menaraproject.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.menaraproject.security.model.User;


import java.util.Collection;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User deleteByUsername(String username);

    Collection<User> findByRole(String role);
}