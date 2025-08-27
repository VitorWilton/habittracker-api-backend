package br.com.portfolio.habittracker_api.repository;

import br.com.portfolio.habittracker_api.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findByUserId(Long userId);
}