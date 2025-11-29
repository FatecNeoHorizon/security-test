package com.neohorizon.demo.insecure.controller;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;


import com.neohorizon.demo.model.UserEntity;
import com.neohorizon.demo.repository.UserRepository;


@RestController
@RequestMapping("/insecure")
public class InsecureUserController {


@Autowired
private JdbcTemplate jdbcTemplate;


@Autowired
private UserRepository repository;


// 1) SQL Injection (uso direto de concatenação)
@GetMapping("/users/find")
public List<Map<String, Object>> findUserJdbc(@RequestParam String name) {
String sql = "SELECT id, name, email, secret FROM users WHERE name = '" + name + "'";
// executa SQL construído dinamicamente — vulnerável a SQLi
return jdbcTemplate.queryForList(sql);
}


// 2) Reflected XSS (retorna HTML sem escape)
@GetMapping(value = "/xss", produces = "text/html")
public String xss(@RequestParam String input) {
return "<h1>Olá " + input + "</h1>"; // sem escaping
}


// 3) Exposure of environment variables / sensitive data
@GetMapping("/debug/env")
public Map<String, String> env() {
// retorna variáveis de ambiente do sistema — inseguro
return System.getenv();
}


// 4) Hard-coded credential example (simples endpoint de login inseguro)
@PostMapping("/login")
public String login(@RequestParam String user, @RequestParam String pass) {
if ("admin".equals(user) && "admin".equals(pass)) {
return "logado";
}
return "falhou";
}


// 5) Insecure direct object reference (ID exposto sem autorização)
@GetMapping("/users/{id}")
public Optional<UserEntity> getUser(@PathVariable Long id) {
return repository.findById(id);
}
}
