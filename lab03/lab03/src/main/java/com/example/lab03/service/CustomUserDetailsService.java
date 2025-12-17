package com.example.lab03.service;

import com.example.lab03.entity.User;
import com.example.lab03.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // Репозиторій для пошуку користувачів

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Ключовий метод - знаходить користувача по логіну
        User user = userRepository.findByUsername(username) // Шукаємо в БД
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
                // Якщо не знайшли
        return user; // Повертаємо користувача, Security перевірить пароль
    }
}
