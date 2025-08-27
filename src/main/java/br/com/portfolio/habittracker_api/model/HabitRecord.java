package br.com.portfolio.habittracker_api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "habit_records")
public class HabitRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "completion_date", nullable = false)
    private LocalDate completionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id", nullable = false)
    private Habit habit;
}