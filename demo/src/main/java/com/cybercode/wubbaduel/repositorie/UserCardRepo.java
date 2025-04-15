package com.cybercode.wubbaduel.repositorie;


import com.cybercode.wubbaduel.model.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserCardRepo extends JpaRepository<UserCard, Long> {

    // Buscar todas las cartas de un usuario específico
    List<UserCard> findByUserId(Long userId);

    // Buscar todas las cartas que un usuario tiene en una cantidad mayor a un valor
    List<UserCard> findByUserIdAndQuantityGreaterThan(Long userId, int quantity);

    // Buscar un UserCard específico de un usuario con una carta específica
    UserCard findByUserIdAndCardId(Long userId, Long cardId);
}