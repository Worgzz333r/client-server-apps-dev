package com.example.lab03.config;

import com.example.lab03.entity.Note;
import com.example.lab03.repository.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(NoteRepository noteRepository) {
        return args -> {
            // Створюємо тестові нотатки тільки якщо база пуста
            if (noteRepository.count() == 0) {
                Note note1 = new Note();
                note1.setTitle("Перша нотатка");
                note1.setContent("Це перша тестова нотатка");

                Note note2 = new Note();
                note2.setTitle("Список покупок");
                note2.setContent("Молоко, хліб, яйця");

                Note note3 = new Note();
                note3.setTitle("Зустріч");
                note3.setContent("Зустріч з клієнтом о 14:00");

                noteRepository.save(note1);
                noteRepository.save(note2);
                noteRepository.save(note3);

                System.out.println("Створено 3 тестові нотатки");
            }
        };
    }
}