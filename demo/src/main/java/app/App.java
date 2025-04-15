package app;

import models.*;
import repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages = {"app", "repositories", "models"})
@EnableJpaRepositories(basePackages = "repositories")
@EntityScan(basePackages = "models")
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class, args);

    }
    @Bean
    public CommandLineRunner init(CardRepository cardRepository) {
        return args -> {
            if (cardRepository.count() == 0) { // Solo para no duplicar si reinicias
                Card item = new ItemCard(
                        null,
                        "Espada",
                        "Espada de fuego",
                        "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                        "Causa 2 de daño"
                );
                cardRepository.save(item);
                System.out.println("Carta de prueba guardada en la base de datos");
            }
        };
    }

}
