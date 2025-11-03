package com.example.choremonkeys.services.Impl;

import com.example.choremonkeys.models.User;
import com.example.choremonkeys.models.UserType;
import com.example.choremonkeys.models.exceptions.InvalidUserIdExcpetion;
import com.example.choremonkeys.repository.ChoreRepository;
import com.example.choremonkeys.repository.UserRepository;
import com.example.choremonkeys.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, ChoreRepository choreRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(InvalidUserIdExcpetion::new);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findByUserType(UserType userType) {
        return userRepository.findByUserType(userType);
    }

    @Override
    public User createUser(String username, String password, String email, Long money, UserType userType) {
        return userRepository.save(new User(username, password, email, money, userType));
    }

    @Override
    public User updateUser(Long id, String username, String password, String email, Long money, UserType userType) {
        User user = findById(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setMoney(money);
        user.setUserType(userType);
        userRepository.save(user);
        return user;

    }

    @Override
    public User updateUserMoney(Long id, Long money) {
        User user = findById(id);
        if(money > user.getMoney()) {
            user.setMoney(user.getMoney() + money);
        }
        else{
            Long payed = user.getMoney() - money;
            user.setMoney(user.getMoney() - payed);
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
    User user = findById(id);
    userRepository.delete(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }
}
