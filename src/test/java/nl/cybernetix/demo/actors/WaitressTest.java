package nl.cybernetix.demo.actors;

import nl.cybernetix.demo.events.OrderCookedEvent;
import nl.cybernetix.demo.events.OrderServedEvent;
import nl.cybernetix.demo.events.OrderTakenEvent;
import nl.cybernetix.demo.items.Menu;
import nl.cybernetix.demo.items.MenuItem;
import nl.cybernetix.demo.items.Order;
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

    @InjectMocks
    private Waitress waitress;

    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = createMenu();
        waitress.setUuid(UUID.randomUUID().toString());
        waitress.setName("Alice");
        waitress.setMenu(menu);
    }

    @Test
    void takeOrderShouldPublishOrderTakenEvent() {
        menu.getMenuItems().forEach(item -> {
            when(communicator.askYesNoQuestion("Alice: Would you like a " + item + "?"))
                    .thenReturn(!item.getName().equals("Margherita Pizza"));
        });

        waitress.takeOrder();

        ArgumentCaptor<OrderTakenEvent> eventCaptor = ArgumentCaptor.forClass(OrderTakenEvent.class);
        verify(publisher).publishEvent(eventCaptor.capture());

        OrderTakenEvent publishedEvent = eventCaptor.getValue();

        assertThat(publishedEvent.getOrder().getItems()).containsExactly(menu.getMenuItems().get(0), menu.getMenuItems().get(2));
    }

    @Test
    void serveCustomerShouldPublishOrderServedEvent(){
        Order cookedOrder = new Order(menu.getMenuItems().subList(0, 2)); // sublist is tot niet tot en met.
        OrderCookedEvent cookedEvent = new OrderCookedEvent(cookedOrder);

        waitress.serveCustomer(cookedEvent);

        verify(publisher).publishEvent(any(OrderServedEvent.class));
    }

    private Menu createMenu(){
        return new Menu(List.of(
                new MenuItem("1", "Spaghetti Bolognese", 12.5),
                new MenuItem("2", "Margherita Pizza", 10.0),
                new MenuItem("3", "Caesar Salad", 8.75)
        ));
    }
}
