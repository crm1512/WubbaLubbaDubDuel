package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "email", unique = true)
    private String email;
    @ManyToMany
    private Set<Card> cards = new HashSet<>();
    @ManyToMany
    private Set<Deck> decks = new HashSet<>();


    // Constructor
    public User(String username, String password, String email) {
        this.username = username;
        this.email = email;
        this.cards = new HashSet<>();
        this.decks = new HashSet<>();
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public Set<Deck> getDecks() {
        return decks;
    }

    public void setDecks(Set<Deck> decks) {
        this.decks = decks;
    }


    // Methods to manage cards and decks
    public void addCard(Card card, Deck deck) {
        cards.add(card);
        deck.addCard(card);
    }

    public void removeCard(Card card, Deck deck) {
        cards.remove(card);
        deck.removeCard(card);
    }

    public void addDeck(Deck deck) {
        decks.add(deck);
    }

    public void removeDeck(Deck deck) {
        decks.remove(deck);
    }
}