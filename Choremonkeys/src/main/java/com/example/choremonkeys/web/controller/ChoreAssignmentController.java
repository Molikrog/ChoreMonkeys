package com.example.choremonkeys.web.controller;

import com.example.choremonkeys.models.ChoreStatus;
import com.example.choremonkeys.models.User;
import com.example.choremonkeys.services.ChoreAssignmentService;
import com.example.choremonkeys.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/assignments")
public class ChoreAssignmentController {

    private final ChoreAssignmentService choreAssignmentService;
    private final UserService userService;

    public ChoreAssignmentController(ChoreAssignmentService choreAssignmentService, UserService userService) {
        this.choreAssignmentService = choreAssignmentService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public String create(Principal principal,
                         @RequestParam Long choreId,
                         @RequestParam String deadline) {

        User employer = userService.findByUsername(principal.getName());
        choreAssignmentService.createAssignment(
                employer.getId(),
                choreId,
                LocalDateTime.parse(deadline)
        );

        return "redirect:/dashboard?userId=" + employer.getId();
    }

    @PostMapping("/{id}/accept")
    public String accept(@PathVariable Long id,
                         @RequestParam Long workerId) {

        choreAssignmentService.acceptChore(id, workerId);

        return "redirect:/dashboard?userId=" + workerId;
    }

    @PostMapping("/{id}/complete")
    public String complete(@PathVariable Long id,
                           @RequestParam Long workerId) {

        choreAssignmentService.completeChore(id, workerId);

        return "redirect:/dashboard?userId=" + workerId;
    }

    @PostMapping("/{id}/approve")
    public String approve(@PathVariable Long id,
                          @RequestParam Long employerId) {

        choreAssignmentService.approveAndPay(id, employerId);

        return "redirect:/dashboard?userId=" + employerId;
    }

    @PostMapping("/{id}/cancel")
    public String cancel(@PathVariable Long id,
                         @RequestParam Long employerId) {

        choreAssignmentService.cancel(id);

        return "redirect:/dashboard?userId=" + employerId;
    }

    @PostMapping("/{id}/remove-worker")
    public String removeWorker(@PathVariable Long id,
                               @RequestParam Long employerId) {

        choreAssignmentService.removeWorker(id, employerId);

        return "redirect:/dashboard?userId=" + employerId;
    }
}
