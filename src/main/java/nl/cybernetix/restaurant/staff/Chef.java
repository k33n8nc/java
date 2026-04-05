package nl.cybernetix.restaurant.staff;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;

import nl.cybernetix.restaurant.menu.MenuItem;
import nl.cybernetix.restaurant.order.OrderStatus;
import nl.cybernetix.restaurant.order.events.OrderCookedEvent;
import nl.cybernetix.restaurant.order.events.OrderTakenEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Chef {
    private static final Logger log = LoggerFactory.getLogger(Chef.class);
    private final ApplicationEventPublisher publisher;

    @Async
    @EventListener
    public void cookOrder(OrderTakenEvent event) {
        event.getOrder().setStatus(OrderStatus.COOKING);
        List<MenuItem> menuItems = event.getOrder().getItems();
        UUID orderId = event.getOrder().getOrderId();

        log.info("Chef: Received order {}. Cooking items: {}. Publishing OrderCookedEvent.", orderId, menuItems);
        
        try {
            Thread.sleep(2000); // Simulate cooking time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        event.getOrder().setStatus(OrderStatus.COOKED);
        publisher.publishEvent(new OrderCookedEvent(event.getOrder()));
    }
}
