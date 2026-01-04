package nl.cybernetix.demo.actors;

import nl.cybernetix.demo.events.OrderCookedEvent;
import nl.cybernetix.demo.events.OrderServedEvent;
import nl.cybernetix.demo.events.OrderTakenEvent;
import nl.cybernetix.demo.items.Menu;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @BeforeEach
    void setUp() {
        waitress.setUuid(UUID.randomUUID().toString());
        waitress.setName("Alice");
    }

    @Test
    void checkIfReadyToWork(){
        waitress.setUuid(UUID.randomUUID().toString());
        waitress.setName("Alice");
        waitress.setNoteBook(Menu.getItems());

        assertThat(waitress.getUuid()).isNotNull();
        assertThat(waitress.getName()).isEqualTo("Alice");
        assertEquals(waitress.getNoteBook(), Menu.getItems());
    }

    @Test
    void takeOrderShouldPublishOrderTakenEvent() {
        when(communicator.askYesNoQuestion("Alice: Would you like a " + Menu.starter + "?")).thenReturn(true);
        when(communicator.askYesNoQuestion("Alice: Would you like a " + Menu.mainCourse + "?")).thenReturn(false);
        when(communicator.askYesNoQuestion("Alice: Would you like a " + Menu.dessert + "?")).thenReturn(true);

        waitress.takeOrder();

        ArgumentCaptor<OrderTakenEvent> eventCaptor = ArgumentCaptor.forClass(OrderTakenEvent.class);
        verify(publisher).publishEvent(eventCaptor.capture());

        OrderTakenEvent publishedEvent = eventCaptor.getValue();

        assertThat(publishedEvent.getOrder().getOrder()).containsExactly(Menu.starter, Menu.dessert);
    }

    @Test
    void serveCustomerShouldPublishOrderServedEvent(){
        Order cookedOrder = new Order(List.of(Menu.starter, Menu.mainCourse));
        OrderCookedEvent cookedEvent = new OrderCookedEvent(cookedOrder);

        waitress.serveCustomer(cookedEvent);

        verify(publisher).publishEvent(any(OrderServedEvent.class));
    }
    
}