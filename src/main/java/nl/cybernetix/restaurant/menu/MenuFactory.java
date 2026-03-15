package nl.cybernetix.restaurant.menu;

import lombok.RequiredArgsConstructor;
import nl.cybernetix.restaurant.config.RestaurantConfig;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuFactory {

    private final RestaurantConfig restaurantConfig;

    public Menu createMenu(MenuCategory category){

        List<MenuItem> chosenMenu = restaurantConfig.getMenuItems().stream()
                .filter(menuItem -> menuItem.getCategories().contains(category))
                .toList();

        return new Menu(chosenMenu);
    }
}
