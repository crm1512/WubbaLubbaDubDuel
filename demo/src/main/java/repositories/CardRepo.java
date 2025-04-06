package repositories;


import models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepo extends JpaRepository<Card, Long> {
    // Buscar cartas por nombre
    List<Card> findByName(String name);

    // Buscar cartas por tipo
    List<Card> findByType(String type);

    // Buscar cartas por ID de usuario
    List<Card> findByUserId(Long userId);

    // Buscar cartas por costo mayor a un valor dado
    List<Card> findByCostGreaterThan(int cost);

    // Buscar cartas por rango de costo
    List<Card> findByCostBetween(int minCost, int maxCost);

    // Buscar cartas por nombre o descripción que contengan el término dado
    List<Card> findByNameContainingOrDescriptionContaining(String name, String description);
}
