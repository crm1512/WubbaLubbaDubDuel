package services;

import models.Card;
import repositories.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private final CardRepo cardRepo;

    @Autowired
    public CardService(CardRepo cardRepo) {
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
