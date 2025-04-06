package repositories;

import models.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepo extends JpaRepository<Deck, Long> {
	
    List<Deck> findByUserId(Long userId);
}
