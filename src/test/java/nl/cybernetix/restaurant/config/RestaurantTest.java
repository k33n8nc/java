package nl.cybernetix.restaurant.config;

import nl.cybernetix.restaurant.menu.Menu;
import nl.cybernetix.restaurant.menu.MenuCategory;
import nl.cybernetix.restaurant.menu.MenuFactory;
import nl.cybernetix.restaurant.menu.MenuItem;
import nl.cybernetix.restaurant.staff.Waitress;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class, OutputCaptureExtension.class})
class RestaurantTest {

    @Mock
    private Waitress waitress;

    @Mock
    private MenuFactory menuFactory;

    @Mock
    private RestaurantConfig restaurantConfig;

    @InjectMocks
    private Restaurant restaurant;

    @Test
    void openRestaurantShouldTriggerTakeOrder(CapturedOutput output) {
        restaurantConfig.setMenuCategory(MenuCategory.BREAKFAST);
        restaurantConfig.setMenuItems(List.of(MenuItem.builder().id("1").name("Croissant").price(3.0).build()));
        Menu menu = new Menu(restaurantConfig.getMenuItems());

        waitress.setMenu(menu);
        restaurant.open();

        assertThat(output.getOut()).contains("Restaurant is open! Starting the process...");
        verify(waitress).setName("Jessica");
        verify(waitress).setMenu(menu);
        verify(menuFactory).createMenu(restaurantConfig.getMenuCategory());
        verify(waitress).takeOrder();
    }
}
