package models;

import jakarta.persistence.*;
import lombok.*;

/**
 * Representa una carta de personaje en el juego.
 * Esta clase extiende de `Card` y agrega atributos específicos para las cartas de personajes.
 */
@Entity // Indica que esta clase se mapea a una tabla en la base de datos.
@Table(name = "character_cards") // Especifica el nombre de la tabla en la base de datos.
@Data // Lombok genera automáticamente los métodos getters, setters, toString, equals, y hashCode.
@NoArgsConstructor //Constructores automaticos
@AllArgsConstructor 
public class CharacterCard extends Card {

    private int attack; // Poder de ataque de la carta de personaje.

    private int health; // Salud del personaje de la carta.

    // Constructor que hereda de la clase base Card
    public CharacterCard(String name, String description, int cost, String image, int attack, int health, User user) {
		super(name, description, cost, image, user); // Llama al constructor de la clase `Card`
        this.attack = attack;
        this.health = health;
    }

}
