package com.cybercode.wubbaduel.app.controllers;


import com.cybercode.wubbaduel.app.models.UserCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cybercode.wubbaduel.app.service.UserCardService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-cards")
public class UserCardController {

    private final UserCardService userCardService;

    @Autowired
    public UserCardController(UserCardService userCardService) {
        this.userCardService = userCardService;
    }

    // Obtener todas las cartas de un usuario
    @GetMapping("/{userId}")
    public List<UserCard> getUserCards(@PathVariable Long userId) {
        return userCardService.getUserCards(userId);
    }

    // Obtener una carta específica de un usuario
    @GetMapping("/{userId}/card/{cardId}")
    public Optional<UserCard> getUserCard(@PathVariable Long userId, @PathVariable Long cardId) {
        return userCardService.getUserCard(userId, cardId);
    }

    // Verificar si el usuario tiene una carta
    @GetMapping("/{userId}/has-card/{cardId}")
    public boolean userHasCard(@PathVariable Long userId, @PathVariable Long cardId) {
        return userCardService.userHasCard(userId, cardId);
    }

    // Eliminar una carta del inventario del usuario
    @DeleteMapping("/{userCardId}")
    public void deleteUserCard(@PathVariable Long userCardId) {
        userCardService.removeUserCard(userCardId);
    }

    // Actualizar la cantidad de una carta de usuario
    @PutMapping("/{userCardId}")
    public UserCard updateQuantity(@PathVariable Long userCardId, @RequestParam int quantity) {
        return userCardService.updateCardQuantity(userCardId, quantity);
    }
}
