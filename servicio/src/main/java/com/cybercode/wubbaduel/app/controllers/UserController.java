package com.cybercode.wubbaduel.app.controllers;

import com.cybercode.wubbaduel.app.models.User;
import com.cybercode.wubbaduel.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.List;

@RestController //Convierte a json automaticamente es magia
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Añadir una cantidad personalizada de tokens al usuario
    @PostMapping("/tokens")
    public ResponseEntity<?> addCustomTokens(@RequestBody Map<String, Object> payload) {
        Long userId = Long.valueOf(payload.get("userId").toString());
        int tokens = Integer.parseInt(payload.get("tokens").toString());

        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setTokens(foundUser.getTokens() + tokens);
            userService.save(foundUser);

            Map<String, Object> response = new HashMap<>();
            response.put("tokens", foundUser.getTokens());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(404).body("No se ha encontrado al usuario");
        }
    }

    // Obtener los tokens de un usuario por ID
    @PostMapping("/get-tokens")
    public ResponseEntity<?> getTokens(@RequestBody Map<String, Object> payload) {
        Long userId = Long.valueOf(payload.get("id").toString());

        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("tokens", user.get().getTokens());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(404).body("No se ha encontrado al usuario");
        }
    }

    @PostMapping("/list")
    public ResponseEntity<?> getUsersPaginated(@RequestBody Map<String, Object> payload) {
        int page = Integer.parseInt(payload.get("page").toString());
        int pageSize = 9;

        List<User> users;
        long totalUsers;

        if (payload.containsKey("userId")) {
            Long userId = Long.valueOf(payload.get("userId").toString());
            users = userService.getUsersExcludingUser(userId, page, pageSize);
            totalUsers = userService.countUsersExcluding(userId); // <-- Asegúrate de tener este método
        } else {
            users = userService.getAllUsersPaginated(page, pageSize);
            totalUsers = userService.countAllUsers(); // <-- Y este también
        }

        List<Map<String, Object>> result = users.stream().map(user -> {
            Map<String, Object> userData = new HashMap<>();
            userData.put("id", user.getId());
            userData.put("username", user.getUsername());
            userData.put("avatar", user.getAvatar());
            userData.put("email", user.getEmail());
            userData.put("tokens", user.getTokens());
            userData.put("createdDate", user.getCreatedAt());
            return userData;
        }).toList();

        Map<String, Object> response = new HashMap<>();
        response.put("users", result);
        response.put("totalUsers", totalUsers);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/update-last-login")
    public ResponseEntity<?> updateLastLogin(@RequestBody Map<String, Object> payload) {
        try {
            Long userId = Long.valueOf(payload.get("userId").toString());
            Optional<User> userOpt = userService.getUserById(userId);

            if (userOpt.isEmpty()) {
                return ResponseEntity.status(404).body("Usuario no encontrado");
            }

            User user = userOpt.get();
            user.setLastLogin(LocalDateTime.now());
            userService.save(user);

            Map<String, Object> userData = new HashMap<>();
            userData.put("id", user.getId());
            userData.put("username", user.getUsername());
            userData.put("avatarUrl", user.getAvatar());
            userData.put("email", user.getEmail());
            userData.put("tokens", user.getTokens());
            userData.put("createdAt", user.getCreatedAt());
            userData.put("lastLogin", user.getLastLogin());

            return ResponseEntity.ok(userData);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error al actualizar lastLogin: " + e.getMessage());
        }
    }



}

