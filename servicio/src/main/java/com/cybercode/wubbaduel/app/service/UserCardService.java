package com.cybercode.wubbaduel.app.service;

import com.cybercode.wubbaduel.app.models.*;
import com.cybercode.wubbaduel.app.repositories.UserCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.stream.Collectors;

@Service
public class UserCardService {

    private final UserCardRepository userCardRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private CardService cardService;

    @Autowired
    public UserCardService(UserCardRepository userCardRepo) {
        this.userCardRepo = userCardRepo;
    }

    private final Random random = new Random();

    //Mapa para las cartas segun la rareza del sobre
    private static final Map<String, Map<String, Double>> PACK_PROBABILITIES = Map.of(
            "NORMAL", Map.of(
                    "NORMAL", 0.85,
                    "RARE", 0.13,
                    "EPIC", 0.015,
                    "LEGENDARY", 0.005
            ),
            "RARE", Map.of(
                    "NORMAL", 0.50,
                    "RARE", 0.40,
                    "EPIC", 0.08,
                    "LEGENDARY", 0.02
            ),
            "EPIC", Map.of(
                    "NORMAL", 0.20,
                    "RARE", 0.40,
                    "EPIC", 0.30,
                    "LEGENDARY", 0.10
            ),
            "LEGENDARY", Map.of(
                    "NORMAL", 0.05,
                    "RARE", 0.15,
                    "EPIC", 0.50,
                    "LEGENDARY", 0.30
            )
    );

    // Crear una nueva relación entre un usuario y una carta
    public UserCard createUserCard(User user, Card card, int quantity) {
        UserCard existingUserCard = userCardRepo.findByUserIdAndCardId(user.getId(), card.getId());

        if (existingUserCard != null) {
            existingUserCard.setQuantity(existingUserCard.getQuantity() + quantity);
            return userCardRepo.save(existingUserCard);
        }

        UserCard userCard = new UserCard(user, card, quantity);
        return userCardRepo.save(userCard);
    }

    public List<UserCard> getUserCards(Long userId) {
        return userCardRepo.findAllByUserId(userId);
    }

    public List<UserCard> getUserCardsWithMinQuantity(Long userId, int quantity) {
        return userCardRepo.findByUserIdAndQuantityGreaterThan(userId, quantity);
    }

    public Optional<UserCard> getUserCard(Long userId, Long cardId) {
        return Optional.ofNullable(userCardRepo.findByUserIdAndCardId(userId, cardId));
    }

    public void removeUserCard(Long userId, Long cardId) {
        UserCardId id = new UserCardId(userId, cardId);
        userCardRepo.deleteById(id);
    }

    public UserCard updateCardQuantity(Long userId, Long cardId, int quantityToAdd) {
        UserCardId id = new UserCardId(userId, cardId);
        Optional<UserCard> userCardOptional = userCardRepo.findById(id);

        if (userCardOptional.isPresent()) {
            UserCard userCard = userCardOptional.get();
            userCard.setQuantity(userCard.getQuantity() + quantityToAdd);
            return userCardRepo.save(userCard);
        } else {
            Optional<User> userOpt = userService.getUserById(userId);
            if (userOpt.isEmpty()) {
                throw new RuntimeException("Usuario no encontrado con ID: " + userId);
            }
            User user = userOpt.get();

            Card card = cardService.getCardById(cardId);
            if (card == null) {
                throw new RuntimeException("Carta no encontrada con ID: " + cardId);
            }

            UserCard newUserCard = new UserCard(user, card, quantityToAdd);
            return userCardRepo.save(newUserCard);
        }
    }




    public boolean userHasCard(Long userId, Long cardId) {
        return userCardRepo.findByUserIdAndCardId(userId, cardId) != null;
    }

    public List<Card> openPack(Long userId, String rarity) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Card> allCards = cardService.getAllCards();
        List<Card> selectedCards = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Card card = getRandomCardByRarity(allCards, rarity);
            createUserCard(user, card, 1);
            selectedCards.add(card);
        }

        return selectedCards;
    }

    private Card getRandomCardByRarity(List<Card> allCards, String packRarity) {
        Map<String, Double> probabilities = PACK_PROBABILITIES.getOrDefault(packRarity.toUpperCase(), PACK_PROBABILITIES.get("NORMAL"));

        double roll = random.nextDouble();
        double cumulative = 0.0;
        String selectedRarity = "NORMAL"; // fallback

        for (Map.Entry<String, Double> entry : probabilities.entrySet()) {
            cumulative += entry.getValue();
            if (roll <= cumulative) {
                selectedRarity = entry.getKey();
                break;
            }
        }

        List<Card> filtered = filterByRarity(allCards, selectedRarity);

        if (filtered.isEmpty()) {
            // En caso raro de que no haya cartas de la rareza, intenta fallback a cualquier carta
            filtered = allCards;
        }

        return filtered.get(random.nextInt(filtered.size()));
    }

    private List<Card> filterByRarity(List<Card> cards, String rarity) {
        return cards.stream()
                .filter(card -> card.getRarity().name().equalsIgnoreCase(rarity))
                .collect(Collectors.toList());
    }

}
