package com.example.choremonkeys.repository;

import com.example.choremonkeys.models.Chore;
import com.example.choremonkeys.models.User;
import com.example.choremonkeys.models.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Find by exact username
    Optional<User> findByUsername(String username);

    // Find by exact email
    Optional<User> findByEmail(String email);

    // Find all users by type
    List<User> findByUserType(UserType userType);


}
