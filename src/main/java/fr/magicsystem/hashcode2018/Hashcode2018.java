package fr.magicsystem.hashcode2018;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Hashcode2018 {

    private static ConfigurableApplicationContext CTX;

    public static void main(String[] args) {
        CTX = SpringApplication.run(Hashcode2018.class, args);
    }

}
