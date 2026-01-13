package nl.cybernetix.demo.items;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuFactory {
    public Menu createMenu(){
        return new Menu(List.of(
                new MenuItem("1", "Spaghetti Bolognese", 12.5),
                new MenuItem("2", "Margherita Pizza", 10.0),
                new MenuItem("3", "Caesar Salad", 8.75)
        ));
    }
}
