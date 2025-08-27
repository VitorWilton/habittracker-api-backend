package br.com.portfolio.habittracker_api.dto;

import lombok.Data;

// DTO para receber dados na criação/atualização de um hábito
@Data
public class HabitRequestDTO {
    private String name;
    private String description;
    private Long userId; // Por enquanto, vamos passar o ID do usuário manualmente
}