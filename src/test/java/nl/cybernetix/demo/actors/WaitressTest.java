package nl.cybernetix.demo.actors;

import nl.cybernetix.demo.events.OrderCookedEvent;
import nl.cybernetix.demo.events.OrderServedEvent;
import nl.cybernetix.demo.events.OrderTakenEvent;
import nl.cybernetix.demo.items.Menu;
import nl.cybernetix.demo.items.MenuFactory;
import nl.cybernetix.demo.items.Order;
import nl.cybernetix.demo.items.MenuItem;
import nl.cybernetix.demo.utils.Communicator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WaitressTest {

    @Mock
    private ApplicationEventPublisher publisher;

    @Mock
    private Communicator communicator;

    @Mock
    private MenuFactory menuFactory;

    @Mock
    private Menu menu;

    @InjectMocks
    private Waitress waitress;

    @BeforeEach
    void setUp() {
        waitress.setUuid(UUID.randomUUID().toString());
        waitress.setName("Alice");
    }

    @Test
    void takeOrderShouldPublishOrderTakenEvent() {
        MenuItem item1 = new MenuItem("1", "Spaghetti Bolognese", 12.5);
        MenuItem item2 = new MenuItem("2", "Margherita Pizza", 10.0);

        when(menu.getMenuItems()).thenReturn(List.of(item1, item2));
        when(menuFactory.createMenu()).thenReturn(menu);

        when(communicator.askYesNoQuestion("Alice: Would you like a " + item1 + "?")).thenReturn(false);
        when(communicator.askYesNoQuestion("Alice: Would you like a " + item2 + "?")).thenReturn(true);

        waitress.takeOrder();

        ArgumentCaptor<OrderTakenEvent> eventCaptor = ArgumentCaptor.forClass(OrderTakenEvent.class);
        verify(publisher).publishEvent(eventCaptor.capture());

        OrderTakenEvent publishedEvent = eventCaptor.getValue();

        assertThat(publishedEvent.getOrder().getItems()).containsExactly(item2);
    }

    @Test
    void serveCustomerShouldPublishOrderServedEvent(){
        Order cookedOrder = new Order(List.of(new MenuItem("1", "Soup", 5.0)));
        OrderCookedEvent cookedEvent = new OrderCookedEvent(cookedOrder);

        waitress.serveCustomer(cookedEvent);

        verify(publisher).publishEvent(any(OrderServedEvent.class));
    }
}