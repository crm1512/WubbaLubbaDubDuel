package com.cybercode.wubbaduel.app.repositories;

import com.cybercode.wubbaduel.app.models.Deck;
import com.cybercode.wubbaduel.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {

    Optional<Deck> findByUserAndName(User user, String name);

}
