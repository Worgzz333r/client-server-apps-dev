package com.example.lab03.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
public class Note {
    @Id // Позначає первинний ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоінкремент
    private Long id;

    private String title; // Назва нотатки

    @Column(length = 2000) // Обмеження довжини для контенту
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Конструктори
    public Note() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Гетери і сетери
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    // Title з автоматичним оновленням часу
    public String getTitle() { return title; }
    public void setTitle(String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }
    // Content з автоматичним оновленням часу
    public String getContent() { return content; }
    public void setContent(String content) {
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
