package com.cybercode.wubbaduel.app.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "deck")
public class Deck implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @Column(name = "deck_name")
    private String name;

    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DeckCard> deckCards = new HashSet<>();

    // Constructor
    public Deck() {
        this.deckCards = new HashSet<>();
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DeckCard> getDeckCards() {
        return deckCards;
    }

    public void setDeckCards(Set<DeckCard> deckCards) {
        this.deckCards = deckCards;
    }

    // Métodos de utilidad
    public void addCard(Card card) {
        DeckCard deckCard = new DeckCard(this, card);
        this.deckCards.add(deckCard);
    }

    public void removeCard(Card card) {
        this.deckCards.removeIf(dc -> dc.getCard().equals(card));
    }

    public int getNumberOfCards() {
        return deckCards.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deck)) return false;
        Deck deck = (Deck) o;
        return id != null && id.equals(deck.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
