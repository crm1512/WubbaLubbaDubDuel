package com.cybercode.wubbaduel.app.service;

import com.cybercode.wubbaduel.app.models.*;
import com.cybercode.wubbaduel.app.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DeckService {

    private final DeckRepository deckRepo;
    private final DeckCardRepository deckCardRepo;
    private final CardRepository cardRepo;
    private final UserRepository userRepo;

    @Autowired
    public DeckService(DeckRepository deckRepo, DeckCardRepository deckCardRepo,
                       CardRepository cardRepo, UserRepository userRepo) {
        this.deckRepo = deckRepo;
        this.deckCardRepo = deckCardRepo;
        this.cardRepo = cardRepo;
        this.userRepo = userRepo;
    }

    public List<Card> getFavoriteCards(Long userId) {
        Optional<User> userOpt = userRepo.findById(userId);
        if (userOpt.isEmpty()) return Collections.emptyList();

        Deck favoriteDeck = getOrCreateFavoriteDeck(userOpt.get());
        List<DeckCard> deckCards = deckCardRepo.findByDeck(favoriteDeck);

        List<Card> cards = new ArrayList<>();
        for (DeckCard dc : deckCards) {
            cards.add(dc.getCard());
        }
        return cards;
    }

    public boolean addCardToFavorites(Long userId, Long cardId) {
        Optional<User> userOpt = userRepo.findById(userId);
        Optional<Card> cardOpt = cardRepo.findById(cardId);
        if (userOpt.isEmpty() || cardOpt.isEmpty()) return false;

        Deck favoriteDeck = getOrCreateFavoriteDeck(userOpt.get());

        if (deckCardRepo.countByDeck(favoriteDeck) >= 3) return false; // máximo 3

        if (!deckCardRepo.existsByDeckAndCard(favoriteDeck, cardOpt.get())) {
            DeckCard deckCard = new DeckCard(favoriteDeck, cardOpt.get());
            deckCardRepo.save(deckCard);
        }
        return true;
    }

    @Transactional
    public void removeCardFromFavorites(Long userId, Long cardId) {
        Optional<User> userOpt = userRepo.findById(userId);
        Optional<Card> cardOpt = cardRepo.findById(cardId);
        if (userOpt.isEmpty() || cardOpt.isEmpty()) return;

        Deck favoriteDeck = getOrCreateFavoriteDeck(userOpt.get());
        deckCardRepo.deleteByDeckAndCard(favoriteDeck, cardOpt.get());
    }

    private Deck getOrCreateFavoriteDeck(User user) {
        return deckRepo.findByUserAndName(user, "favorites")
                .orElseGet(() -> {
                    Deck newDeck = new Deck();
                    newDeck.setUser(user);
                    newDeck.setName("favorites");
                    return deckRepo.save(newDeck);
                });
    }
}
