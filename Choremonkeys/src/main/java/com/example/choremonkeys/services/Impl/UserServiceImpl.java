package com.example.choremonkeys.services.Impl;

import com.example.choremonkeys.models.Chore;
import com.example.choremonkeys.models.User;
import com.example.choremonkeys.models.exceptions.InvalidUserIdExcpetion;
import com.example.choremonkeys.repository.UserRepository;
import com.example.choremonkeys.services.ChoreService;
import com.example.choremonkeys.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    ChoreService choreService;

    public UserServiceImpl(UserRepository userRepository, ChoreService choreService) {
        this.userRepository = userRepository;
        this.choreService = choreService;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(InvalidUserIdExcpetion::new);
    }

    @Override
    public User createUser(String Username, String Password, String Email, Long Money, List<Chore> choreId) {
        return this.userRepository.save(new User(Username, Password, Email, Money, choreId));
    }

    @Override
    public Long deleteUser(Long id) {
        this.userRepository.deleteById(id);
        return id;
    }

    @Override
    public User updateUser(Long id, String Username, String Password, String Email, Long Money, List<Long> choreIds) {
        List<Chore> chores = this.choreService.findByIds(choreIds);
        User user = this.userRepository.findById(id).orElseThrow(InvalidUserIdExcpetion::new);
        user.setUsername(Username);
        user.setPassword(Password);
        user.setEmail(Email);
        user.setMoney(Money);
        user.setChores(chores);
        return user;
    }

//    @Override
//    public double calculateEarnings(Long id) {
//        return 0;
//    }

//    @Override
//    public List<User> searchUserByName(String name) {
//        return List.of();
//    }
}
