package repositories;

import models.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepo extends JpaRepository<Deck, Long> {
	
	// Buscar todos los mazos de un usuario
    List<Deck> findByUserId(Long userId);

    // Buscar un mazo por su nombre y usuario
    List<Deck> findByUserIdAndName(Long userId, String name);
    
}
