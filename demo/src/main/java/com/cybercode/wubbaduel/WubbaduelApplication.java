package com.cybercode.wubbaduel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WubbaduelApplication {
    public static void main(String[] args) {
        SpringApplication.run(WubbaduelApplication.class, args);
        System.out.println("Servidor en marcha...");
    }
}
