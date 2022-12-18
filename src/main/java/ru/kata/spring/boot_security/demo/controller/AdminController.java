package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String findAll(Model model, Principal principal) {
        model.addAttribute("users", userService.findAll());
        User princ = userService.findUserByUserName(principal.getName());
        model.addAttribute("princ", princ);
        return "user-list";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user-create")
    public String createUserForm(@ModelAttribute("user") User user, Model model) {
        return "user-list";
    }

    @PatchMapping("/user-update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user-list";
    }

    @DeleteMapping("/user-delete")
    public String deleteUser(Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user-list";
    }
}
