package com.cybercode.wubbaduel.app.service;

import com.cybercode.wubbaduel.app.models.User;
import com.cybercode.wubbaduel.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public User register(String name, String lastname, String username, String password, String email){
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        String hashedPassword = passwordEncoder.encode(password);

        User newUser = new User();
        newUser.setName(name);
        newUser.setLastname(lastname);
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword);
        newUser.setTokens(500);
        LocalDateTime time = LocalDateTime.now();
        newUser.setCreatedAt(time);
        int randomId = ThreadLocalRandom.current().nextInt(1, 827); // 827 no cuenta es hasta 826
        String avatarUrl = "https://rickandmortyapi.com/api/character/avatar/" + randomId + ".jpeg";
        newUser.setAvatar(avatarUrl);
        return userRepository.save(newUser);
    }

    public Optional<User> login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
