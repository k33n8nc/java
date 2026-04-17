package nl.cybernetix.restaurant.staff;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import nl.cybernetix.restaurant.order.Order;
import nl.cybernetix.restaurant.order.OrderQueue;
import nl.cybernetix.restaurant.order.OrderStatus;
import nl.cybernetix.restaurant.order.events.OrderCookedEvent;
import nl.cybernetix.restaurant.order.events.OrderTakenEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class Chef {
    private static final Logger log = LoggerFactory.getLogger(Chef.class);
    private final ApplicationEventPublisher publisher;
    private final OrderQueue orderQueue;

    @Getter @Setter
    private ChefState state = ChefState.IDLE;
    private int numOfOrdersCooking = 0;

    @EventListener
    public void onOrderTaken(OrderTakenEvent event) {
        orderQueue.addOrder(event.getOrder());
        log.info("Chef: Order {} added to orderQueue.", event.getOrder().getId());
        processOrderQueue();
    }

    @EventListener
    public void onOrderCooked(OrderCookedEvent event) {
        numOfOrdersCooking--;
        updateState();
        processOrderQueue();
    }

    private void processOrderQueue() {
        while (numOfOrdersCooking < 3 && !orderQueue.getPending().isEmpty()) {
            Order order = orderQueue.removeOrder();
            numOfOrdersCooking++;
            updateState();

            // Kook de order asynchroon op de achtergrond
            CompletableFuture.runAsync(() -> cook(order));
        }
    }

    private void cook(Order order) {
        order.setStatus(OrderStatus.COOKING);
        log.info("Chef: Started cooking order {}. State: {} (Cooking: {})", order.getId(), state, numOfOrdersCooking);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        order.setStatus(OrderStatus.COOKED);
        log.info("Chef: Finished cooking order {}.", order.getId());
        publisher.publishEvent(new OrderCookedEvent(order)); // Vertel de Waitress dat hij klaar is!
    }

    private void updateState() {
        this.state = (numOfOrdersCooking >= 3) ? ChefState.BUSY : ChefState.IDLE;
    }
}
