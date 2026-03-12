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
    private Long phoneNumber;
    private int price;


    private Double longitude;
    private Double latitude;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private User employer;

    @Enumerated(EnumType.STRING)
    private ChoreStatus choreStatus = ChoreStatus.Available;


    public Chore(String title, String description, String destination, Long phoneNumber, int price, ChoreStatus choreStatus) {
        this.title = title;
        this.description = description;
        this.destination = destination;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.choreStatus = choreStatus;
    }

    public Chore(String title, String description, String destination, Long phoneNumber, int price, ChoreStatus choreStatus, User employer) {
        this.title = title;
        this.description = description;
        this.destination = destination;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.choreStatus = choreStatus;
        this.employer = employer;
    }

    public Chore(String title, String description, String destination, int price, Long phoneNumber, ChoreStatus choreStatus, Double latitude, Double longitude) {
        this(title, description, destination, phoneNumber, price, choreStatus); // calls existing constructor
        this.latitude = latitude;
        this.longitude = longitude;
    }


}
