package nl.cybernetix.demo;

import nl.cybernetix.demo.actors.Waitress;
import nl.cybernetix.demo.items.Menu;
import nl.cybernetix.demo.items.MenuFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class, OutputCaptureExtension.class})
class RestaurantTest {

    @Mock
    private Waitress waitress;

    @Mock
    private MenuFactory menuFactory;

    @Mock
    private Menu menu;

    @InjectMocks
    private Restaurant restaurant;

    @Test
    void openRestaurantShouldTriggerTakeOrder(CapturedOutput output) {
        when(menuFactory.createMenu()).thenReturn(menu);

        restaurant.open();

        assertThat(output.getOut()).contains("Restaurant is open! Starting the process...");
        verify(waitress).setName("Jessica");
        verify(menuFactory).createMenu();
        verify(waitress).setMenu(menu);
        verify(waitress).takeOrder();
    }
}
