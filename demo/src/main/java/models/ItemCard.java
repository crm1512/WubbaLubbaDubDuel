package models;
import jakarta.persistence.*;

@Entity
public class ItemCard extends Card{
    // Atributtes
    @Column(name = "effect")
    private String effect;

    //Constructor
    public ItemCard() {
    }

    public ItemCard(Long id, String name, String description, String image, String effect){
        super(id, name, description, image);

        this.effect = effect;
    }

    // Getters & Setters

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

}
