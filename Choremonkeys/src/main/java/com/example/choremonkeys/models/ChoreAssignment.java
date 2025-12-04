package com.example.choremonkeys.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING)
    ChoreStatus choreStatus;

    @ManyToOne
    private Chore choreList;

    @ManyToOne
    private User employerList;

    @ManyToOne
    private User workerList;


    public ChoreAssignment ( User employerList, Chore choreList, LocalDateTime deadline){
        this.employerList = employerList;
        this.choreList = choreList;
        this.localDateTime = deadline;
    }

}
