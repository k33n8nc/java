package nl.cybernetix.restaurant.menu;

import nl.cybernetix.restaurant.config.RestaurantConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MenuFactoryTest {
    @Mock
    private RestaurantConfig restaurantConfig;

    @InjectMocks
    private MenuFactory menuFactory;

    @Test
    public void createMenuTest() {
        List<MenuItem> chosenMenu = new ArrayList<>();

        chosenMenu.add(new MenuItem("1", "Omelet", 4.50, List.of(MenuCategory.BREAKFAST)));

        when(restaurantConfig.getMenuItems()).thenReturn(chosenMenu);

        Menu menu = menuFactory.createMenu(MenuCategory.BREAKFAST);

        assertThat(menu.getMenuItems()).isEqualTo(chosenMenu);
    }

}
