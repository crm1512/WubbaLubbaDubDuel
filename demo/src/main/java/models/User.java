package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class User {
    // Attributes
    private int id;
    private String username;
    private String email;
    private String hashedPassword;
    private List<Card> cards;
    private List<Deck> decks;

    // Constructor
    public User(String username, String password, String email) {
        this.username = username;
        this.email = email;
        this.hashedPassword = hashPassword(password);
        this.cards = new ArrayList<>();
        this.decks = new ArrayList<>();
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

    public String getHashedPassword() {
        return hashedPassword;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Deck> getDecks() {
        return decks;
    }

    public void setDecks(List<Deck> decks) {
        this.decks = decks;
    }

    // Method to hash the password
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
        return password;
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