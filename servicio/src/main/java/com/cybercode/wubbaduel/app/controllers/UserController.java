package com.cybercode.wubbaduel.app.controllers;

import com.cybercode.wubbaduel.app.models.User;
import com.cybercode.wubbaduel.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        if (payload.containsKey("userId")) {
            Long userId = Long.valueOf(payload.get("userId").toString());
            users = userService.getUsersExcludingUser(userId, page, pageSize);
        } else {
            users = userService.getAllUsersPaginated(page, pageSize);
        }

        List<Map<String, Object>> result = users.stream().map(user -> {
            Map<String, Object> userData = new HashMap<>();
            userData.put("id", user.getId());
            userData.put("username", user.getUsername());
            userData.put("avatar", user.getAvatar());
            userData.put("email", user.getEmail());
            userData.put("tokens", user.getTokens());
            return userData;
        }).toList();

        return ResponseEntity.ok(result);
    }

}

