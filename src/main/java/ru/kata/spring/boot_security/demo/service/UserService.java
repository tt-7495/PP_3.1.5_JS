package ru.kata.spring.boot_security.demo.service;

import jakarta.annotation.PostConstruct;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class UserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        return user;
    }

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findById(Long id) {
        return userRepository.getReferenceById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void updateUser(User user) {
        User updatedUser = userRepository.findByUsername(user.getUsername());
        updatedUser.setId(user.getId());
        updatedUser.setName(user.getName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setLogin(user.getLogin());
        updatedUser.setRoles(user.getRoles());
        userRepository.save(updatedUser);
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void deleteById(Long id) { userRepository.deleteById(id);}

    public User findUserByUserName(String userName) {
        return findAll().stream().filter(user -> user.getUsername().equals(userName)).findAny().orElse(null);
    }

    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

    @PostConstruct
    private void postConstruct() {
        Role admin = new Role(1L, "ROLE_ADMIN");
        Role user = new Role(2L, "ROLE_USER");
        roleRepository.saveAll(List.of(admin, user));

        List<Role> rolesOfAdmin = new ArrayList<>();
        List<Role> rolesOfUser = new ArrayList<>();

        Collections.addAll(rolesOfAdmin, admin, user);
        Collections.addAll(rolesOfUser, user);

        User adminUser = new User("Anatolii", "Khitrov",
                "tt-7495@mail.ru", "admin", passwordEncoder.encode("admin1"), rolesOfAdmin);
        User normalUser = new User("Alexandr", "Russkin",
                "AlexandrRusskin@mail.ru", "user", passwordEncoder.encode("user1"), rolesOfUser);

        userRepository.save(adminUser);
        userRepository.save(normalUser);
    }
}