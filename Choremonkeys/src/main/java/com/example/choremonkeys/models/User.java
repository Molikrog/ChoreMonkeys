package com.example.choremonkeys.models;

import jakarta.persistence.*;
import lombok.*;

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
    private Long id;
    private String username;
    private String password;
    private String email;
    private Long money;
    @Setter
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, // Fetch chores lazily (only when accessed)
            orphanRemoval = true // Remove chores if they are no longer associated with a user
    )
    private List<Chore> chores;

    public User(String username, String password, String email, Long money, List<Chore> chores) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.money = money;
        this.chores = chores;
    }
}
