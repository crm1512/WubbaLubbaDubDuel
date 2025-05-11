package com.cybercode.wubbaduel.app.controllers;
import com.cybercode.wubbaduel.app.models.LoginDTO;
import com.cybercode.wubbaduel.app.models.LoginRequest;
import com.cybercode.wubbaduel.app.models.RegisterRequest;
import org.springframework.http.ResponseEntity;
import com.cybercode.wubbaduel.app.models.User;
import com.cybercode.wubbaduel.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // si usas frontend externo
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try{
            User user = authService.register(
                    request.getName(),
                    request.getLastname(),
                    request.getUsername(),
                    request.getPassword(),
                    request.getEmail()
            );

            //Utilizo el dto de login porque creo que nos valdrá con el mismo
            LoginDTO dto = new LoginDTO(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getAvatar(), // podemos poner un default
                    user.getTokens()  // podemos poner un default
            );

            return ResponseEntity.ok(dto);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest request) {
        Optional<User> user = authService.login(request.getEmail(), request.getPassword());
        if (user.isPresent()) {
            User u = user.get();
            LoginDTO dto = new LoginDTO(
                    u.getId(),
                    u.getUsername(),
                    u.getEmail(),
                    u.getAvatar(),
                    u.getTokens()
            );
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}
