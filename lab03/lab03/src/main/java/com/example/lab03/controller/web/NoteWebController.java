package com.example.lab03.controller.web;

import com.example.lab03.entity.Note;
import com.example.lab03.service.NoteService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/web/notes")
public class NoteWebController {

    private final NoteService noteService;

    public NoteWebController(NoteService noteService) {
        this.noteService = noteService;
    }

    // Перегляд всіх нотаток - для всіх авторизованих
    @GetMapping
    public String getAllNotes(Model model) {
        List<Note> notes = noteService.getAllNotes();
        model.addAttribute("notes", notes);
        return "notes/list";
    }

    // Перегляд однієї нотатки - для всіх авторизованих
    @GetMapping("/{id}")
    public String getNoteById(@PathVariable Long id, Model model) {
        Note note = noteService.getNoteById(id);
        model.addAttribute("note", note);
        return "notes/detail";
    }

    // Форма створення - тільки для адмінів
    @GetMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public String showCreateForm(Model model) {
        model.addAttribute("note", new Note());
        return "notes/create";
    }

    // Створення нотатки - тільки для адмінів
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public String createNote(@ModelAttribute Note note) {
        noteService.createNote(note);
        return "redirect:/web/notes";
    }

    // Форма редагування - тільки для адмінів
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showEditForm(@PathVariable Long id, Model model) {
        Note note = noteService.getNoteById(id);
        model.addAttribute("note", note);
        return "notes/edit";
    }

    // Оновлення нотатки - тільки для адмінів
    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateNote(@PathVariable Long id, @ModelAttribute Note note) {
        note.setId(id);
        noteService.updateNote(note);
        return "redirect:/web/notes";
    }

    // Видалення нотатки - тільки для адмінів
    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return "redirect:/web/notes";
    }
}