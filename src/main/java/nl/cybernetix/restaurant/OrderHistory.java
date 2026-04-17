package nl.cybernetix.restaurant;

import lombok.Getter;
import nl.cybernetix.restaurant.order.Order;
import nl.cybernetix.restaurant.order.events.OrderTakenEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderHistory {
    private static final Logger log = LoggerFactory.getLogger(OrderHistory.class);

    @Getter
    private final List<Order> history = new ArrayList<>();

    @EventListener
    public void logOrder(OrderTakenEvent event) {
        history.add(event.getOrder());
        log.info("OrderHistory updated. Total orders saved: {}", history.size());
    }
}