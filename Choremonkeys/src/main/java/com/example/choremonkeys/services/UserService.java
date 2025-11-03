package com.example.choremonkeys.services;

import com.example.choremonkeys.models.User;
import com.example.choremonkeys.models.UserType;

import java.util.List;
import java.util.Optional;

public interface UserService {

    // READ operations
    User findById(Long id);
    List<User> findAll();
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findByUserType(UserType userType);

    // CREATE operations
    User createUser(String username,
                    String password,
                    String email,
                    Long money,
                    UserType userType);

    // UPDATE operations
    User updateUser(Long id,
                    String username,
                    String password,
                    String email,
                    Long money,
                    UserType userType);

    User updateUserMoney(Long id, Long money);

    // DELETE operations
    void deleteUser(Long id);

    // UTILITY operations
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}