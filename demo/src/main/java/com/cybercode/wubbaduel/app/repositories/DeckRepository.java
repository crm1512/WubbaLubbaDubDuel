package com.cybercode.wubbaduel.app.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cybercode.wubbaduel.app.models.*;
import java.util.List;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
    // Buscar todos los mazos de un usuario
    List<Deck> findByUserId(Long userId);

    // Buscar un mazo por su nombre y usuario
    List<Deck> findByUserIdAndName(Long userId, String name);
}
