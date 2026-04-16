package nl.cybernetix.restaurant;

import lombok.Getter;
import nl.cybernetix.restaurant.order.Order;
import nl.cybernetix.restaurant.order.events.OrderTakenEvent;
import nl.cybernetix.restaurant.staff.Waitress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class OrderBook {
    private static final Logger log = LoggerFactory.getLogger(Waitress.class);
    private final UUID id = UUID.randomUUID();

    @Getter
    private final List<Order> history = new ArrayList<>();

    @EventListener
    public void logOrder(OrderTakenEvent event) {
        history.add(event.getOrder());
        log.info("Order history: {}", getHistory().size());
    }
}
