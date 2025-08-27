package br.com.portfolio.habittracker_api.controller;

import br.com.portfolio.habittracker_api.dto.HabitRequestDTO;
import br.com.portfolio.habittracker_api.model.Habit;
import br.com.portfolio.habittracker_api.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits") // O caminho base para todos os métodos de hábitos
public class HabitController {

    @Autowired
    private HabitService habitService;

    /**
     * Endpoint para criar um novo hábito para o usuário autenticado.
     * Rota: POST /api/habits
     */
    @PostMapping
    public ResponseEntity<Habit> createHabit(@RequestBody HabitRequestDTO habitRequestDTO, Authentication authentication) {
        String username = authentication.getName();
        Habit createdHabit = habitService.createHabit(habitRequestDTO, username);
        return ResponseEntity.ok(createdHabit);
    }

    /**
     * Endpoint para listar todos os hábitos do usuário autenticado.
     * Rota: GET /api/habits
     */
    @GetMapping
    public ResponseEntity<List<Habit>> getHabitsForCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        List<Habit> habits = habitService.getHabitsForUser(username);
        return ResponseEntity.ok(habits);
    }

    /**
     * Endpoint para deletar um hábito específico do usuário autenticado.
     * Rota: DELETE /api/habits/{habitId}
     */
    @DeleteMapping("/{habitId}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long habitId, Authentication authentication) {
        String username = authentication.getName();
        habitService.deleteHabit(habitId, username);
        return ResponseEntity.noContent().build();
    }
}