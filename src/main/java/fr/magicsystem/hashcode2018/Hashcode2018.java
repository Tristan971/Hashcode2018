package fr.magicsystem.hashcode2018;

import fr.magicsystem.hashcode2018.processing.Processor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hashcode2018 {

    private static final String INPUT_FILE = "/Users/tristan/Desktop/b.in";
    private static final String OUTPUT_FILE = "/Users/tristan/Desktop/b.out";

    public static void main(String[] args) {
        final Processor processor = SpringApplication.run(Hashcode2018.class, args).getBean(Processor.class);
        processor.processInput(INPUT_FILE, OUTPUT_FILE);
    }

}
