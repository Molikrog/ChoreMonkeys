package com.example.choremonkeys.web.controller;

import org.springframework.ui.Model;
import com.example.choremonkeys.models.Chore;
import com.example.choremonkeys.services.ChoreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ChoreController {

    private final ChoreService choreService;

    public ChoreController(ChoreService choreService) {
        this.choreService = choreService;
    }

    // show all chores
    @GetMapping({"/", "/chores"})
    public String showChores(Model model) {
        List<Chore> chores = this.choreService.findAll();
        model.addAttribute("chores", chores);

        return "list";
    }


    // show add form
    @GetMapping("/chores/add")
    public String showAddForm(Model model) {
        model.addAttribute("chore", new Chore());
        return "form";
    }


    //show edit form
//    @GetMapping("/chores/{id}/edit")
//    public String showEditForm(@PathVariable Long id, Model model) {
//        Chore chore = this.choreService.findById(id);
//        model.addAttribute("chore", chore);
//        return "form";
//    }


    //create chore
    @PostMapping("/chores")
    public String create(@RequestParam String title,
                         @RequestParam String description,
                         @RequestParam String destination,
                         @RequestParam Integer phoneNumber,
                         @RequestParam Long price) {
        // For now, hardcode user ID = 1
        choreService.createChore(title, description, destination, phoneNumber, price, 1L);
        return "redirect:/chores";
    }

    //update chore
    @PostMapping("/chores/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String title,
                         @RequestParam String description,
                         @RequestParam String destination,
                         @RequestParam Integer phoneNumber,
                         @RequestParam Long price) {
        // Hardcode user ID = 1 for now
        choreService.update(id, title, description, destination, phoneNumber, price, 1L);
        return "redirect:/chores";
    }


    //delete chore
    @PostMapping("/chores/{id}/delete")
    public String delete(@PathVariable Long id) {
        choreService.delete(id);
        return "redirect:/chores";
    }


}
