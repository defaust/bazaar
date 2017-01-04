package ua.com.codespace.repository;

import ua.com.codespace.model.User;


public interface CustomUserRepository {
    User findByUsername(String username);
}
