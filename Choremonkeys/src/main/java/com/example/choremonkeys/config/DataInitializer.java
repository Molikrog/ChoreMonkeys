package com.example.choremonkeys.config;

import com.example.choremonkeys.models.Chore;
import com.example.choremonkeys.models.User;
import com.example.choremonkeys.services.ChoreService;
import com.example.choremonkeys.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataInitializer {

    private final UserService userService;
    private final ChoreService choreService;

    public DataInitializer(UserService userService, ChoreService choreService) {
        this.userService = userService;
        this.choreService = choreService;
    }

    @PostConstruct
    public void initData() {
        // Create one user
        User user = userService.createUser(
                "testuser",
                "password123",
                "testuser@example.com",
                100L,
                new ArrayList<>() // Empty list of chores
        );

        // Create one chore assigned to the user
        choreService.createChore(
                "Test Chore",
                "This is a test chore.",
                "Home",
                1,
                50L,
                user.getId()
        );
    }
}