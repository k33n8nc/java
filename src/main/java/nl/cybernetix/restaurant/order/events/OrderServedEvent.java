package nl.cybernetix.restaurant.order.events;

import lombok.Getter;
import nl.cybernetix.restaurant.order.Order;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderServedEvent extends ApplicationEvent {
    private final Order order;

    public OrderServedEvent(Order order) {
        super(order);
        this.order = order;
    }

}
