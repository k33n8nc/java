package nl.cybernetix.demo;

import nl.cybernetix.demo.actors.Waitress;
import nl.cybernetix.demo.items.MenuFactory;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class Restaurant {

    private final Waitress waitress;
    private final MenuFactory menuFactory;

    public void open() {
        System.out.println("Restaurant is open! Starting the process...");
        waitress.setName("Jessica");
        waitress.setMenu(menuFactory.createMenu());
        waitress.takeOrder();
    }

}
