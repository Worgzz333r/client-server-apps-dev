package com.example.lab03.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // Веб-контролер для рендерингу HTML
public class LoginController {

    @GetMapping("/login")
    public String login(
            // Отримання параметрів URL
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {

        if (error != null) {
            model.addAttribute("error", "Невірний логін або пароль");
        }

        if (logout != null) {
            model.addAttribute("message", "Ви вийшли з системи");
        }

        return "login"; // Шаблон /templates/login.html
    }
}
