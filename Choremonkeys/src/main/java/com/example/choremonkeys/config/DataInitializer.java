package com.example.choremonkeys.config;

import com.example.choremonkeys.models.*;
import com.example.choremonkeys.repository.ChoreAssignmentRepository;
import com.example.choremonkeys.repository.ChoreRepository;
import com.example.choremonkeys.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final ChoreRepository choreRepository;
    private final ChoreAssignmentRepository assignmentRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            UserRepository userRepository,
            ChoreRepository choreRepository,
            ChoreAssignmentRepository assignmentRepository, PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.choreRepository = choreRepository;
        this.assignmentRepository = assignmentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
//        if (assignmentRepository.count() == 0) {
//
//            User employer = userRepository.findByUsername("employer1")
//                    .orElseGet(() ->
//                    {
//                        User u = new User();
//                        u.setUsername("employer3");
//                        u.setEmail("employer1@gmail.com");
//                        u.setPassword(passwordEncoder.encode("password3"));
//                        u.setUserType(UserType.EMPLOYER);
//                        return userRepository.save(u);
//
//                    });
//
//            Chore chore = choreRepository.findAll().stream().findFirst().orElseGet(() -> {
//                Chore newChore = new Chore();
//                newChore.setTitle("Default Chore");
//                newChore.setDescription("Auto-generated task");
//                return choreRepository.save(newChore);
//            });
//
//            assignmentRepository.save(
//                    new ChoreAssignment(
//                            employer,
//                            chore,
//                            LocalDateTime.now().plusDays(3),
//                            ChoreStatus.Available)
//            );
//        }
        //Used for Testing purposes to automatically add chores and choreAssignments
    }
}
