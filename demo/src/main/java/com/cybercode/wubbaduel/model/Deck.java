package com.cybercode.wubbaduel.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Representa un mazo de cartas de un usuario.
 * Un mazo puede contener múltiples cartas, y un usuario puede tener varios mazos.
 */

@Entity // Esta clase se mapea a una tabla en la base de datos.
@Table(name = "decks") // Especifica el nombre de la tabla en la base de datos.
@Data // Lombok genera automáticamente los métodos getters, setters, toString, equals, y hashCode.
@NoArgsConstructor //Constructores automaticos
@AllArgsConstructor 
public class Deck {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el valor del campo.
    private Long id; 

    private String name; 

    // Relación muchos a uno con el usuario
    @ManyToOne
    @JoinColumn(name = "user_id") // Establece la columna "user_id" para la relación con el usuario.
    private User user; // El usuario dueño de este mazo.

    // Relación muchos a muchos con las cartas a través de UserCard
    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserCard> userCards; // Lista de cartas en este mazo (relación con UserCard).

    // Método para agregar una carta al mazo
    public void addCard(Card card, int quantity) {
        UserCard userCard = new UserCard();
        userCard.setCard(card);
        userCard.setUser(this.user);
        userCard.setQuantity(quantity);
        this.userCards.add(userCard);
    }

    // Método para eliminar una carta del mazo
    public void removeCard(Card card) {
        this.userCards.removeIf(userCard -> userCard.getCard().equals(card));
    }
}
