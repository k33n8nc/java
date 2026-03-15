package nl.cybernetix.restaurant.config;

import lombok.Data;
import nl.cybernetix.restaurant.menu.MenuCategory;
import nl.cybernetix.restaurant.menu.MenuItem;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "restaurant")
@Data
public class RestaurantConfig {
    private MenuCategory menuCategory;
    private List<MenuItem> menuItems;
}