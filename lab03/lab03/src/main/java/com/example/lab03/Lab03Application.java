package com.example.lab03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab03Application {
	
	// Точка входу в додаток
	public static void main(String[] args) {
		// Запускає Spring Boot додаток
		SpringApplication.run(Lab03Application.class, args);
		// Після цього:
		// 1. Завантажується Tomcat сервер
		// 2. Підключається база даних
		// 3. Реєструються всі @Bean
		// 4. Додаток доступний на localhost:8080
	}
}
