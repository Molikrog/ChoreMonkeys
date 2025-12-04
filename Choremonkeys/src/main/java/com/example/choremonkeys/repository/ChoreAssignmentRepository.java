package com.example.choremonkeys.repository;

import com.example.choremonkeys.models.ChoreAssignment;
import com.example.choremonkeys.models.ChoreStatus;
import com.example.choremonkeys.models.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoreAssignmentRepository extends JpaRepository<ChoreAssignment, Long> {

    List<ChoreAssignment> findByEmployer_Id(Long employerId);

    List<ChoreAssignment> findByWorker_Id(Long workerId);

    List<ChoreAssignment> findAllByChoreStatus(ChoreStatus status);

    List<ChoreAssignment> findByChore_ChoreId(Long choreId);
}
