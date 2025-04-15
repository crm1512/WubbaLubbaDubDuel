package com.cybercode.wubbaduel.app.models;
import jakarta.persistence.*;

@Entity
public class BoardCard extends Card {

    // Atributtes
    @Column(name = "effect")
    private String effect;
    @Column(name = "card_condition")
    private String condition;

    // Constructor
    public BoardCard() {
    }

    public BoardCard(Long id, String name, String description, String image, String effect, String condition) {
        super(id, name, description, image);
        this.effect = effect;
        this.condition = condition;
    }

    // Getters & Setters

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

}
