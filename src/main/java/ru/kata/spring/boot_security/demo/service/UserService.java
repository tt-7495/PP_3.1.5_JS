package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    User findById(Long id);

    List<User> findAll();

    void updateUser(User user);

    void saveUser(User user);

    void deleteById(Long id);

    User findUserByUserName(String userName);
}
