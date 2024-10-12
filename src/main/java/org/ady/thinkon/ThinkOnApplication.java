package org.ady.thinkon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThinkOnApplication {

    public static void main(String[] args) {
        System.out.println("Initializing...");
        SpringApplication.run(ThinkOnApplication.class, args);
    }
}
