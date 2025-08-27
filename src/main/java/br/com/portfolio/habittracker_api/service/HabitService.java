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
    private UserRepository userRepository;

    /**
     * Busca os hábitos de um usuário específico pelo seu username.
     */
    public List<Habit> getHabitsForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o nome: " + username));
        return habitRepository.findByUserId(user.getId());
    }

    public Habit createHabit(HabitRequestDTO habitRequestDTO, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o nome: " + username));

        Habit newHabit = new Habit();
        newHabit.setName(habitRequestDTO.getName());
        newHabit.setDescription(habitRequestDTO.getDescription());

        newHabit.setUser(user);

        return habitRepository.save(newHabit);
    }

    /**
     * Deleta um hábito, garantindo que ele pertence ao usuário autenticado.
     */
    public void deleteHabit(Long habitId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + username));

        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new RuntimeException("Hábito não encontrado: " + habitId));

        if (!habit.getUser().getId().equals(user.getId())) {
            throw new SecurityException("Acesso negado: Este hábito não pertence a você.");
        }

        habitRepository.delete(habit);
    }
}