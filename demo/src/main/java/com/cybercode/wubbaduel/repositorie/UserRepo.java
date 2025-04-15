package com.cybercode.wubbaduel.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cybercode.wubbaduel.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	// Buscar un usuario por su nombre
	User findByUsername(String username);
	
	// Verificar si un usuario con un nombre de usuario específico ya existe
	boolean existsByUsername(String username);
	
	// Verificar si un usuario con un nombre de usuario específico ya existe
	boolean existsByEmail(String email);

	// Buscar un usuario por su correo electrónico
	User findByEmail(String email);
}