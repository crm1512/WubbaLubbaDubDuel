package com.cybercode.wubbaduel.app.models;
import jakarta.persistence.*;
@Entity
public class CharacterCard extends Card {

    // Atributtes
    @Column(name = "hp")
    private int hp;
    @Column(name = "attack")
    private int attack;
    @Column(name = "cost")
    private int cost;

    //Constructor
    public CharacterCard() {
    }

    public CharacterCard(Long id, String name, String description, String image,
                         int hp, int attack, int cost){
        super(id, name, description, image);
        this.hp = hp;
        this.attack = attack;
        this.cost = cost;
    }

    // Getters & Setters
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
