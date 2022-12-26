package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;


import java.util.HashSet;
import java.util.Set;

@Service

public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    public Set<Role> findAll() {
        return new HashSet<>(roleRepository.findAll());
    }
}

