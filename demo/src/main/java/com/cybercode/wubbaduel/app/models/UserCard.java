package com.cybercode.wubbaduel.app.models;

import jakarta.persistence.*;
@Entity
@Table(name = "user_cards")
public class UserCard {

    // Attributes
    @EmbeddedId
    private UserCardId id;

    @ManyToOne
    @MapsId("userId")
    private User user;
    @ManyToOne
    @MapsId("cardId")
    private Card card;
    @Column(name = "quantity")
    private int quantity;

    // Constructor
    public UserCard() {}

    public UserCard(User user, Card card, int quantity) {
        this.user = user;
        this.card = card;
        this.quantity = quantity;
        this.id = new UserCardId(user.getId(), card.getId());
    }


    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
