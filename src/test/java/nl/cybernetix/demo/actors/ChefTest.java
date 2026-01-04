package nl.cybernetix.demo.actors;

import nl.cybernetix.demo.events.OrderCookedEvent;
import nl.cybernetix.demo.events.OrderTakenEvent;
import nl.cybernetix.demo.items.Menu;
import nl.cybernetix.demo.items.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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
        Order order = new Order(List.of(Menu.starter));
        OrderTakenEvent event = new OrderTakenEvent(order);

        chef.cookOrder(event);

        ArgumentCaptor<OrderCookedEvent> eventCaptor = ArgumentCaptor.forClass(OrderCookedEvent.class);
        verify(publisher).publishEvent(eventCaptor.capture());
        OrderCookedEvent publishedEvent = eventCaptor.getValue();

        assertThat(publishedEvent.getOrder().getOrder()).containsExactly(Menu.starter);
    }

}
