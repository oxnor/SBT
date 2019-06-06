package ru.shaa.sbt.shoppingmngr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.shaa.sbt.shoppingmngr.config.JpaConfig;

@SpringBootApplication
public class ShoppingmngrApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Class<?>[] {ShoppingmngrApplication.class, JpaConfig.class}, args);
    }

}
