package com.cybercode.wubbaduel.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Representa un usuario que colecciona cartas.
 * Un usuario puede tener múltiples copias de la misma carta.
 */

@Entity // Indica que la clase es una entidad JPA, lo que significa que se mapeará a una tabla en la base de datos.
@Table(name = "users") // Especifica el nombre de la tabla en la base de datos.
@Data // Lombok genera automáticamente los métodos getters, setters, toString, equals, y hashCode.
@NoArgsConstructor //Constructores automaticos
@AllArgsConstructor
public class User {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define la estrategia para la generación automática del valor del campo (auto-incremental).
    private Long  id; // Identificador único del usuario.

    private String username; 

    private String email; 

    private String hashedPassword; 

    // Relación 1 a muchos con la entidad UserCard (representa las cartas del usuario)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserCard> userCards; // Lista de objetos UserCard que representan las cartas del usuario.

    // Relación 1 a muchos con la entidad Deck (representa los mazos del usuario)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Deck> decks; // Lista de mazos que el usuario tiene.

}
