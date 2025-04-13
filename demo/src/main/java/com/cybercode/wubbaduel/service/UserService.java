package com.cybercode.wubbaduel.service;

import com.cybercode.wubbaduel.model.User;
import com.cybercode.wubbaduel.repositorie.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    
    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }
    
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
