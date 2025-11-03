package com.example.choremonkeys.repository;

import com.example.choremonkeys.models.ChoreAssignment;
import com.example.choremonkeys.models.ChoreStatus;
import com.example.choremonkeys.models.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoreAssignmentRepository extends JpaRepository<ChoreAssignment, Long> {

    List<ChoreAssignment> findByChore_ChoreId(Long choreId);
    List<ChoreAssignment> findByUser_UserIdAndChoreStatus(Long choreId, ChoreStatus choreStatus);
    // Find chores posted by this user with specific status
    List<ChoreAssignment> findByUser_idAndUserTypeAndChoreStatus(Long employerId, ChoreStatus choreStatus);

    List<ChoreAssignment> findByEmployer_Id(Long employerId);
    List<ChoreAssignment> findByWorker_Id(Long workerId);

    //ChoreStatus
    List<ChoreAssignment> findAllByChoreStatus(ChoreStatus choreStatus);
}
