package com.example.choremonkeys.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Setter
@Getter
@Table(name = "app_User")
public class User {

    @Id
    @GeneratedValue
    private Long Id;
    private String Username;
    private String Password;
    private String Email;
    private Long Money;
    @Setter
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, // Fetch chores lazily (only when accessed)
            orphanRemoval = true // Remove chores if they are no longer associated with a user
    )
    private List<Chore> chores;


    public User(String username,
                String password,
                String email,
                Long money,
                List<Chore> chores) {
        this.Username = username;
        this.Password = password;
        this.Email = email;
        this.Money = money;
        this.chores = chores;
        }

}
