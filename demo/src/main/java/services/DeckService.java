package services;

import models.Deck;
import repositories.DeckRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeckService {

    private final DeckRepo deckRepo;

    @Autowired
    public DeckService(DeckRepo deckRepo) {
        this.deckRepo = deckRepo;
    }

    // Crear un nuevo mazo
    public Deck createDeck(Deck deck) {
        return deckRepo.save(deck);
    }

    // Obtener un mazo por su ID
    public Optional<Deck> getDeckById(Long id) {
        return deckRepo.findById(id);
    }

    // Obtener todos los mazos de un usuario
    public List<Deck> getDecksByUserId(Long userId) {
        return deckRepo.findByUserId(userId);
    }

    // Eliminar un mazo por su ID
    public void deleteDeck(Long id) {
        deckRepo.deleteById(id);
    }

    // Actualizar un mazo existente
    public Deck updateDeck(Long id, Deck updatedDeck) {
        Optional<Deck> existingDeckOpt = deckRepo.findById(id);
        if (existingDeckOpt.isPresent()) {
            Deck existingDeck = existingDeckOpt.get();
            existingDeck.setName(updatedDeck.getName());
            existingDeck.setUser(updatedDeck.getUser());
            // Aquí puedes actualizar otros atributos del mazo si es necesario
            return deckRepo.save(existingDeck);
        } else {
            throw new RuntimeException("Deck not found with id: " + id);
        }
    }
}

