package nl.cybernetix.demo;

import nl.cybernetix.demo.actors.Customer;
import nl.cybernetix.demo.actors.Waitress;
import nl.cybernetix.demo.events.OrderCookedEvent;
import nl.cybernetix.demo.events.OrderServedEvent;
import nl.cybernetix.demo.items.MenuItem;
import nl.cybernetix.demo.utils.Communicator;
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

@SpringBootTest(classes = Application.class)
@RecordApplicationEvents
public class RestaurantIT {
    @Autowired
    private Restaurant restaurant;

    @Autowired
    private Waitress waitress;

    @Autowired
    private ApplicationEvents applicationEvents;

    @MockitoBean
    private Communicator communicator;

    @MockitoBean
    private Customer customer;

    @Test
    void testRestaurantIsReady() {
        when(communicator.askYesNoQuestion(anyString())).thenReturn(true);

        restaurant.open();

        verify(communicator, atLeastOnce()).askYesNoQuestion(anyString());
    }

    @Test
    void takeOrderTriggersCookOrderEvent() {
        when(communicator.askYesNoQuestion(anyString())).thenReturn(true);

        waitress.setName("Jessica");
        waitress.takeOrder();

        OrderCookedEvent cookedEvent = applicationEvents.stream(OrderCookedEvent.class)
                .findFirst()
                .orElse(null);

        assertThat(cookedEvent)
                .isNotNull();

        assertThat(cookedEvent.getOrder().getItems())
                .extracting(MenuItem::getName)
                .containsExactlyInAnyOrder("Spaghetti Bolognese", "Margherita Pizza", "Caesar Salad");

    }

    @Test
    void takeOrderTriggersServedOrderEvent() {
        when(communicator.askYesNoQuestion(anyString())).thenReturn(true);

        waitress.setName("Jessica");
        waitress.takeOrder();

        ArgumentCaptor<OrderServedEvent> eventCaptor = ArgumentCaptor.forClass(OrderServedEvent.class);
        verify(customer, times(1)).sayThanks(eventCaptor.capture());

        OrderServedEvent servedEvent = eventCaptor.getValue();
        assertThat(servedEvent.getOrder().getItems())
                .extracting(MenuItem::getName)
                .containsExactlyInAnyOrder("Spaghetti Bolognese", "Margherita Pizza", "Caesar Salad");
    }
}
