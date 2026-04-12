package nl.cybernetix.restaurant.staff;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import nl.cybernetix.restaurant.menu.Menu;
import nl.cybernetix.restaurant.order.Order;
import nl.cybernetix.restaurant.order.OrderStatus;
import nl.cybernetix.restaurant.order.events.OrderCookedEvent;
import nl.cybernetix.restaurant.order.events.OrderServedEvent;
import nl.cybernetix.restaurant.order.events.OrderTakenEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Waitress {
    private static final Logger log = LoggerFactory.getLogger(Waitress.class);
    private final ApplicationEventPublisher publisher;
    @Setter
    private Menu menu;

    @Getter
    @Setter
    private String uuid;

    @Getter
    @Setter
    private String name;

    public void takeOrder() {
        Order order = new Order(menu.getMenuItems());
        order.setStatus(OrderStatus.TAKEN);
        log.info("Waitress {}: Order taken: {}. Order status: {}", name, order.getId(), order.getStatus());
        publisher.publishEvent(new OrderTakenEvent(order));
    }

    @EventListener
    public void serveCustomer(OrderCookedEvent event) {
        Order order = event.getOrder();
        order.setStatus(OrderStatus.SERVED);
        log.info("Waitress {}: Served order: {} to customer. Order status: {}", name, order.getId(), order.getStatus());
        publisher.publishEvent(new OrderServedEvent(order));
    }
}
