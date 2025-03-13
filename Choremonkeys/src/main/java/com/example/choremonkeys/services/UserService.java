package com.example.choremonkeys.services;

import com.example.choremonkeys.models.Chore;
import com.example.choremonkeys.models.User;

import java.util.List;

public interface UserService {

    User findById(Long id);
    User createUser(String Username,
                    String Password,
                    String Email,
                    Long Money, List<Chore> choreId);
    Long deleteUser(Long id);

    User updateUser(Long id,
                    String Username,
                    String Password,
                    String Email,
                    Long Money,
                    List<Chore> ChoreIds);


//    double calculateEarnings(Long id);

//    List<User> searchUserByName(String name);
}
