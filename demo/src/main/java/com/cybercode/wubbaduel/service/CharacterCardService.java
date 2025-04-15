package com.cybercode.wubbaduel.service;

import com.cybercode.wubbaduel.model.CharacterCard;
import com.cybercode.wubbaduel.repositorie.CharacterCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterCardService {

    private final CharacterCardRepo characterCardRepo;

    @Autowired
    public CharacterCardService(CharacterCardRepo characterCardRepo) {
        this.characterCardRepo = characterCardRepo;
    }

    // Obtener todas las cartas de personajes
    public List<CharacterCard> getAllCharacterCards() {
        return characterCardRepo.findAll();
    }

    // Buscar cartas de personajes por nombre (contiene un término)
    public List<CharacterCard> getCharacterCardsByName(String name) {
        return characterCardRepo.findByNameContaining(name);
    }

    // Buscar cartas de personajes con costo mayor a un valor específico
    public List<CharacterCard> getCharacterCardsByCostGreaterThan(int cost) {
        return characterCardRepo.findByCostGreaterThan(cost);
    }

    // Obtener una carta de personaje por su ID
    public Optional<CharacterCard> getCharacterCardById(Long cardId) {
        return characterCardRepo.findById(cardId);
    }
}
