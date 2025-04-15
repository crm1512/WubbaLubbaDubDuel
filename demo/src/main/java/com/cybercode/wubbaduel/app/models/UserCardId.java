package com.cybercode.wubbaduel.app.models;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

public class UserCardId implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "card_id")
    private Long cardId;

    public UserCardId() {}

    public UserCardId(Long userId, Long cardId) {
        this.userId = userId;
        this.cardId = cardId;
    }

    // Getters, setters, equals y hashCode obligatorios
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        if (!(o instanceof UserCardId)) return false;
        UserCardId that = (UserCardId) o;
        return userId == that.userId && Objects.equals(cardId, that.cardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, cardId);
    }
}

