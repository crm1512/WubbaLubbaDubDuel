package models;

public class Card {

    // Atributtes
    private int id;
    private String name;
    private String description;
    private int hp;
    private int attack;
    private String image;
    private int cost;

    //Constructor
    public Card(int id, String name, String description, int hp, int attack, String image, int cost) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hp = hp;
        this.attack = attack;
        this.image = image;
        this.cost = cost;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public int getCost() {
        return cost;
    }
    
    public void setCost(int cost) {
        this.cost = cost;
    }
}