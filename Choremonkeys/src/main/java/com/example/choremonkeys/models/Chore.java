package com.example.choremonkeys.models;


import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import lombok.*;

@Entity
@NoArgsConstructor
@Data
@Setter
@Getter
public class Chore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long choreId;
    private String title;
    private String description;
    private String destination;
    private Integer phoneNumber;
    private Long price;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Chore(String title, String description, String destination, Integer phoneNumber, Long price, User user) {
        this.title = title;
        this.description = description;
        this.destination = destination;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.user = user;
    }
}
