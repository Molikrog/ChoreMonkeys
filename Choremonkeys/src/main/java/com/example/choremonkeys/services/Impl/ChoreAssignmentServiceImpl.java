package com.example.choremonkeys.services.Impl;

import com.example.choremonkeys.models.*;
import com.example.choremonkeys.models.exceptions.InvalidChoreAssignmentIdException;
import com.example.choremonkeys.repository.ChoreAssignmentRepository;
import com.example.choremonkeys.services.ChoreAssignmentService;
import com.example.choremonkeys.services.ChoreService;
import com.example.choremonkeys.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ChoreAssignmentServiceImpl implements ChoreAssignmentService {

    private final ChoreAssignmentRepository choreAssignmentRepository;
    private final UserService userService;
    private final ChoreService choreService;

    public ChoreAssignmentServiceImpl(ChoreAssignmentRepository choreAssignmentRepository,
                                      UserService userService,
                                      ChoreService choreService) {
        this.choreAssignmentRepository = choreAssignmentRepository;
        this.userService = userService;
        this.choreService = choreService;
    }

    @Override
    public ChoreAssignment findById(Long id) {
        return choreAssignmentRepository.findById(id)
                .orElseThrow(InvalidChoreAssignmentIdException::new);
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
    public ChoreAssignment createAssignment(Long employerId,
                                            Long choreId,
                                            LocalDateTime deadline) {

        User employer = userService.findById(employerId);

        if (employer.getUserType() != UserType.EMPLOYER) {
            throw new RuntimeException("Only employers can create assignments");
        }

        Chore chore = choreService.findById(choreId);

        ChoreAssignment assignment =
                new ChoreAssignment(employer, chore, deadline, ChoreStatus.Available);

        return choreAssignmentRepository.save(assignment);
    }

    @Override
    public ChoreAssignment acceptChore(Long assignmentId, Long workerId) {

        ChoreAssignment assignment = findById(assignmentId);
        User worker = userService.findById(workerId);

        if (worker.getUserType() != UserType.WORKER) {
            throw new RuntimeException("Only workers can accept chores");
        }

        if (assignment.getChoreStatus() != ChoreStatus.Available) {
            throw new RuntimeException("Chore is not available");
        }

        if (assignment.getEmployer().getId().equals(workerId)) {
            throw new RuntimeException("Employer cannot accept own chore");
        }

        assignment.setWorker(worker);
        assignment.setChoreStatus(ChoreStatus.Taken);

        return choreAssignmentRepository.save(assignment);
    }

    @Override
    public ChoreAssignment startChore(Long assignmentId, Long workerId) {

        ChoreAssignment assignment = findById(assignmentId);

        if (assignment.getWorker() == null ||
                !assignment.getWorker().getId().equals(workerId)) {
            throw new RuntimeException("Only assigned worker can start");
        }

        if (assignment.getChoreStatus() != ChoreStatus.Taken) {
            throw new RuntimeException("Chore cannot be started");
        }

        return assignment;
    }

    @Override
    public ChoreAssignment completeChore(Long assignmentId, Long workerId) {

        ChoreAssignment assignment = findById(assignmentId);

        if (assignment.getWorker() == null ||
                !assignment.getWorker().getId().equals(workerId)) {
            throw new RuntimeException("Only assigned worker can complete");
        }

        if (assignment.getChoreStatus() != ChoreStatus.Taken) {
            throw new RuntimeException("Chore cannot be completed");
        }

        assignment.setChoreStatus(ChoreStatus.AwaitingApproval);

        return choreAssignmentRepository.save(assignment);
    }

    @Override
    public ChoreAssignment approveAndPay(Long assignmentId, Long employerId) {

        ChoreAssignment assignment = findById(assignmentId);
        User employer = userService.findById(employerId);

        if (employer.getUserType() != UserType.EMPLOYER) {
            throw new RuntimeException("Only employers can approve");
        }

        if (!assignment.getEmployer().getId().equals(employerId)) {
            throw new RuntimeException("Not your chore");
        }

        if (assignment.getChoreStatus() != ChoreStatus.AwaitingApproval) {
            throw new RuntimeException("Chore not ready for approval");
        }

        User worker = assignment.getWorker();
        Long price = assignment.getChore().getPrice();

        if (employer.getMoney() < price) {
            throw new RuntimeException("Insufficient funds");
        }

        employer.setMoney(employer.getMoney() - price);
        worker.setMoney(worker.getMoney() + price);

        assignment.setChoreStatus(ChoreStatus.Completed);

        choreService.updateChoreStatus(
                assignment.getChore().getChoreId(),
                ChoreStatus.Completed
        );

        return assignment;
    }

    @Override
    public ChoreAssignment cancel(Long assignmentId) {

        ChoreAssignment assignment = findById(assignmentId);

        if (assignment.getChoreStatus() == ChoreStatus.Completed ||
                assignment.getChoreStatus() == ChoreStatus.Cancelled) {
            throw new RuntimeException("Cannot cancel");
        }

        assignment.setWorker(null);
        assignment.setChoreStatus(ChoreStatus.Cancelled);

        return choreAssignmentRepository.save(assignment);
    }

    @Override
    public List<ChoreAssignment> findByEmployer(Long employerId) {
        return choreAssignmentRepository.findByEmployer_Id(employerId);
    }

    @Override
    public List<ChoreAssignment> findByWorker(Long workerId) {
        return choreAssignmentRepository.findByWorker_id(workerId);
    }

    @Override
    public List<ChoreAssignment> findExpiredAssignments() {
        return List.of();
    }

    @Override
    public List<User> getWorkers() {
        return userService.findAll()
                .stream()
                .filter(u -> u.getUserType() == UserType.WORKER)
                .toList();
    }

    @Override
    public List<User> getEmployers() {
        return userService.findAll()
                .stream()
                .filter(u -> u.getUserType() == UserType.EMPLOYER)
                .toList();
    }

    @Override
    public ChoreAssignment assignWorker(Long choreId, User worker) {
        return null;
    }

    @Override
    public ChoreAssignment removeWorker(Long assignmentId, Long employerId) {

        ChoreAssignment assignment = findById(assignmentId);

        if (!assignment.getEmployer().getId().equals(employerId)) {
            throw new RuntimeException("Only employer can remove worker");
        }

        assignment.setWorker(null);
        assignment.setChoreStatus(ChoreStatus.Available);

        return choreAssignmentRepository.save(assignment);
    }
}
