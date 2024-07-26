package com.example.menaraproject.security.controller;

import com.example.menaraproject.security.jwt.JwtResponse;
import com.example.menaraproject.security.model.User;
import com.example.menaraproject.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
    public JwtResponse signIn(@RequestBody User user) {
        return userService.signIn(user);
    }
}
