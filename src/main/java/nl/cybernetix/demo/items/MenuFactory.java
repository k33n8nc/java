package nl.cybernetix.demo.items;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuFactory {
    public Menu createMenu(){
        return new Menu(List.of(
                new MenuItem("1", "Croissant", 3.00, List.of(FoodCategory.BREAKFAST)),
                new MenuItem("2", "Omelet", 4.50, List.of(FoodCategory.BREAKFAST, FoodCategory.LUNCH)),
                new MenuItem("3", "Baguette", 7.00, List.of(FoodCategory.LUNCH)),
                new MenuItem("4", "Spaghetti Bolognese", 12.5, List.of(FoodCategory.DINNER)),
                new MenuItem("5", "Margherita Pizza", 10.0, List.of(FoodCategory.DINNER)),
                new MenuItem("6", "Caesar Salad", 8.75, List.of(FoodCategory.BREAKFAST, FoodCategory.DINNER))
        ));
    }
}
