package com.example.choremonkeys.web.controller;


import com.example.choremonkeys.models.User;
import com.example.choremonkeys.models.UserType;
import com.example.choremonkeys.services.ChoreAssignmentService;
import com.example.choremonkeys.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class DashboardController {

    private final UserService userService;
    private final ChoreAssignmentService choreAssignmentService;

    public DashboardController(UserService userService,
                               ChoreAssignmentService choreAssignmentService) {
        this.userService = userService;
        this.choreAssignmentService = choreAssignmentService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {

        model.addAttribute("chores",
                choreAssignmentService.findAll());

        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            model.addAttribute("user", user);

            if (user.getUserType() == UserType.WORKER) {
                model.addAttribute("myChores",
                        choreAssignmentService.findByWorker(user.getId()));
            }

            if (user.getUserType() == UserType.EMPLOYER) {
                model.addAttribute("myPostedChores",
                        choreAssignmentService.findByEmployer(user.getId()));
            }
        }

        model.addAttribute("title", "Dashboard");
        model.addAttribute("content", "dashboard");

        return "layout";
    }
}





