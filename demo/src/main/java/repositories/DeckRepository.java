package repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import models.*;
@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {

}
