package br.com.portfolio.habittracker_api.repository;

import br.com.portfolio.habittracker_api.model.HabitRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRecordRepository extends JpaRepository<HabitRecord, Long> {
}