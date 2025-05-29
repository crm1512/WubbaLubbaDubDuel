package com.cybercode.wubbaduel.app.controllers;

import  com.cybercode.wubbaduel.app.models.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cybercode.wubbaduel.app.service.CardService;

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


}
