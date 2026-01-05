package com.example.choremonkeys.models;


import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import lombok.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "app_chore")

public class Chore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long choreId;
    private String title;
    private String description;
    private String destination;
    private Integer phoneNumber;
    private Long price;

    @Enumerated(EnumType.STRING)
    private ChoreStatus choreStatus = ChoreStatus.Available;


    public Chore(String title, String description, String destination, Integer phoneNumber, Long price, ChoreStatus choreStatus) {
        this.title = title;
        this.description = description;
        this.destination = destination;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.choreStatus = choreStatus;
    }

}
