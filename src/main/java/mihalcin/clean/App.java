package mihalcin.clean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    // Entity
    // e.g. Person entity.. if I change some attributes over time, I'm still person.. my identity was not changed

    // Value object
    // e.g. phone number.. if I have 2 instances with the same phone number, it is the same value object

    // Aggregate
    // I can include multiple entities and value objects into an aggregate and hide these internals from other client code

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
