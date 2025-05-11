package com.cybercode.wubbaduel.app.service;

import com.cybercode.wubbaduel.app.models.Card;
import com.cybercode.wubbaduel.app.repositories.CardRepository;
import com.cybercode.wubbaduel.app.repositories.UserCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepo;

    @Autowired
    public CardService(CardRepository cardRepo) {
        this.cardRepo = cardRepo;
    }

    // Obtener todas las cartas
    public List<Card> getAllCards() {
        return cardRepo.findAll();
    }

    // Obtener una carta por su ID
    public Card getCardById(Long cardId) {
        return cardRepo.findById(cardId).orElse(null);
    }

    // Obtener cartas por nombre
    public Card getCardByName(String name) {
        return cardRepo.findByName(name);
    }

    // Obtener cartas cuyo costo es menor o igual a un valor dado
    public List<Card> getCardsByCostLessThanEqual(int cost) {
        return cardRepo.findByCostLessThanEqual(cost);
    }

}
