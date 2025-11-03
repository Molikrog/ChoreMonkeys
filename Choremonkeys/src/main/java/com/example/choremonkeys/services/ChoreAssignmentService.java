package com.example.choremonkeys.services;

import com.example.choremonkeys.models.ChoreAssignment;
import com.example.choremonkeys.models.ChoreStatus;
import com.example.choremonkeys.models.UserType;

import java.time.LocalDateTime;
import java.util.List;

public interface ChoreAssignmentService {

    // READ operations
    ChoreAssignment findById(Long id);
    List<ChoreAssignment> findAll();

    // Find by chore
    List<ChoreAssignment> findByChoreId(Long choreId);

    // Find by employer (parent who posted)
    List<ChoreAssignment> findByEmployerId(Long employerId);
    List<ChoreAssignment> findByEmployerIdAndStatus(Long employerId, ChoreStatus choreStatus);

    // Find by worker (child doing the chore)
    List<ChoreAssignment> findByWorkerId(Long workerId);


    List<ChoreAssignment> findByWorkerIdAndStatus(Long workerId, ChoreStatus choreStatus);

    // Find available chores (no worker assigned yet)
    List<ChoreAssignment> findAvailableChores();

    // CREATE operations
    // Parent posts a chore (uses first constructor - no worker yet)
    ChoreAssignment createChoreAssignment(Long employerId, Long choreId, LocalDateTime deadline);

    // Parent posts and directly assigns to a worker (uses second constructor)
    ChoreAssignment createChoreAssignmentWithWorker(Long employerId, Long choreId,
                                                    Long workerId, LocalDateTime deadline);

    // UPDATE operations
    // Child accepts a chore
    ChoreAssignment acceptChore(Long assignmentId, Long workerId);

    // Mark as in progress
    ChoreAssignment startChore(Long assignmentId, Long workerId);

    // Mark as completed
    ChoreAssignment completeChore(Long assignmentId, Long workerId);

    // Employer approves completion and pays worker
    ChoreAssignment approveAndPayChore(Long assignmentId, Long employerId);

    // Cancel assignment
    ChoreAssignment cancelAssignment(Long assignmentId);

    // Worker unassigns themselves (goes back to AVAILABLE)
    ChoreAssignment unassignWorker(Long assignmentId, Long workerId);

    // Update status manually
    ChoreAssignment updateStatus(Long assignmentId, ChoreStatus choreStatus);

    // DELETE operations
    void deleteAssignment(Long assignmentId);

    // UTILITY operations
    boolean isWorkerAssignedToChore(Long assignmentId, Long workerId);
    boolean canWorkerAcceptChore(Long assignmentId, Long workerId);
    int getActiveChoreCountForWorker(Long workerId);
    List<ChoreAssignment> findExpiredAssignments();
}