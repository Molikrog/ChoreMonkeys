package com.example.choremonkeys.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private Long money;



    @Enumerated(EnumType.STRING)
    private UserType userType;


    public User(String username, String password, String email, Long money, UserType userType) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.money = money;
        this.userType = userType;
    }

    public User(String username, String password, String email, Long money, UserType userType, List<ChoreAssignment> choreAssignment) {
        this(username, password, email, money, userType);

    }


}
