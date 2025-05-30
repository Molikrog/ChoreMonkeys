package com.example.choremonkeys.repository;


import com.example.choremonkeys.models.Chore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoreRepository extends JpaSpecificationRepository<Chore, Long>, JpaRepository<Chore, Long> {
    @Query ("SELECT c FROM Chore c WHERE LOWER(c.Title) LIKE CONCAT(LOWER(:title), '%')")
    List<Chore> findbyTitle(String Title);
    @Query ("SELECT c FROM Chore c WHERE LOWER(c.Destination) LIKE CONCAT(LOWER(:title), '%')")
    List<Chore> findbyDestination(String Destination);
}
