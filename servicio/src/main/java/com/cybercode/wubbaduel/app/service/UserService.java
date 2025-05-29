package com.cybercode.wubbaduel.app.service;


import com.cybercode.wubbaduel.app.models.User;
import com.cybercode.wubbaduel.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public Optional<User> getUserByEmail(String email) {
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

    public void save(User user) {
        userRepo.save(user);
    }

    public List<User> getUsersExcludingUser(Long excludeUserId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepo.findByIdNot(excludeUserId, pageable);
    }

    public List<User> getAllUsersPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepo.findAll(pageable).getContent();
    }

    public long countAllUsers() {
        return userRepository.count();
    }

    public long countUsersExcluding(Long userId) {
        return userRepository.countByIdNot(userId);
    }

}
