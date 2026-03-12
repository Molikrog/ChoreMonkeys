package com.example.choremonkeys.web.controller;


import com.example.choremonkeys.models.ChoreAssignment;
import com.example.choremonkeys.models.ChoreStatus;
import com.example.choremonkeys.models.User;
import com.example.choremonkeys.models.UserType;
import com.example.choremonkeys.services.ChoreAssignmentService;
import com.example.choremonkeys.services.ChoreService;
import com.example.choremonkeys.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class AppController {

    private final ChoreService choreService;
    private final ChoreAssignmentService choreAssignmentService;
    private final UserService userService;

    public AppController(ChoreService choreService, ChoreAssignmentService choreAssignmentService, UserService userService) {
        this.choreService = choreService;
        this.choreAssignmentService = choreAssignmentService;
        this.userService = userService;
    }

    //---- Dashboard --------------------------------

    @GetMapping({"/dashboard", "/"})
    public String dashboard(Model model, Principal principal) {

        // always added regardless of login status
        model.addAttribute("title", "Dashboard");
        model.addAttribute("content", "dashboard");
        model.addAttribute("choreAssignment", choreAssignmentService.findAll());
        model.addAttribute("chores", choreService.findAll());



        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            model.addAttribute("user", user);

            if (user.getUserType() == UserType.WORKER) {
                model.addAttribute("myChores", choreAssignmentService.findByWorker(user.getId()));
            }
            if (user.getUserType() == UserType.EMPLOYER) {
                model.addAttribute("myPostedChores", choreAssignmentService.findByEmployer(user.getId()));
            }
        }

        return "layout";
    }
    //----RoleSwitch-----------------------------------
    @PostMapping("/profile/switch-role")
    public String switchRole(Principal principal, HttpServletRequest request) {
        User user = userService.findByUsername(principal.getName());
        if (user.getUserType() == UserType.WORKER) {
            user.setUserType(UserType.EMPLOYER);
        } else {
            user.setUserType(UserType.WORKER);
        }
        userService.updateUserType(user.getId(), user.getUserType());
        request.getSession().invalidate();


        return "redirect:/dashboard";
    }

    //----ChoreAssignment----------------------------

    @PostMapping("/dashboard/assignmentCreate")
    public String createAssignment(Principal principal,
                                   @RequestParam Long choreId,
                                   @RequestParam String deadline,
                                   @RequestParam Long max) {
        User user = userService.findByUsername(principal.getName());

        choreAssignmentService.createAssignment(user.getId(), choreId, LocalDateTime.parse(deadline), max);

        return "redirect:/dashboard";

    }

    @PostMapping("/dashboard/assignment/{id}")
    public String editAssignment(Principal principal,
                                 @PathVariable Long id,
                                 @RequestParam Long choreId,
                                 @RequestParam String deadline,
                                 @RequestParam Long max) {
        User user = userService.findByUsername(principal.getName());

        choreAssignmentService.updateAssignment(id, user.getId(), choreId, LocalDateTime.parse(deadline), max);

        return "redirect:/dashboard";

    }

    //----Chore----------------------------

    @PostMapping("/dashboard/choreCreate")
    public String createChore(     Principal principal,
                                   @RequestParam String title,
                                   @RequestParam String description,
                                   @RequestParam String destination,
                                   @RequestParam Long phoneNumber,
                                   @RequestParam int price,
                                   @RequestParam (required = false) Double longitude,
                                   @RequestParam (required = false) Double latitude) {



        User employer = userService.findByUsername(principal.getName());
        choreService.createChoreWithStatus(title, description, destination, phoneNumber, price, ChoreStatus.Available, employer, longitude, latitude);

        return "redirect:/dashboard";

    }

    @PostMapping("/dashboard/chore/{id}")
    public String editChore(       Principal principal,
                                   @PathVariable Long id,
                                   @RequestParam String title,
                                   @RequestParam String description,
                                   @RequestParam String destination,
                                   @RequestParam Long phoneNumber,
                                   @RequestParam int price,
                                   @RequestParam (required = false) Double longitude,
                                   @RequestParam (required = false) Double latitude) {

        choreService.updateChore(id,title, description, destination, phoneNumber, price, ChoreStatus.Available, longitude, latitude);

        return "redirect:/dashboard";

    }

    //----User----------------------------



    @PostMapping("/assignments/{id}/accept")
    public String accept(@PathVariable Long id, Principal principal) {

        User worker = userService.findByUsername(principal.getName());
        choreAssignmentService.acceptChore(id, worker.getId());
        return "redirect:/dashboard";
    }

    @PostMapping("/assignments/{id}/complete")
    public String complete(@PathVariable Long id, Principal principal) {
        User worker = userService.findByUsername(principal.getName());
        choreAssignmentService.completeChore(id, worker.getId());
        return "redirect:/dashboard";
    }

    @PostMapping("/assignments/{id}/approve")
    public String approve(@PathVariable Long id, Principal principal) {

        User worker = userService.findByUsername(principal.getName());
        choreAssignmentService.approveAndPay(id, worker.getId());
        return "redirect:/dashboard";
    }

    @PostMapping("/assignments/{id}/cancel")
    public String cancel(@PathVariable Long id, Principal principal) {
        User worker = userService.findByUsername(principal.getName());
        choreAssignmentService.cancel(id);
        return "redirect:/dashboard";
    }

    @PostMapping("/assignments/{id}/remove-worker")
    public String removeWorker(@PathVariable Long id, Principal principal) {
        User employer = userService.findByUsername(principal.getName());
        choreAssignmentService.removeWorker(id, employer.getId());
        return "redirect:/dashboard";
    }

    @PostMapping("/profile/add-money")
    public String addMoney(Principal principal, @RequestParam int amount) {
        User user = userService.findByUsername(principal.getName());
        userService.addMoney(user.getId(), amount);
        return "redirect:/dashboard";
    }

}