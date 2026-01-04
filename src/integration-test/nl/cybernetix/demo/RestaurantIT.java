package nl.cybernetix.demo;

import nl.cybernetix.demo.actors.Customer;
import nl.cybernetix.demo.actors.Waitress;
import nl.cybernetix.demo.events.OrderCookedEvent;
import nl.cybernetix.demo.events.OrderServedEvent;
import nl.cybernetix.demo.items.Menu;
import nl.cybernetix.demo.utils.Communicator;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
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
    private ApplicationEvents applicationEvents;

    @MockitoBean
    private Communicator communicator;

    @MockitoSpyBean
    private Customer customer;

    @Test
    void takeOrderTriggersCookOrderEvent() {
        when(communicator.askYesNoQuestion(anyString())).thenReturn(true);
        waitress.setName("Jessica");

        waitress.takeOrder();

        OrderCookedEvent event = applicationEvents.stream(OrderCookedEvent.class)
                .findFirst()
                .orElse(null);

        assertThat(event)
                .isNotNull();

        assertThat(event.getOrder().getOrder())
                .containsExactlyInAnyOrder(Menu.starter, Menu.mainCourse, Menu.dessert);

    }

    @Test
    void takeOrderTriggersServedOrderEvent() {
        waitress.setName("Jessica");
        when(communicator.askYesNoQuestion("Jessica: Would you like a " + Menu.starter + "?")).thenReturn(true);
        when(communicator.askYesNoQuestion("Jessica: Would you like a " + Menu.mainCourse + "?")).thenReturn(false);
        when(communicator.askYesNoQuestion("Jessica: Would you like a " + Menu.dessert + "?")).thenReturn(true);

        waitress.takeOrder();

        ArgumentCaptor<OrderServedEvent> eventCaptor = ArgumentCaptor.forClass(OrderServedEvent.class);
        verify(customer, times(1)).sayThanks(eventCaptor.capture());

        OrderServedEvent servedEvent = eventCaptor.getValue();
        assertThat(servedEvent.getOrder().getOrder()).containsExactly(Menu.starter, Menu.dessert);
    }
}
