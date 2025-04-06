package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    // Puedes agregar consultas personalizadas si es necesario, por ejemplo, encontrar por nombre de usuario
    User findByUsername(String username);
}