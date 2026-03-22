package nl.cybernetix.restaurant.config;

import nl.cybernetix.restaurant.Restaurant;
import nl.cybernetix.restaurant.menu.MenuCategory;
import nl.cybernetix.restaurant.menu.MenuItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class RestaurantConfigIT {

    @MockitoBean
    private Restaurant restaurant; // voorkom dat application wordt gestart

    @Autowired
    private RestaurantConfig restaurantConfig;

    @Test
    public void testConfigFromYaml() {
        MenuCategory menuCategory = restaurantConfig.getMenuCategory();
        List<MenuItem> menuItems = restaurantConfig.getMenuItems();

        // deterministisch maken

        menuItems.getFirst().setName("FirstMenuItem");

        assertNotNull(restaurantConfig);
        assertEquals(MenuCategory.BREAKFAST, menuCategory);
        assertNotNull(menuItems);

        assertEquals("FirstMenuItem", menuItems.getFirst().getName());
    }
}
