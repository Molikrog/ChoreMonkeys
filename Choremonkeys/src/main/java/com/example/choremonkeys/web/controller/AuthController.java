package com.example.choremonkeys.web.controller;

import com.example.choremonkeys.models.User;
import com.example.choremonkeys.models.UserType;
import com.example.choremonkeys.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {

        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String email,
                               @RequestParam UserType userType,
                               RedirectAttributes redirectAttributes) {
        if(userService.existsByUsername(username)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Username already exists!");
            return "redirect:/register";
        }
        if(userService.existsByEmail(email)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Email already exists!");
            return "redirect:/register";
        }

        userService.createUser(username, password, email, 0L, userType);
        redirectAttributes.addFlashAttribute("successMessage", "Registration successful! You can now log in.");
        return "redirect:/login";
    }

}