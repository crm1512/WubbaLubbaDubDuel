package com.cybercode.wubbaduel.app.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;
@Entity
@Table(name = "deck_cards")
public class DeckCard implements Serializable{

    @EmbeddedId
    private DeckCardId id;

    @ManyToOne
    @MapsId("deckId")
    @JoinColumn(name = "deck_id", nullable = false)
    private Deck deck;

    @ManyToOne
    @MapsId("cardId")
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    public DeckCard() {}

    public DeckCard(Deck deck, Card card) {
        this.deck = deck;
        this.card = card;
        this.id = new DeckCardId(deck.getId(), card.getId());
    }

    // Getters & setters
    public DeckCardId getId() {
        return id;
    }

    public Deck getDeck() {
        return deck;
    }

    public Card getCard() {
        return card;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setId(DeckCardId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeckCard)) return false;
        DeckCard that = (DeckCard) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
