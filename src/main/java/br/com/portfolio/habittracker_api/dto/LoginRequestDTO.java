package br.com.portfolio.habittracker_api.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String password;
}