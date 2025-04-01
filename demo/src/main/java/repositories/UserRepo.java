package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    // Puedes agregar consultas personalizadas aquí si las necesitas
}
