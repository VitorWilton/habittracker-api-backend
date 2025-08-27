package br.com.portfolio.habittracker_api.repository;

import br.com.portfolio.habittracker_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}