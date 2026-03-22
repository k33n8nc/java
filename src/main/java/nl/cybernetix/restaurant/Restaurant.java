package nl.cybernetix.restaurant;

import nl.cybernetix.restaurant.config.RestaurantConfig;
import nl.cybernetix.restaurant.staff.Waitress;
import nl.cybernetix.restaurant.menu.MenuFactory;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class Restaurant {

    private final Waitress waitress;
    private final MenuFactory menuFactory;
    private final RestaurantConfig restaurantConfig;

    public void open() {
        System.out.println("Restaurant is open! Starting the process...");
        waitress.setName("Jessica");
        waitress.setMenu(menuFactory.createMenu(restaurantConfig.getMenuCategory()));

        while(true){
            waitress.takeOrder();
        }

    }

}
