package models;

import jakarta.persistence.*;
import lombok.*;

/**
 * Representa una carta genérica en el juego. Esta clase es abstracta y se
 * extenderá por diferentes tipos de cartas (por ejemplo, CharacterCard,
 * SpellCard, etc.).
 */

@MappedSuperclass // Indica que esta clase es una clase base que no se mapea directamente a una
					// tabla, pero sus campos se heredan por otras entidades.
@Data // Lombok genera automáticamente los métodos getters, setters, toString, equals,
		// y hashCode.
@NoArgsConstructor // Constructores automaticos
@AllArgsConstructor
public abstract class Card {

	@Id // Define que este campo es la clave primaria de la tabla.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el valor del campo, normalmente
														// utilizado para auto-incremento.
	private Long  id;

	private String name;

	private String description;

	private int cost;

	private String image;

	// Relación con el usuario
	@ManyToOne // Muchas cartas pueden pertenecer a un solo usuario.
	@JoinColumn(name = "user_id") // Nombre de la columna en la tabla de cartas para almacenar la relación con el
									// usuario.
	private User user; // El usuario que posee esta carta.

	public Card(String name, String description, int cost, String image, User user) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.image = image;
        this.user = user;
    }
	
}
