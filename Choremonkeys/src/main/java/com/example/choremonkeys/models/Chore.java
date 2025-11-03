package com.example.choremonkeys.models;


import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "app_Chore")

public class Chore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ChoreId;
    private String Title;
    private String Description;
    private String Destination;
    private Integer PhoneNumber;
    private Long Price;

    @Enumerated(EnumType.STRING)
    private ChoreStatus choreStatus = ChoreStatus.Available;

    @OneToMany(mappedBy = "chore")
    private List<ChoreAssignment> choreAssignment = new ArrayList<>();

    public Chore(String title, String description, String destination, Integer phoneNumber, Long price, ChoreStatus choreStatus) {
        this.Title = title;
        this.Description = description;
        this.Destination = destination;
        this.PhoneNumber = phoneNumber;
        this.Price = price;
        this.choreStatus = choreStatus;
    }

    public Chore (String title, String description, String destination, Integer phoneNumber, Long price, ChoreStatus choreStatus, List<ChoreAssignment> choreAssignment) {
        this(title, description, destination, phoneNumber, price, choreStatus);
        if(choreAssignment != null){
            this.choreAssignment.forEach(this::addChoreAssignment);
        }
    }

    public void addChoreAssignment(ChoreAssignment choreAssignment) {
        this.choreAssignment.add(choreAssignment);
        choreAssignment.setChoreList(this); // with this part we add the object of the
    } // this is a helping function to add the object choreAssignment and then adding the whole thing to the list

    public void removeChoreAssignment(ChoreAssignment choreAssignment) {
        this.choreAssignment.remove(choreAssignment);
        choreAssignment.setChoreList(null);
    }
}
