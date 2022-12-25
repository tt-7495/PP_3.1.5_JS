package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void updateUser(User user);

    void saveUser(User user);

    void deleteById(Long id);

    User findById(Long id);

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAll();
}
