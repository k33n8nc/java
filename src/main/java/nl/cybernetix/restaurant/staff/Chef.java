package nl.cybernetix.restaurant.staff;

import lombok.RequiredArgsConstructor;
import lombok.Getter;
import nl.cybernetix.restaurant.order.OrderStatus;
import nl.cybernetix.restaurant.order.OrderQueue;
import nl.cybernetix.restaurant.order.Order;
import nl.cybernetix.restaurant.order.events.OrderCookedEvent;
import nl.cybernetix.restaurant.order.events.OrderTakenEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Chef {
    private static final Logger log = LoggerFactory.getLogger(Chef.class);
    private final ApplicationEventPublisher publisher;
    private final OrderQueue orderQueue;
    @Getter
    private ChefState state = ChefState.IDLE;

    @EventListener
    public void onOrderTaken(OrderTakenEvent event) {
        orderQueue.addOrder(event.getOrder());
        log.info("Chef: Order {} added to the ticket rail. Queue size: {}", event.getOrder().getId(), orderQueue.getQueueSize());
    }

    @Async
    @EventListener(ApplicationReadyEvent.class)
    public void processOrders() {
        log.info("Chef is at the station, ready to cook. State: {}", state);
        while (true) {
            try {
                this.state = ChefState.IDLE;
                Order order = orderQueue.takeNextOrder(); // Blokkeert totdat er een order is
                
                this.state = ChefState.COOKING;
                order.setStatus(OrderStatus.COOKING);
                log.info("Chef: Started cooking order {}. Chef state: {}", order.getId(), state);
                
                Thread.sleep(1000);
                
                order.setStatus(OrderStatus.COOKED);
                log.info("Chef: Finished cooking order {}. Order status: {}", order.getId(), order.getStatus());
                publisher.publishEvent(new OrderCookedEvent(order));

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
