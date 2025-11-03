//package com.example.choremonkeys.web.controller;
//
//import com.example.choremonkeys.models.User;
//import com.example.choremonkeys.services.UserService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//@Controller
//@RequestMapping("/")
//public class UserController {
//
//    private final UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    // Show all users
////    @GetMapping({"/", "/users"})
////    public String showUsers(Model model) {
////        List<User> users = this.userService.findAll(); // has no user findAll() in impl
////        model.addAttribute("users", users);
////        return "user-list"; // make a template user-list.html
////    }
//
//    // Show add form
//    @GetMapping("/users/add")
//    public String showAddForm(Model model) {
//        model.addAttribute("user", new User());
//        return "user-form"; // make a template user-form.html
//    }
//
//    // Show edit form
//    @GetMapping("/users/{id}/edit")
//    public String showEditForm(@PathVariable Long id, Model model) {
//        User user = this.userService.findById(id);
//        model.addAttribute("user", user);
//        return "user-form";
//    }
//
//    // Create user
//    @PostMapping("/users")
//    public String create(@RequestParam String username,
//                         @RequestParam String password,
//                         @RequestParam String email,
//                         @RequestParam Long money) {
//        userService.createUser(username, password, email, money, null);
//        return "redirect:/users";
//    }
//
//    // Update user
//    @PostMapping("/users/{id}")
//    public String update(@PathVariable Long id,
//                         @RequestParam String username,
//                         @RequestParam String password,
//                         @RequestParam String email,
//                         @RequestParam Long money) {
//        userService.updateUser(id, username, password, email, money, List.of());
//        return "redirect:/users";
//    }
//
//    // Delete user
//    @PostMapping("/users/{id}/delete")
//    public String delete(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return "redirect:/users";
//    }
//
//
//}
