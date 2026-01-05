package com.example.choremonkeys.services;

import com.example.choremonkeys.models.User;
import com.example.choremonkeys.models.UserType;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findById(Long id);
    List<User> findAll();
    User findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findByUserType(UserType userType);

    User createUser(String username,
                    String password,
                    String email,
                    Long money,
                    UserType userType);

    User updateUser(Long id,
                    String username,
                    String password,
                    String email,
                    Long money,
                    UserType userType);

    User updateUserMoney(Long id, Long money);

    void deleteUser(Long id);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
//    void migratePlainTextPasswords();
}