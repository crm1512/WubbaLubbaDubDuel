package com.cybercode.wubbaduel.app.repositories;

import com.cybercode.wubbaduel.app.models.Card;
import com.cybercode.wubbaduel.app.models.Deck;
import com.cybercode.wubbaduel.app.models.DeckCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeckCardRepository extends JpaRepository<DeckCard, Long> {

    List<DeckCard> findByDeck(Deck deck);

    void deleteByDeckAndCard(Deck deck, Card card);

    boolean existsByDeckAndCard(Deck deck, Card card);

    long countByDeck(Deck deck);


}
