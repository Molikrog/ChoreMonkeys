package com.example.choremonkeys;

import com.example.choremonkeys.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChoremonkeysApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChoremonkeysApplication.class, args);
    }


//    @Bean
//    CommandLineRunner migratePasswords(UserService userService) {
//        return args -> {
//            userService.migratePlainTextPasswords();
//        };
//    }

}
