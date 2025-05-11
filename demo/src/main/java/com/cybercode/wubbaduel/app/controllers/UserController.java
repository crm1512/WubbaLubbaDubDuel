package com.cybercode.wubbaduel.app.controllers;

import com.cybercode.wubbaduel.app.models.User;
import com.cybercode.wubbaduel.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController //Convierte a json automaticamente es magia
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Crear un nuevo usuario
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Obtener un usuario por nombre de usuario
    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Añadir 100 tokens al usuario
    @PostMapping("/{id}/add-tokens")
    public ResponseEntity<?> addTokens(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setTokens(foundUser.getTokens() + 100); // Añado 100 tokens
            userService.save(foundUser);
            return ResponseEntity.ok("Se han añadido 100 tokens al usuario: " + foundUser.getUsername());
        } else {
            return ResponseEntity.status(404).body("No se ha encontrado al usuario");
        }
    }

    // Añadir una cantidad personalizada de tokens al usuario
    @PostMapping("/{id}/add-tokens/{tokens}")
    public ResponseEntity<?> addCustomTokens(@PathVariable Long userId, @PathVariable int tokens) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setTokens(foundUser.getTokens() + tokens);
            userService.save(foundUser);
            return ResponseEntity.ok("Se han añadido " + tokens + " tokens al usuario: " + foundUser.getUsername());
        } else {
            return ResponseEntity.status(404).body("No se ha encontrado al usuario");
        }
    }
}

