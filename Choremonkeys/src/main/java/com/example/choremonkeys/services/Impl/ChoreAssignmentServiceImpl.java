package com.example.choremonkeys.services.Impl;

import com.example.choremonkeys.models.*;
import com.example.choremonkeys.models.exceptions.InvalidChoreAssignmentIdException;
import com.example.choremonkeys.repository.ChoreAssignmentRepository;
import com.example.choremonkeys.services.ChoreAssignmentService;
import com.example.choremonkeys.services.ChoreService;
import com.example.choremonkeys.services.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChoreAssignmentServiceImpl implements ChoreAssignmentService {

    private final ChoreAssignmentRepository choreAssignmentRepository;
    private final UserService userService;
    private final ChoreService choreService;


    public ChoreAssignmentServiceImpl(ChoreAssignmentRepository choreAssignmentRepository, UserService userService, ChoreService choreService) {
        this.choreAssignmentRepository = choreAssignmentRepository;
        this.userService = userService;
        this.choreService = choreService;
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
    public List<ChoreAssignment> findAvailableChores() {
        return choreAssignmentRepository.findAllByChoreStatus(ChoreStatus.Available);
    }

    @Override
    public ChoreAssignment createAssignment(Long employerId, Long choreId, LocalDateTime deadline) {

        User employer = userService.findById(employerId);
        Chore chore = choreService.findById(choreId);
        return choreAssignmentRepository.save(new ChoreAssignment(employer, chore, deadline));
    }

    @Override
    public ChoreAssignment acceptChore(Long assignmentId, Long workerId) {
        return null;
    }

    @Override
    public ChoreAssignment startChore(Long assignmentId, Long workerId) {

        ChoreAssignment choreAssignment = findById(assignmentId);
        User worker = userService.findById(workerId);
        choreAssignment.setChoreStatus(ChoreStatus.Taken);

        return null;
    }

    @Override
    public ChoreAssignment completeChore(Long assignmentId, Long workerId) {
        return null;
    }

    @Override
    public ChoreAssignment approveAndPay(Long assignmentId, Long employerId) {

        ChoreAssignment choreAssignment = findById(assignmentId);
        User user = userService.findById(employerId);
        choreAssignment.setChoreStatus(ChoreStatus.Completed);
        user.setMoney(user.getMoney());
        return null;
    }

    @Override
    public ChoreAssignment cancel(Long assignmentId) {

        ChoreAssignment choreAssignment = findById(assignmentId);
        choreAssignment.setChoreStatus(ChoreStatus.Cancelled);
        choreAssignmentRepository.save(choreAssignment);
        return choreAssignment;

    }

    @Override
    public List<ChoreAssignment> findByEmployer(Long employerId) {
        return choreAssignmentRepository.findByEmployer_Id(employerId);
    }

    @Override
    public List<ChoreAssignment> findByWorker(Long workerId) {
        return choreAssignmentRepository.findByWorker_Id(workerId);
    }

    @Override
    public List<ChoreAssignment> findExpiredAssignments() {
        return List.of();
    }

    @Override
    public List<User> getWorkers() {
        return userService.findAll().stream().filter(w -> w.getUserType() == UserType.WORKER).toList();
    }

    @Override
    public List<User> getEmployers() {
        return userService.findAll().stream().filter(e -> e.getUserType() == UserType.EMPLOYER).toList();
    }
}
