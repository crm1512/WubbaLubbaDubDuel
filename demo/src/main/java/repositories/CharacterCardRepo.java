package repositories;


import models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterCardRepo extends JpaRepository<Card, Long> {
}
