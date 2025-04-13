package com.cybercode.wubbaduel.controller;

import com.cybercode.wubbaduel.model.CharacterCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cybercode.wubbaduel.service.CharacterCardService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/character-cards")
public class CharacterCardController {

    private final CharacterCardService characterCardService;

    @Autowired
    public CharacterCardController(CharacterCardService characterCardService) {
        this.characterCardService = characterCardService;
    }

    // Obtener todas las cartas de personajes
    @GetMapping
    public List<CharacterCard> getAllCharacterCards() {
        return characterCardService.getAllCharacterCards();
    }

    // Obtener una carta de personaje por su ID
    @GetMapping("/{id}")
    public Optional<CharacterCard> getCharacterCardById(@PathVariable Long id) {
        return characterCardService.getCharacterCardById(id);
    }

    // Buscar cartas de personajes por nombre (contiene)
    @GetMapping("/search")
    public List<CharacterCard> getCharacterCardsByName(@RequestParam String name) {
        return characterCardService.getCharacterCardsByName(name);
    }

    // Buscar cartas con costo mayor al especificado
    @GetMapping("/cost-greater-than")
    public List<CharacterCard> getCharacterCardsByCost(@RequestParam int cost) {
        return characterCardService.getCharacterCardsByCostGreaterThan(cost);
    }
}
