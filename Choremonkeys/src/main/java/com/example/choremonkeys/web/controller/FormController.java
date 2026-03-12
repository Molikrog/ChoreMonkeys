package com.example.choremonkeys.web.controller;

import com.example.choremonkeys.models.Chore;
import com.example.choremonkeys.models.User;
import com.example.choremonkeys.services.ChoreAssignmentService;
import com.example.choremonkeys.services.ChoreService;
import com.example.choremonkeys.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class FormController {

    private final ChoreAssignmentService choreAssignmentService;
    private final ChoreService choreService;
    private final UserService userService;

    public FormController(ChoreAssignmentService choreAssignmentService, ChoreService choreService, UserService userService) {
        this.choreAssignmentService = choreAssignmentService;
        this.choreService = choreService;
        this.userService = userService;
    }


    @GetMapping("/dashboard/assignmentCreate")
    public String showAssignmentCreate(Model model, Principal principal) {
        User employer = userService.findByUsername(principal.getName());

        model.addAttribute("assignment", null);
        model.addAttribute("chores", choreService.findByEmployer(employer.getId()));

        model.addAttribute("title",     "Create Assignment");

        return "choreAssignmentForm";
    }


    @GetMapping("/dashboard/assignment/{id}")
    public String showAssignmentEdit(Model model, Principal principal,
                                     @PathVariable Long id) {
        User employer = userService.findByUsername(principal.getName());

        model.addAttribute("assignment", choreAssignmentService.findById(id));
        model.addAttribute("chores", choreService.findByEmployer(employer.getId()));

        model.addAttribute("title", "Edit Assignment");

        return "choreAssignmentForm";
    }

    @GetMapping("/dashboard/choreCreate")
    public String showChoreCreate(Model model, Principal principal) {

        model.addAttribute("chore", new Chore());
        model.addAttribute("title", "Add Chore");


        return "choreForm";
    }

    @GetMapping("/dashboard/chore/{id}")
    public String showChoreEdit(Model model, Principal principal,
                                @PathVariable Long id) {

        model.addAttribute("chore", choreService.findById(id));

        model.addAttribute("title", "Edit Chore");
        return "choreForm";
    }


}
