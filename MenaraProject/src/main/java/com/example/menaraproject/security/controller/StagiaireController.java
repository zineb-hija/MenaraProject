package com.example.menaraproject.security.controller;

import com.example.menaraproject.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/encadrant")
@CrossOrigin("*")
public class StagiaireController {

    @Autowired
    UserService userService;

}
