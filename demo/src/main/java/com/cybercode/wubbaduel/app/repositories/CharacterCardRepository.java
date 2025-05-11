package com.cybercode.wubbaduel.app.repositories;


import com.cybercode.wubbaduel.app.models.CharacterCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterCardRepository extends JpaRepository<CharacterCard, Long> {
    // Buscar cartas de personajes por nombre
    List<CharacterCard> findByNameContaining(String name);

    // Buscar cartas de personajes por costo
    List<CharacterCard> findByCostGreaterThan(int cost);

    // Buscar todas las cartas de personajes de un usuario
    //List<CharacterCard> findByUser_Id(Long userId);
}
