package com.example.lab03.repository;

import com.example.lab03.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Репозиторій для роботи з нотатками
@Repository  // Реєструє як Spring Bean + обробка винятків
public interface NoteRepository extends JpaRepository<Note, Long> {
    // Наслідує JpaRepository - автоматичні CRUD методи
}
