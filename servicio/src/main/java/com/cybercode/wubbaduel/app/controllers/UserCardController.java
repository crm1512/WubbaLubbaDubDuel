package com.cybercode.wubbaduel.app.controllers;

import com.cybercode.wubbaduel.app.models.UserCard;
import com.cybercode.wubbaduel.app.models.Card;
import com.cybercode.wubbaduel.app.service.UserCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Map;

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

    // Eliminar una carta del inventario del usuario
    @DeleteMapping("/{userId}/card/{cardId}")
    public void deleteUserCard(@PathVariable Long userId, @PathVariable Long cardId) {
        userCardService.removeUserCard(userId, cardId);
    }

    // Actualizar la cantidad de una carta de usuario
    @PutMapping("/{userId}/card/{cardId}")
    public UserCard updateQuantity(
            @PathVariable Long userId,
            @PathVariable Long cardId,
            @RequestParam int quantity) {
        return userCardService.updateCardQuantity(userId, cardId, quantity);
    }

    // Abrir sobre segun rareza
    @PostMapping("/open-pack")
    public ResponseEntity<?> openPack(@RequestBody Map<String, Object> payload) {
        Long userId = Long.valueOf(payload.get("id").toString());
        String rarity = payload.get("rarity").toString().toLowerCase();

        try {
            List<Card> cards = userCardService.openPack(userId, rarity);
            return ResponseEntity.ok(cards);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al abrir el sobre: " + e.getMessage());
        }
    }

    // Obtener todas las cartas de un usuario mediante POST (enviando userId en el body)
    @PostMapping("/get-by-user")
    public ResponseEntity<?> getUserCardsByPost(@RequestBody Map<String, Object> payload) {
        try {
            Long userId = Long.valueOf(payload.get("userId").toString());
            List<UserCard> userCards = userCardService.getUserCards(userId);
            return ResponseEntity.ok(userCards);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Error al obtener las cartas del usuario: " + e.getMessage());
        }
    }
}
