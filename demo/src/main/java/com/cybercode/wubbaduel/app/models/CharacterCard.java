package com.cybercode.wubbaduel.app.models;
import jakarta.persistence.*;
@Entity
public class CharacterCard extends Card {

    // Atributtes
    @Column(name = "hp")
    private int hp;
    @Column(name = "attack")
    private int attack;

    @Column(name = "gender")
    private String gender;

    @Column(name = "specie")
    private String specie;

    @Column(name = "origin")
    private String origin;

    //Constructor
    public CharacterCard() {
    }

    public CharacterCard(Long id, String name, String description, String image, int cost, Rarity rarity, Type type,
                         String gender, String specie, String origin, int hp, int attack) {
        super(id, name, description, image, cost, rarity, type);
        this.hp = hp;
        this.attack = attack;
        this.gender = gender;
        this.specie = specie;
        this.origin = origin;
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

    public String getGender(){
        return  gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String getSpecie(){
        return specie;
    }

    public void setSpecie(String specie){
        this.specie = specie;
    }

    public String getOrigin(){
        return origin;
    }

    public void setOrigin(String origin){
        this.origin = origin;
    }

}
