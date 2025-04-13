package com.cybercode.wubbaduel.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Representa la relación entre un usuario y una carta.
 * Permite almacenar la cantidad de copias que un usuario tiene de una carta específica.
 */
@Entity // Indica que esta clase es una entidad JPA y se mapeará a una tabla en la base de datos.
@Table(name = "user_cards") // Especifica el nombre de la tabla en la base de datos.
@Data // Lombok genera automáticamente los métodos getters, setters, toString, equals, y hashCode.
public class UserCard {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el valor del campo, normalmente utilizado para auto-incremento.
    private Long id; // Identificador único de la relación.

    @ManyToOne // Define una relación de muchos a uno con la entidad User.
    @JoinColumn(name = "user_id") // Indica que la columna "user_id" será la que guarde la relación con el usuario.
    private User user; // El usuario propietario de la carta.

    @ManyToOne // Define una relación de muchos a uno con la entidad Card.
    @JoinColumn(name = "card_id") // Indica que la columna "card_id" será la que guarde la relación con la carta.
    private Card card; // La carta que tiene el usuario.

    private int quantity; // Almacena la cantidad de copias de una carta que tiene el usuario.

}
