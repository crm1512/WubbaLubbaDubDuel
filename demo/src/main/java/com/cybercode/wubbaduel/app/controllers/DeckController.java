package com.cybercode.wubbaduel.app.controllers;

import com.cybercode.wubbaduel.app.models.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cybercode.wubbaduel.app.service.DeckService;

import java.util.List;
import java.util.Optional;

@RestController //Convierte a json automaticamente es magia
@RequestMapping("/api/decks")
public class DeckController {

    private final DeckService deckService;

    @Autowired
    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    // Crear un nuevo mazo
    @PostMapping
    public Deck createDeck(@RequestBody Deck deck) {
        return deckService.createDeck(deck);
    }

    // Obtener un mazo por su ID
    @GetMapping("/{id}")
    public Optional<Deck> getDeckById(@PathVariable Long id) {
        return deckService.getDeckById(id);
    }

    // Obtener todos los mazos de un usuario
    @GetMapping("/user/{userId}")
    public List<Deck> getDecksByUserId(@PathVariable Long userId) {
        return deckService.getDecksByUserId(userId);
    }

    // Actualizar un mazo
    @PutMapping("/{id}")
    public Deck updateDeck(@PathVariable Long id, @RequestBody Deck updatedDeck) {
        return deckService.updateDeck(id, updatedDeck);
    }

    // Eliminar un mazo
    @DeleteMapping("/{id}")
    public void deleteDeck(@PathVariable Long id) {
        deckService.deleteDeck(id);
    }
}
