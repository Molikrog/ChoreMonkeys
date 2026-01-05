package com.example.choremonkeys.web.controller;

import com.example.choremonkeys.models.Chore;
import com.example.choremonkeys.models.ChoreStatus;
import com.example.choremonkeys.models.User;
import com.example.choremonkeys.services.ChoreService;
import com.example.choremonkeys.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/chores")
public class ChoreController {

    private final ChoreService choreService;
    private final UserService userService;

    public ChoreController(ChoreService choreService, UserService userService) {
        this.choreService = choreService;
        this.userService = userService;
    }

//    @GetMapping("/")
//    public String showChores(Model model, Principal principal) {
//        List<Chore> chores = choreService.findAll();
//        User user = null;
//        if (principal != null) {
//            user = userService.findByUsername(principal.getName());
//        }
//
//        model.addAttribute("chores", chores);
//        model.addAttribute("user", user);
//        model.addAttribute("title", "Dashboard");
//        model.addAttribute("content", "dashboard");
//
//        return "layout";
//    }

    @GetMapping("/")
    public String home(){
        return "redirect:/dashboard";
    }

    @PreAuthorize("hasRole('EMPLOYER')")
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("chore", new Chore());
        model.addAttribute("title", "Add Chore");
        model.addAttribute("content", "form");
        return "layout";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Chore chore = choreService.findById(id);
        model.addAttribute("chore", chore);
        model.addAttribute("title", "Edit Chore");
        model.addAttribute("content", "form");
        return "layout";
    }

    @PreAuthorize("hasRole('EMPLOYER')")
    @PostMapping("/add")
    public String create(@RequestParam String title,
                         @RequestParam String description,
                         @RequestParam String destination,
                         @RequestParam Integer phoneNumber,
                         @RequestParam Long price) {

        choreService.createChoreWithStatus(title, description, destination, price, phoneNumber, ChoreStatus.Available);
        return "redirect:/dashboard";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String title,
                         @RequestParam String description,
                         @RequestParam String destination,
                         @RequestParam Integer phoneNumber,
                         @RequestParam Long price) {

        choreService.updateChore(id, title, description, destination, phoneNumber, price, ChoreStatus.Available);
        return "redirect:/dashboard";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        choreService.deleteChore(id);
        return "redirect:/dashboard";
    }
}
