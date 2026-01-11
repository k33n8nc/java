package nl.cybernetix.demo;

import nl.cybernetix.demo.actors.Waitress;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class Restaurant {

    private final Waitress waitress;

    public void open() {
        System.out.println("Restaurant is open! Starting the process...");
        waitress.setName("Jessica");
        waitress.takeOrder();
    }

}
