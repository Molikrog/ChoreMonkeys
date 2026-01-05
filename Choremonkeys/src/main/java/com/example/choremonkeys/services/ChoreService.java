package com.example.choremonkeys.services;

import com.example.choremonkeys.models.Chore;
import com.example.choremonkeys.models.ChoreStatus;

import java.util.List;

public interface ChoreService {

    // READ operations
    Chore findById(Long choreId);
    List<Chore> findAll();
    List<Chore> findByTitle(String title);
    List<Chore> findByDestination(String destination);
    List<Chore> findByStatus(ChoreStatus choreStatus);
    List<Chore> findByPriceRange(Long minPrice, Long maxPrice);


    Chore createChoreWithStatus(String title, String description, String destination, Long phoneNumber, int price, ChoreStatus choreStatus);

    // UPDATE operations
    Chore updateChore(Long id,
                      String title,
                      String description,
                      String destination,
                      Integer phoneNumber,
                      Long price,
                      ChoreStatus choreStatus);

    Chore updateChoreStatus(Long id, ChoreStatus choreStatus);

    // DELETE operations
    void deleteChore(Long choreId);

    // UTILITY operations
    boolean existsById(Long choreId);
}
