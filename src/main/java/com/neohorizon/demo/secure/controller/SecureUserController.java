package com.neohorizon.demo.secure.controller;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;


import com.neohorizon.demo.model.UserEntity;
import com.neohorizon.demo.repository.UserRepository;


@RestController
@RequestMapping("/secure")
public class SecureUserController {


    @Autowired
    private UserRepository repository;


    // 1) Safe query via JPA (no SQL injection)
    @GetMapping("/users/find")
    public List<UserEntity> findUser(@RequestParam String name) {
    return repository.findByName(name);
    }


    // 2) XSS safe (escape)
    @GetMapping(value = "/xss", produces = "text/html")
    public String secureXss(@RequestParam String input) {
    return "<h1>Olá " + HtmlUtils.htmlEscape(input) + "</h1>";
    }


    // 3) Do not expose environment data; return sanitized / limited info
    @GetMapping("/info")
    public Map<String, String> info() {
    Map<String, String> m = new HashMap<>();
    m.put("app", "Security Lab");
    m.put("version", "0.0.1");
    return m;
    }


    // 4) Protected user lookup — requires authentication (see SecurityConfig)
    @GetMapping("/users/{id}")
    public Optional<UserEntity> getUser(@PathVariable Long id) {
    return repository.findById(id);
    }
}
