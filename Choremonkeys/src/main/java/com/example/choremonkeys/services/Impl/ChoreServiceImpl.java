package com.example.choremonkeys.services.Impl;

import com.example.choremonkeys.models.Chore;
import com.example.choremonkeys.models.User;
import com.example.choremonkeys.models.exceptions.InvalidChoresIdException;
import com.example.choremonkeys.repository.ChoreRepository;
import com.example.choremonkeys.services.ChoreService;
import com.example.choremonkeys.services.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChoreServiceImpl implements ChoreService {

    ChoreRepository choreRepository;
    UserService userService;

    public ChoreServiceImpl(ChoreRepository choreRepository, @Lazy UserService userService) {
        this.choreRepository = choreRepository;
        this.userService = userService;
    }

    @Override
    public Chore findById(Long choreId) {
        return choreRepository.findById(choreId).orElseThrow(InvalidChoresIdException::new);
    }

//    @Override
//    public List<Chore> findChoresByIds(List<Chore> chores) {
//        // Extract IDs from the list of Chore entities
//        List<Long> choreIds = chores.stream()
//                .map(Chore::getChoreId)
//                .collect(Collectors.toList());
//
//        // Fetch the Chore entities from the repository using the IDs
//        List<Chore> result = choreRepository.findAllById(choreIds);
//
//        // Validate if all IDs were found (optional)
//        if (result.size() != choreIds.size()) {
//            throw new InvalidChoresIdException("One or more chore IDs are invalid");
//        }
//
//        return result;
//    }

    @Override
    public List<Chore> findByTitle(String Title) {
        return choreRepository.findbyTitle(Title);
    }

    @Override
    public List<Chore> findByDestination(String destination) {
        return choreRepository.findbyDestination(destination);
    }

    @Override
    public List<Chore> findByIds(List<Long> choreIds) {
        return choreRepository.findAllById(choreIds);
    }

    @Override
    public List<Chore> findAll() {
        List<Chore> chores = choreRepository.findAll();
        return chores;
    }

    @Override
    public Chore createChore(String Title, String Description, String Destination, Integer PhoneNumber, Long Price, Long UserId) {
        User user =  userService.findById(UserId);
        return this.choreRepository.save(new Chore(Title, Description, Destination, PhoneNumber, Price, user));
    }

    @Override
    public Chore update(Long ChoreId, String Title, String Description, String Destination, Integer PhoneNumber, Long Price, Long UserId) {
        User user =  userService.findById(UserId);
        Chore chore = choreRepository.findById(ChoreId).orElse(null); // isto
        chore.setTitle(Title);
        chore.setDescription(Description);
        chore.setDestination(Destination);
        chore.setPhoneNumber(PhoneNumber);
        chore.setPrice(Price);
        chore.setUser(user);
        return chore;
    }

    @Override
    public void delete(Long ChoreId) {
    Chore chore = choreRepository.findById(ChoreId).orElse(null); // Ova treba da se popravi, nekako da se namesti exceptionot
    choreRepository.delete(chore);

    }
}
