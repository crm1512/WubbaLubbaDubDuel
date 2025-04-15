package models;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Deck {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany
    @JoinTable (name = "deck_cards",
            joinColumns = @JoinColumn(name = "deck_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id"))
    private Set<Card> cards = new HashSet<>();

    // Constructor
    public Deck() {
        this.cards = new HashSet<>();
    }

    // Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Set<Card> getCards() {
        return cards;
    }
    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    // Methods
    public int getNumberOfCards() {
        return cards.size();
    }
    public void addCard(Card card) {
        cards.add(card);
    }
    public void removeCard(Card card) {
        cards.remove(card);
    }
}