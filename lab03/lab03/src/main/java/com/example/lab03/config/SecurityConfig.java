package com.example.lab03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Налаштування авторизації з ролями
                .authorizeHttpRequests(authz -> authz
                        // Дозволити всім (без авторизації)
                        .requestMatchers("/", "/home", "/login", "/error",
                                "/css/**", "/js/**", "/webjars/**").permitAll()

                        // API - тільки для адмінів
                        .requestMatchers("/api/**").hasRole("ADMIN")

                        // Веб-нотатки:
                        // GET запити (перегляд) - для всіх авторизованих
                        .requestMatchers("/web/notes", "/web/notes/{id}").authenticated()
                        // POST/PUT/DELETE (створення/редагування/видалення) - тільки для адмінів
                        .requestMatchers("/web/notes/create", "/web/notes/update/**",
                                "/web/notes/delete/**").hasRole("ADMIN")

                        // Все інше потребує авторизації
                        .anyRequest().authenticated()
                )

                // Налаштування форми логіну
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/web/notes", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )

                // Налаштування логауту
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                )

                // CSRF для тестування
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}