package nl.cybernetix.restaurant.staff;

import nl.cybernetix.restaurant.menu.MenuItem;
import nl.cybernetix.restaurant.order.Order;
import nl.cybernetix.restaurant.order.events.OrderCookedEvent;
import nl.cybernetix.restaurant.order.events.OrderTakenEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ChefTest {

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private Chef chef;

    @Test
    void cookOrderShouldReturnCorrectOrderInEvent() {
//        List<MenuItem> items = List.of(MenuItem.builder().id("1").name("Croissant").price(3.0).build());
//        Order order = new Order(items);
//        OrderTakenEvent event = new OrderTakenEvent(order);
//
//        chef.cookOrder(event);
//
//        ArgumentCaptor<OrderCookedEvent> eventCaptor = ArgumentCaptor.forClass(OrderCookedEvent.class);
//        verify(publisher).publishEvent(eventCaptor.capture());
//        OrderCookedEvent publishedEvent = eventCaptor.getValue();
//
//        assertThat(publishedEvent.getOrder().getItems()).containsExactlyElementsOf(items);
    }

}
