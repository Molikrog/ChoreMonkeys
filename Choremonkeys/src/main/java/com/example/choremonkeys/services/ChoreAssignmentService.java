package com.example.choremonkeys.services;

import com.example.choremonkeys.models.ChoreAssignment;
import com.example.choremonkeys.models.ChoreStatus;
import com.example.choremonkeys.models.User;
import com.example.choremonkeys.models.UserType;

import java.time.LocalDateTime;
import java.util.List;

public interface ChoreAssignmentService {

    ChoreAssignment findById(Long id);

    List<ChoreAssignment> findAll();

    List<ChoreAssignment> findAvailableChores();

    ChoreAssignment createAssignment(Long employerId, Long choreId, LocalDateTime deadline);

    ChoreAssignment acceptChore(Long assignmentId, Long workerId);

    ChoreAssignment startChore(Long assignmentId, Long workerId);

    ChoreAssignment completeChore(Long assignmentId, Long workerId);

    ChoreAssignment approveAndPay(Long assignmentId, Long employerId);

    ChoreAssignment cancel(Long assignmentId);

    List<ChoreAssignment> findByEmployer(Long employerId);

    List<ChoreAssignment> findByWorker(Long workerId);

    List<ChoreAssignment> findExpiredAssignments();

    List<User> getWorkers ();

    List<User> getEmployers ();
}