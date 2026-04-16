package nl.cybernetix.restaurant.order;

import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class OrderQueue {
    private final BlockingQueue<Order> pendingOrders = new LinkedBlockingQueue<>();

    public void addOrder(Order order) {
        pendingOrders.offer(order);
    }

    public Order takeNextOrder() throws InterruptedException {
        // take() blokkeert de thread totdat er een order beschikbaar is
        return pendingOrders.take();
    }

    public int getQueueSize() {
        return pendingOrders.size();
    }
}