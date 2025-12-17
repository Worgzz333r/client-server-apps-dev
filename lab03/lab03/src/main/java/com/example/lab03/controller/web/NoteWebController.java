package com.example.lab03.controller.web;

import com.example.lab03.entity.Note;
import com.example.lab03.service.NoteService;
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

    @GetMapping
    public String getAllNotes(Model model) {
        List<Note> notes = noteService.getAllNotes();
        model.addAttribute("notes", notes);
        return "notes/list";
    }

    @GetMapping("/{id}")
    public String getNoteById(@PathVariable Long id, Model model) {
        Note note = noteService.getNoteById(id);
        model.addAttribute("note", note);
        return "notes/detail";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("note", new Note());
        return "notes/create";
    }

    @PostMapping("/create")
    public String createNote(@ModelAttribute Note note) {
        noteService.createNote(note);
        return "redirect:/web/notes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Note note = noteService.getNoteById(id);
        model.addAttribute("note", note);
        return "notes/edit";
    }

    @PostMapping("/update/{id}")
    public String updateNote(@PathVariable Long id, @ModelAttribute Note note) {
        note.setId(id);
        noteService.updateNote(note);
        return "redirect:/web/notes";
    }

    @PostMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return "redirect:/web/notes";
    }
}