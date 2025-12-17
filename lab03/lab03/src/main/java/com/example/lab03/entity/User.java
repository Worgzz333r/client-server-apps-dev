package com.example.lab03.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {

    // Константи для ролей
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Id // Первинний ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоінкремент
    private Long id;

    @Column(unique = true, nullable = false) // Унікальний логін
    private String username;

    @Column(nullable = false) // Пароль
    private String password;

    @Column(nullable = false)
    private String role = ROLE_USER; // За замовчуванням USER

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Повертає роль як право доступу
        return List.of(new SimpleGrantedAuthority(role));
    }

    // Статуси акаунтів
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
