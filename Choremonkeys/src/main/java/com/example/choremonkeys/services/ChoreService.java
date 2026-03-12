package com.example.choremonkeys.services;

import com.example.choremonkeys.models.Chore;
import com.example.choremonkeys.models.ChoreStatus;
import com.example.choremonkeys.models.User;

import java.util.List;

public interface ChoreService {

    // READ operations
    Chore findById(Long choreId);
    List<Chore> findAll();
    List<Chore> findByTitle(String title);
    List<Chore> findByDestination(String destination);
    List<Chore> findByStatus(ChoreStatus choreStatus);
    List<Chore> findByPriceRange(Long minPrice, Long maxPrice);


    Chore createChoreWithStatus(String title, String description, String destination, Long phoneNumber, int price, ChoreStatus choreStatus, User employer, Double latitude, Double longitude);



    Chore updateChore(Long id,
                      String title,
                      String description,
                      String destination,
                      Long phoneNumber,
                      int price,
                      ChoreStatus choreStatus,
                      Double longitude,
                      Double latitude);

    Chore updateChoreStatus(Long id, ChoreStatus choreStatus);

    List<Chore> findByEmployer(Long employerId);

    void deleteChore(Long choreId);

    boolean existsById(Long choreId);
}
