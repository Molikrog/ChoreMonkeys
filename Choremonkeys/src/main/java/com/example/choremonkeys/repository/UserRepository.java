package com.example.choremonkeys.repository;

import com.example.choremonkeys.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaSpecificationRepository<User, Long>,JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE LOWER(u.Username) LIKE CONCAT(LOWER(:Username), '%')")
    List<User> searchUserByName(String Username);
}
