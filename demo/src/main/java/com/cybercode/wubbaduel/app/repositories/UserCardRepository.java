package com.cybercode.wubbaduel.app.repositories;
import com.cybercode.wubbaduel.app.models.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCardRepository  extends JpaRepository<UserCard, Long> {

    // Buscar todas las cartas de un usuario específico
    List<UserCard> findByUserId(Long userId);

    // Buscar todas las cartas que un usuario tiene en una cantidad mayor a un valor
    List<UserCard> findByUserIdAndQuantityGreaterThan(Long userId, int quantity);

    // Buscar un UserCard específico de un usuario con una carta específica
    UserCard findByUserIdAndCardId(Long userId, Long cardId);
}