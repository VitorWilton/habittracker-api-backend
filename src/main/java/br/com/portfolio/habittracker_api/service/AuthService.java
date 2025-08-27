package br.com.portfolio.habittracker_api.service;

import br.com.portfolio.habittracker_api.dto.RegisterDTO;
import br.com.portfolio.habittracker_api.model.User;
import br.com.portfolio.habittracker_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injetamos o codificador que criamos

    public User register(RegisterDTO registerDTO) {
        // Lógica para verificar se o usuário já existe pode ser adicionada aqui

        User newUser = new User();
        newUser.setUsername(registerDTO.getUsername());
        newUser.setEmail(registerDTO.getEmail());
        // Criptografando a senha antes de salvar!
        newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        return userRepository.save(newUser);
    }
}