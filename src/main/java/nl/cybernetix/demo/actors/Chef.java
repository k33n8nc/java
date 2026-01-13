package nl.cybernetix.demo.actors;

import lombok.RequiredArgsConstructor;
import nl.cybernetix.demo.events.OrderCookedEvent;
import nl.cybernetix.demo.events.OrderTakenEvent;
import nl.cybernetix.demo.items.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Chef {
    private static final Logger log = LoggerFactory.getLogger(Chef.class);
    private final ApplicationEventPublisher publisher;

    @EventListener
    public void cookOrder(OrderTakenEvent event) {
        log.info("Chef: Received order {}. Cooking items: {}. Publishing OrderCookedEvent.", event.getOrder().getOrderId(), event.getOrder().getItems());
        publisher.publishEvent(new OrderCookedEvent(event.getOrder()));
    }
}
