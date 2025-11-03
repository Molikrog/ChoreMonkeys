package com.example.choremonkeys.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "app_ChoreAssignment")

public class ChoreAssignment {
    @Id
    private Long posterId;
    private Long max;
    private Long date;

    @Enumerated(EnumType.STRING)
    ChoreStatus choreStatus;

    @ManyToOne
    private Chore choreList;

    @ManyToOne
    private User userList;

    public ChoreAssignment(User userList, Chore choreList, Long date){
        this.userList = userList;
        this.choreList = choreList;
        this.date = date;
    }

}
