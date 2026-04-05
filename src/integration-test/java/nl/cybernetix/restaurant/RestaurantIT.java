package nl.cybernetix.restaurant;

import nl.cybernetix.restaurant.menu.MenuCategory;
import nl.cybernetix.restaurant.menu.MenuFactory;
import nl.cybernetix.restaurant.menu.MenuItem;
import nl.cybernetix.restaurant.order.events.OrderCookedEvent;
import nl.cybernetix.restaurant.order.events.OrderServedEvent;
import nl.cybernetix.restaurant.staff.Waitress;
import nl.cybernetix.restaurant.utils.Communicator;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@RecordApplicationEvents
public class RestaurantIT {
    @Autowired
    private Waitress waitress;

    @Autowired
    private MenuFactory menuFactory;

    @Autowired
    private ApplicationEvents applicationEvents;

    @MockitoBean
    private Communicator communicator;

    @MockitoBean
    private Customer customer;

    @Test
    void takeOrderTriggersCookOrderEvent() {
        when(communicator.askYesNoQuestion(anyString())).thenReturn(true);

        waitress.setName("Jessica");
        waitress.setMenu(menuFactory.createMenu(MenuCategory.DINNER));
        waitress.takeOrder();

        OrderCookedEvent cookedEvent = applicationEvents.stream(OrderCookedEvent.class).findFirst().orElse(null);

        assertThat(cookedEvent).isNotNull();

        assertThat(cookedEvent.getOrder().getItems()).extracting(MenuItem::getName).containsExactlyInAnyOrder("Spaghetti Bolognese", "Margherita Pizza", "Caesar Salad");

    }

    @Test
    void takeOrderTriggersServedOrderEvent() {
        when(communicator.askYesNoQuestion(anyString())).thenReturn(true);

        waitress.setName("Jessica");
        waitress.setMenu(menuFactory.createMenu(MenuCategory.DINNER));
        waitress.takeOrder();

        ArgumentCaptor<OrderServedEvent> eventCaptor = ArgumentCaptor.forClass(OrderServedEvent.class);
        verify(customer, times(1)).sayThanks(eventCaptor.capture());

        OrderServedEvent servedEvent = eventCaptor.getValue();
        assertThat(servedEvent.getOrder().getItems()).extracting(MenuItem::getName).containsExactlyInAnyOrder("Spaghetti Bolognese", "Margherita Pizza", "Caesar Salad");
    }
}
