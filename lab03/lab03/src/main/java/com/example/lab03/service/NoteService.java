package com.example.lab03.service;

import com.example.lab03.entity.Note;
import com.example.lab03.repository.NoteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository; // Залежність на репозиторій
    
    // Конструкторний dependency injection
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
    // Отримати всі нотатки
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }
    // Отримати нотатку по ID
    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }
    // Створити нову нотатку
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }
    // Оновити існуючу нотатку
    public Note updateNote(Note note) {
        return noteRepository.save(note);
    }
    // Видалити нотатку
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
