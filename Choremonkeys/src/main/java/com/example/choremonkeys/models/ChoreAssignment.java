package com.example.choremonkeys.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "app_chore_assignment")

public class ChoreAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long max;
    private LocalDateTime localDateTime;


    @Enumerated(EnumType.STRING)
    ChoreStatus choreStatus;

    @ManyToOne (optional = false)
    private Chore chore;

    @ManyToOne (optional = false)
    private User employer;

    @ManyToOne
    private User worker;


    public ChoreAssignment ( User employerList, Chore choreList, LocalDateTime deadline, ChoreStatus choreStatus){
        this.employer = employerList;
        this.chore = choreList;
        this.localDateTime = deadline;
        this.choreStatus = choreStatus;
    }



}
