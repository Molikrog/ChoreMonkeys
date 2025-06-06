package com.example.choremonkeys.services;
import com.example.choremonkeys.models.Chore;

import java.util.List;

public interface ChoreService {

    public Chore findById(Long choreId);
//    public List<Chore> findChoresByIds(List<Chore> chores);
    List<Chore> findByTitle(String Title);
    List<Chore> findByDestination(String destination);
    public List<Chore> findByIds(List<Long> choreIds);

    List<Chore> findAll();

    Chore createChore(String Title,
                      String Description,
                      String Destination,
                      Integer PhoneNumber,
                      Long Price,
                      Long userId);
    Chore update(Long ChoreId,
                 String Title,
                 String Description,
                 String Destination,
                 Integer PhoneNumber,
                 Long Price,
                 Long userId);
    void delete(Long ChoreId);
}
