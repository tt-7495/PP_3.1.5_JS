package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Set;

public interface RoleService {
    void save(Role role);

    Role findById(Long id);

    Role findByName(String name);

    Set<Role> findAll();
}
