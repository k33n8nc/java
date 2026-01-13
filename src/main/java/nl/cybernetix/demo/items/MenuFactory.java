package nl.cybernetix.demo.items;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MenuFactory {
    @Bean
    public Menu createMenu() {
        Menu menu = new Menu();
        menu.addMenuItem(new MenuItem("1", "Spaghetti Bolognese", 12.50));
        menu.addMenuItem(new MenuItem("2", "Margherita Pizza", 10.00));
        menu.addMenuItem(new MenuItem("3", "Caesar Salad", 8.75));
        menu.addMenuItem(new MenuItem("4", "Tiramisu", 6.00));
        return menu;
    }
}
