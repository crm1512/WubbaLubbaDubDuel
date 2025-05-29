package com.cybercode.wubbaduel.app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DeckCardId implements Serializable {

    @Column(name = "deck_id")
    private Long deckId;

    @Column(name = "card_id")
    private Long cardId;

    public DeckCardId() {}

    public DeckCardId(Long deckId, Long cardId) {
        this.deckId = deckId;
        this.cardId = cardId;
    }

    // Getters, setters, equals, hashCode


    public Long getDeckId() {
        return deckId;
    }

    public void setDeckId(Long deckId) {
        this.deckId = deckId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeckCardId)) return false;
        DeckCardId that = (DeckCardId) o;
        return Objects.equals(deckId, that.deckId) &&
                Objects.equals(cardId, that.cardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deckId, cardId);
    }
}
