package java.nl.cybernetix.demo.actors;

import nl.cybernetix.demo.actors.Chef;
import nl.cybernetix.demo.events.OrderCookedEvent;
import nl.cybernetix.demo.events.OrderTakenEvent;
import nl.cybernetix.demo.items.MenuItem;
import nl.cybernetix.demo.items.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChefTest {

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private Chef chef;

    @Test
    void cookOrderShouldReturnCorrectOrderInEvent(){
        List<MenuItem> items = List.of(
                new MenuItem("1", "Fanta", 2.50),
                new MenuItem("2", "Pizza", 11.00)
        );
        Order order = new Order(items);
        OrderTakenEvent event = new OrderTakenEvent(order);

        chef.cookOrder(event);

        ArgumentCaptor<OrderCookedEvent> eventCaptor = ArgumentCaptor.forClass(OrderCookedEvent.class);
        verify(publisher).publishEvent(eventCaptor.capture());
        OrderCookedEvent publishedEvent = eventCaptor.getValue();

        // the last (MenuItem) items is casting this so that assert can use it?
        assertThat(publishedEvent.getOrder().getItems()).containsExactly((MenuItem) items);
    }

}
