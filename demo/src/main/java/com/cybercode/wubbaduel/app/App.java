package com.cybercode.wubbaduel.app;

import com.cybercode.wubbaduel.app.models.*;
import com.cybercode.wubbaduel.app.repositories.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages = {"com.cybercode.wubbaduel.app"})
@EnableJpaRepositories(basePackages = "com.cybercode.wubbaduel.app.repositories")
@EntityScan(basePackages = "com.cybercode.wubbaduel.app.models")
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }
}
