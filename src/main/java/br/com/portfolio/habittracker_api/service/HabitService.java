package br.com.portfolio.habittracker_api.service;

import br.com.portfolio.habittracker_api.dto.HabitRequestDTO;
import br.com.portfolio.habittracker_api.model.Habit;
import br.com.portfolio.habittracker_api.model.User;
import br.com.portfolio.habittracker_api.repository.HabitRepository;
import br.com.portfolio.habittracker_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitService {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private UserRepository userRepository; // Precisamos para associar o hábito ao usuário

    public List<Habit> getHabitsByUserId(Long userId) {
        return habitRepository.findByUserId(userId);
    }

    public Habit createHabit(HabitRequestDTO habitRequestDTO) {
        // Busca o usuário no banco. Se não existir, lança uma exceção.
        User user = userRepository.findById(habitRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Habit newHabit = new Habit();
        newHabit.setName(habitRequestDTO.getName());
        newHabit.setDescription(habitRequestDTO.getDescription());
        newHabit.setUser(user);

        return habitRepository.save(newHabit);
    }

    public void deleteHabit(Long habitId) {
        habitRepository.deleteById(habitId);
    }
}