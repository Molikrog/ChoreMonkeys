package com.example.choremonkeys.services.Impl;

import com.example.choremonkeys.models.ChoreAssignment;
import com.example.choremonkeys.models.ChoreStatus;
import com.example.choremonkeys.models.UserType;
import com.example.choremonkeys.models.exceptions.InvalidChoreAssignmentIdException;
import com.example.choremonkeys.repository.ChoreAssignmentRepository;
import com.example.choremonkeys.services.ChoreAssignmentService;
import com.example.choremonkeys.services.ChoreService;
import com.example.choremonkeys.services.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChoreAssignmentServiceImpl implements ChoreAssignmentService {

    final private ChoreAssignmentRepository choreAssignmentRepository;
    final private ChoreService choreService;
    final private UserService userService;

    public ChoreAssignmentServiceImpl(ChoreAssignmentRepository choreAssignmentRepository, ChoreService choreService, UserService userService) {
        this.choreAssignmentRepository = choreAssignmentRepository;
        this.choreService = choreService;
        this.userService = userService;
    }

    @Override
    public ChoreAssignment findById(Long id) {
        return choreAssignmentRepository.findById(id).orElseThrow(InvalidChoreAssignmentIdException::new);
    }

    @Override
    public List<ChoreAssignment> findAll() {
        return choreAssignmentRepository.findAll();
    }

    @Override
    public List<ChoreAssignment> findByChoreId(Long choreId) {
        return choreAssignmentRepository.findByChore_ChoreId(choreId); // you need to make a choreAssignment_Choreid quaery in the repo to search in the table of only the needed chore id assignments.
        //like trying to find the chore mow the lawn chore id in all the assignments that have been posted on the board.
    }

    @Override
    public List<ChoreAssignment> findByEmployerId(Long employerId) {
        return choreAssignmentRepository.findByEmployer_Id(employerId);
    }

    @Override
    public List<ChoreAssignment> findByEmployerIdAndStatus(Long employerId, ChoreStatus choreStatus) {
        return choreAssignmentRepository.findByUser_UserIdAndChoreStatus(employerId, choreStatus);

    }

    @Override
    public List<ChoreAssignment> findByWorkerId(Long workerId) {
        return choreAssignmentRepository.findByWorker_Id(workerId);
    }

    @Override
    public List<ChoreAssignment> findByWorkerIdAndStatus(Long workerId, ChoreStatus choreStatus) {
        return choreAssignmentRepository.findByUser_idAndUserTypeAndChoreStatus(workerId,choreStatus);
    }

    @Override
    public List<ChoreAssignment> findAvailableChores() {
        return choreAssignmentRepository.findAllByChoreStatus(ChoreStatus.Available);
    }

    @Override
    public ChoreAssignment createChoreAssignment(Long employerId, Long choreId, LocalDateTime deadline) {
        return choreAssignmentRepository.save(new ChoreAssignment(employerId, choreId, deadline));
    }

    @Override
    public ChoreAssignment createChoreAssignmentWithWorker(Long employerId, Long choreId, Long workerId, LocalDateTime deadline) {
        return null;
    }

    @Override
    public ChoreAssignment acceptChore(Long assignmentId, Long workerId) {
        return null;
    }

    @Override
    public ChoreAssignment startChore(Long assignmentId, Long workerId) {
        return null;
    }

    @Override
    public ChoreAssignment completeChore(Long assignmentId, Long workerId) {
        return null;
    }

    @Override
    public ChoreAssignment approveAndPayChore(Long assignmentId, Long employerId) {
        return null;
    }

    @Override
    public ChoreAssignment cancelAssignment(Long assignmentId) {
        return null;
    }

    @Override
    public ChoreAssignment unassignWorker(Long assignmentId, Long workerId) {
        return null;
    }

    @Override
    public ChoreAssignment updateStatus(Long assignmentId, ChoreStatus choreStatus) {
        return null;
    }

    @Override
    public void deleteAssignment(Long assignmentId) {

    }

    @Override
    public boolean isWorkerAssignedToChore(Long assignmentId, Long workerId) {
        return false;
    }

    @Override
    public boolean canWorkerAcceptChore(Long assignmentId, Long workerId) {
        return false;
    }

    @Override
    public int getActiveChoreCountForWorker(Long workerId) {
        return 0;
    }

    @Override
    public List<ChoreAssignment> findExpiredAssignments() {
        return List.of();
    }
}
