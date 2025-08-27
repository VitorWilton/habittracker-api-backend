package br.com.portfolio.habittracker_api.dto;

import lombok.Data;

@Data
public class HabitRequestDTO {
    private String name;
    private String description;
    private Long userId;
}