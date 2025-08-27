package br.com.portfolio.habittracker_api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "habits")

public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY) // Muitos hábitos podem pertencer a um usuário
    @JoinColumn(name = "user_id", nullable = false) // Chave estrangeira
    private User user;
}