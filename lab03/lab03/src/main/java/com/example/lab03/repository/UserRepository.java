package com.example.lab03.repository;

import com.example.lab03.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository // Spring Bean для доступу до даних
public interface UserRepository extends JpaRepository<User, Long> {
    // Кастомний метод пошуку користувача за ім'ям
    Optional<User> findByUsername(String username);
    // Spring Data JPA автоматично створює запит:
     // SELECT * FROM users WHERE username = ?
}
