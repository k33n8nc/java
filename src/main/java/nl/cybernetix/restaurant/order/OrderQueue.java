package nl.cybernetix.restaurant.order;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class OrderQueue {

    @Getter
    private final List<Order> pending = new LinkedList<>();

    public void addOrder(Order order) {
        pending.add(order);
    }

    public Order removeOrder() {
        return pending.removeFirst();
    }
}