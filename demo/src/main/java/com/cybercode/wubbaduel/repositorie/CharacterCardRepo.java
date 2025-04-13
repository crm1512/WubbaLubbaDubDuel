package com.cybercode.wubbaduel.repositorie;


import com.cybercode.wubbaduel.model.CharacterCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterCardRepo extends JpaRepository<CharacterCard, Long> {
	// Buscar cartas de personajes por nombre
    List<CharacterCard> findByNameContaining(String name);

    // Buscar cartas de personajes por costo
    List<CharacterCard> findByCostGreaterThan(int cost);

    // Buscar todas las cartas de personajes de un usuario
    List<CharacterCard> findByUserId(Long userId);
}