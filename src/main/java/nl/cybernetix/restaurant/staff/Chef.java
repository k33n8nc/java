package nl.cybernetix.restaurant.staff;

import lombok.RequiredArgsConstructor;
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
        log.info("Chef: Order received {}. Order status: {}", event.getOrder().getId(), event.getOrder().getStatus());

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        event.getOrder().setStatus(OrderStatus.COOKED);
        log.info("Chef: Order cooked {}. Order status: {}", event.getOrder().getId(), event.getOrder().getStatus());
        publisher.publishEvent(new OrderCookedEvent(event.getOrder()));
    }
}
