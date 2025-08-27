package br.com.portfolio.habittracker_api.controller;

import br.com.portfolio.habittracker_api.dto.RegisterDTO;
import br.com.portfolio.habittracker_api.model.User;
import br.com.portfolio.habittracker_api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO) {
        authService.register(registerDTO);
        return ResponseEntity.ok("User registered successfully!");
    }
}