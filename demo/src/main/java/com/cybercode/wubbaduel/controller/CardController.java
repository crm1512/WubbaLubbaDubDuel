package com.cybercode.wubbaduel.controller;

import  com.cybercode.wubbaduel.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cybercode.wubbaduel.service.CardService;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    // Obtener todas las cartas
    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    // Obtener una carta por su ID
    @GetMapping("/{id}")
    public Card getCardById(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    // Obtener carta por nombre
    @GetMapping("/search")
    public Card getCardByName(@RequestParam String name) {
        return cardService.getCardByName(name);
    }

    // Obtener cartas por coste máximo
    @GetMapping("/cost")
    public List<Card> getCardsByCostLessThanEqual(@RequestParam int cost) {
        return cardService.getCardsByCostLessThanEqual(cost);
    }
}
