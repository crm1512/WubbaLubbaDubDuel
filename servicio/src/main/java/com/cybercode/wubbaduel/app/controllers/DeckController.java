package com.cybercode.wubbaduel.app.controllers;

import com.cybercode.wubbaduel.app.models.Card;
import com.cybercode.wubbaduel.app.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/decks")
public class DeckController {

    private final DeckService deckService;

    @Autowired
    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    // Obtener cartas favoritas (devuelve List<UserCard-like> pero solo necesitas los Card)
    @PostMapping("/favorites")
    public ResponseEntity<List<Map<String, Object>>> getFavorites(@RequestBody Map<String, Long> body) {
        Long userId = body.get("userId");
        List<Card> favoriteCards = deckService.getFavoriteCards(userId);

        // Para que el frontend pueda hacer favorites.map(fav => fav.card.id)
        List<Map<String, Object>> response = new ArrayList<>();
        for (Card card : favoriteCards) {
            Map<String, Object> wrapper = new HashMap<>();
            wrapper.put("card", card);
            response.add(wrapper);
        }

        return ResponseEntity.ok(response);
    }

    // Añadir a favoritos (máximo 3)
    @PostMapping("/add-favorite")
    public ResponseEntity<?> addFavorite(@RequestBody Map<String, Long> body) {
        Long userId = body.get("userId");
        Long cardId = body.get("cardId");

        boolean added = deckService.addCardToFavorites(userId, cardId);
        if (!added) {
            return ResponseEntity.badRequest().body("Máximo 3 cartas favoritas.");
        }
        return ResponseEntity.ok().build();
    }

    // Quitar de favoritos
    @PostMapping("/remove-favorite")
    public ResponseEntity<?> removeFavorite(@RequestBody Map<String, Long> body) {
        Long userId = body.get("userId");
        Long cardId = body.get("cardId");

        deckService.removeCardFromFavorites(userId, cardId);
        return ResponseEntity.ok().build();
    }
}
