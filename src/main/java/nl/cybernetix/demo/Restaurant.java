package nl.cybernetix.demo;

import nl.cybernetix.demo.actors.Waitress;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class Restaurant implements CommandLineRunner {

    private final Waitress waitress;

    // De run method is verantwoordelijk voor het starten van de applicatie
    // Het restaurant moet echt enkel restaurant dingen doen.
    // injecteer runner dus in applicatie en niet hier
    @Override
    public void run(String... args) {
        System.out.println("Restaurant is open! Starting the process...");
        waitress.setName("Jessica");
        waitress.takeOrder();
    }

}
