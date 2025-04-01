package models;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    //Attributes
    private int id;//PK UNIQUE (puede que tengamos que meter aqui el PK de User)
    private List<Card> cards;

    // Constructor
    public Deck() {
        this.cards = new ArrayList<>();
    }

    // Getter
    public List<Card> getCards() {
        return cards;
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