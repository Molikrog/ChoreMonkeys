package com.example.choremonkeys.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
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



    @Enumerated(EnumType.STRING)
    private UserType userType;
    @OneToMany (mappedBy = "user")
    private List<ChoreAssignment> choreAssignment = new ArrayList<>();

    public User(String username, String password, String email, Long money, UserType userType) {
        this.Username = username;
        this.Password = password;
        this.Email = email;
        this.Money = money;
        this.userType = userType;
    }

    public User(String username, String password, String email, Long money, UserType userType, List<ChoreAssignment> choreAssignment) {
        this(username, password, email, money, userType);
        if(choreAssignment != null) {
            choreAssignment.forEach(this::addChoreAssignment);
        }
    }

    public void addChoreAssignment (ChoreAssignment choreAssignment){
        this.choreAssignment.add(choreAssignment);
        choreAssignment.setUserList(this);
    }

    public void removeChoreAssignment (ChoreAssignment choreAssignment){
        this.choreAssignment.remove(choreAssignment);
        choreAssignment.setUserList(null);
    }


}
