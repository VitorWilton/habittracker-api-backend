package br.com.portfolio.habittracker_api.model;

import jakarta.persistence.*;
import lombok.Data; // Do Lombok, para getters, setters, etc.

@Data
@Entity
@Table(name = "users") // Nome da tabela no banco
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;
}