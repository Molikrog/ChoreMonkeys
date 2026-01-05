package com.example.choremonkeys.repository;

import com.example.choremonkeys.models.ChoreAssignment;
import com.example.choremonkeys.models.ChoreStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoreAssignmentRepository extends JpaRepository<ChoreAssignment, Long> {

    List<ChoreAssignment> findByEmployer_Id(Long employerId);

    List<ChoreAssignment> findByWorker_id(Long workerId);

    List<ChoreAssignment> findAllByChoreStatus(ChoreStatus status);
}
