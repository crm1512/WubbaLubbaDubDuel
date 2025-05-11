package com.cybercode.wubbaduel.app.service;
import com.cybercode.wubbaduel.app.models.Card;
import com.cybercode.wubbaduel.app.models.User;
import com.cybercode.wubbaduel.app.models.UserCard;
import com.cybercode.wubbaduel.app.repositories.UserCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCardService {

    private final UserCardRepository userCardRepo;

    @Autowired
    public UserCardService(UserCardRepository userCardRepo) {
        this.userCardRepo = userCardRepo;
    }

    // Crear una nueva relación entre un usuario y una carta
    public UserCard createUserCard(User user, Card card, int quantity) {
        // Verifica si ya existe una relación con el usuario y la carta
        UserCard existingUserCard = userCardRepo.findByUserIdAndCardId(user.getId(), card.getId());

        if (existingUserCard != null) {
            // Si existe, solo actualizamos la cantidad
            existingUserCard.setQuantity(existingUserCard.getQuantity() + quantity);
            return userCardRepo.save(existingUserCard);
        }

        // Si no existe, creamos una nueva relación
        UserCard userCard = new UserCard();
        userCard.setUser(user);  // Asignamos el objeto User directamente
        userCard.setCard(card);  // Asignamos el objeto Card directamente
        userCard.setQuantity(quantity);
        return userCardRepo.save(userCard);
    }

    // Obtener todas las cartas de un usuario por el ID del usuario
    public List<UserCard> getUserCards(Long userId) {
        return userCardRepo.findAllByUserId(userId);
    }

    // Obtener cartas de un usuario con cantidad mayor a un valor específico
    public List<UserCard> getUserCardsWithMinQuantity(Long userId, int quantity) {
        return userCardRepo.findByUserIdAndQuantityGreaterThan(userId, quantity);
    }

    // Obtener una carta de un usuario específica por ID de usuario y ID de carta
    public Optional<UserCard> getUserCard(Long userId, Long cardId) {
        return Optional.ofNullable(userCardRepo.findByUserIdAndCardId(userId, cardId));
    }

    // Eliminar una carta de un usuario por su ID
    public void removeUserCard(Long userCardId) {
        userCardRepo.deleteById(userCardId);
    }

    // Verificar si un usuario tiene una carta específica
    public boolean userHasCard(Long userId, Long cardId) {
        return userCardRepo.findByUserIdAndCardId(userId, cardId) != null;
    }

    // Actualizar la cantidad de una carta de un usuario
    public UserCard updateCardQuantity(Long userCardId, int newQuantity) {
        Optional<UserCard> userCardOptional = userCardRepo.findById(userCardId);
        if (userCardOptional.isPresent()) {
            UserCard userCard = userCardOptional.get();
            userCard.setQuantity(newQuantity);
            return userCardRepo.save(userCard);
        } else {
            throw new RuntimeException("Carta no encontrada.");
        }
    }
}
