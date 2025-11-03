package com.example.choremonkeys.services.Impl;

import com.example.choremonkeys.models.Chore;
import com.example.choremonkeys.models.ChoreStatus;
import com.example.choremonkeys.models.User;
import com.example.choremonkeys.models.exceptions.InvalidChoresIdException;
import com.example.choremonkeys.repository.ChoreAssignmentRepository;
import com.example.choremonkeys.repository.ChoreRepository;
import com.example.choremonkeys.services.ChoreService;
import com.example.choremonkeys.services.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChoreServiceImpl implements ChoreService {

    final private ChoreRepository choreRepository;

    public ChoreServiceImpl(ChoreRepository choreRepository, ChoreAssignmentRepository choreAssignmentRepository) {
        this.choreRepository = choreRepository;
    }

    @Override
    public Chore findById(Long choreId) {
        return choreRepository.findById(choreId).orElseThrow(InvalidChoresIdException::new);
    }

    @Override
    public List<Chore> findAll() {
        return choreRepository.findAll();
    }

    @Override
    public List<Chore> findByTitle(String title) {
        return choreRepository.findbyTitle(title);
    }

    @Override
    public List<Chore> findByDestination(String destination) {
        return choreRepository.findbyDestination(destination);
    }

    @Override
    public List<Chore> findByStatus(ChoreStatus choreStatus) {
        return findAll(); // i will come back to this, when i implement the query for the status. For now it will return the whole list
    }

    @Override
    public List<Chore> findByPriceRange(Long minPrice, Long maxPrice) {
        return findAll();
    }

    @Override
    public Chore createChoreWithStatus(String title, String description, String destination, Integer phoneNumber, Long price, ChoreStatus choreStatus) {
        return choreRepository.save(new Chore(title, description, destination, phoneNumber, price, choreStatus));
    }

    @Override
    public Chore updateChore(Long id, String title, String description, String destination, Integer phoneNumber, Long price, ChoreStatus choreStatus) {
        Chore chore = findById(id);
        chore.setTitle(title);
        chore.setDescription(description);
        chore.setDestination(destination);
        chore.setPhoneNumber(phoneNumber);
        chore.setPrice(price);
        chore.setChoreStatus(choreStatus);
        choreRepository.save(chore);
        return chore;
    }

    @Override
    public Chore updateChoreStatus(Long id, ChoreStatus choreStatus) {
        Chore chore = findById(id);
        chore.setChoreStatus(choreStatus);
        choreRepository.save(chore);
        return chore;
    }

    @Override
    public void deleteChore(Long choreId) {
    Chore chore = findById(choreId);
    choreRepository.delete(chore);
    }

    @Override
    public boolean existsById(Long choreId) {
        if (findById(choreId) == null){
            return false;
    }
        return true;

    }
}
