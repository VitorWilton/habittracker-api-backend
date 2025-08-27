package br.com.portfolio.habittracker_api.controller;

import br.com.portfolio.habittracker_api.dto.HabitRequestDTO;
import br.com.portfolio.habittracker_api.model.Habit;
import br.com.portfolio.habittracker_api.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
public class HabitController {

    @Autowired
    private HabitService habitService;

    @PostMapping
    public ResponseEntity<Habit> createHabit(@RequestBody HabitRequestDTO habitRequestDTO) {
        Habit createdHabit = habitService.createHabit(habitRequestDTO);
        return ResponseEntity.ok(createdHabit);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Habit>> getHabitsByUser(@PathVariable Long userId) {
        List<Habit> habits = habitService.getHabitsByUserId(userId);
        return ResponseEntity.ok(habits);
    }

    @DeleteMapping("/{habitId}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long habitId) {
        habitService.deleteHabit(habitId);
        return ResponseEntity.noContent().build();
    }
}