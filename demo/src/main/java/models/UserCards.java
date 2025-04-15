package models;
import jakarta.persistence.*;
@Entity
public class UserCards {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne Card card;
    @Column(name = "quantity")
    private int quantity;
}
