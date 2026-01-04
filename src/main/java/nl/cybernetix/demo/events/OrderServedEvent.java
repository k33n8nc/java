package nl.cybernetix.demo.events;

import lombok.Getter;
import nl.cybernetix.demo.items.Order;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderServedEvent extends ApplicationEvent {
    private final Order order;

    public OrderServedEvent(Order order) {
        super(order);
        this.order = order;
    }
}
