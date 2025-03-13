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
    private Long ChoreId;
    private String Title;
    private String Description;
    private String Destination;
    private Integer PhoneNumber;
    private Long Price;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Chore(String title,
                 String description,
                 String destination,
                 Integer phoneNumber,
                 Long price,
                 User user) {
        this.Title = title;
        this.Description = description;
        this.Destination = destination;
        this.PhoneNumber = phoneNumber;
        this.Price = price;
        this.user = user;

    }
}
