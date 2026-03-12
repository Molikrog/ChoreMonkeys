package com.example.choremonkeys.services.Impl;

import com.example.choremonkeys.models.User;
import com.example.choremonkeys.models.UserType;
import com.example.choremonkeys.models.exceptions.InvalidUserIdExcpetion;
import com.example.choremonkeys.models.exceptions.InvalidUsernameException;
import com.example.choremonkeys.repository.UserRepository;
import com.example.choremonkeys.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(InvalidUsernameException::new);
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
        String encodedPassword = passwordEncoder.encode(password);
        return userRepository.save(new User(username, encodedPassword, email, money, userType));
    }

    @Override
    public User updateUser(Long id, String username, String password, String email, Long money, UserType userType) {
        User user = findById(id);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setMoney(money);
        user.setUserType(userType);
        userRepository.save(user);
        return user;

    }

    @Override
    public User updateUserMoney(Long id, Long money) {
        User user = findById(id);
        if (money > user.getMoney()) {
            user.setMoney(user.getMoney() + money);
        } else {
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
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public User updateUserType(Long id, UserType userType) {
        User user = findById(id);
        user.setUserType(userType);
        userRepository.save(user);
        return user;
    }

    @Override
    public void addMoney(Long userId, int amount) {
        User user = findById(userId);
        user.setMoney(user.getMoney() + amount);
        userRepository.save(user);
    }
}
