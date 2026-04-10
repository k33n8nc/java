package nl.cybernetix.restaurant.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.cybernetix.restaurant.order.events.OrderCookedEvent;
import nl.cybernetix.restaurant.order.events.OrderServedEvent;
import nl.cybernetix.restaurant.order.events.OrderTakenEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class OrderManager {
    private static final Logger log = LoggerFactory.getLogger(OrderManager.class);
    private final ApplicationEventPublisher publisher;
    @Getter
    private final Map<UUID, Order> activeOrders = new ConcurrentHashMap<>();

    public void takeOrder(Order order) {
        order.setStatus(OrderStatus.TAKEN);
        activeOrders.put(order.getOrderId(), order);
        log.info("OrderManager: Ontvangen order {}. Actieve orders: {}. Keuken wordt aangestuurd...", order.getOrderId(), activeOrders.size());

//        if(timedOrder){
//            try {
//                Thread.sleep(2000);
//                log.info("This order had a custom pickup / delivery time");
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }

        publisher.publishEvent(new OrderTakenEvent(order)); // chef
    }

    public void cookOrder(Order order) {
        if (activeOrders.containsKey(order.getOrderId())) {
            order.setStatus(OrderStatus.COOKED);
            log.info("OrderManager: Order {} is gekookt. Bediening wordt aangestuurd om te serveren...", order.getOrderId());

            publisher.publishEvent(new OrderCookedEvent(order)); // waitress
        }
    }

    public void serveOrder(Order order) {
        if (activeOrders.containsKey(order.getOrderId())) {
            order.setStatus(OrderStatus.SERVED);
            activeOrders.remove(order.getOrderId());
            log.info("OrderManager: Order {} is succesvol geserveerd en afgerond! Actieve orders: {}", order.getOrderId(), activeOrders.size());

            publisher.publishEvent(new OrderServedEvent(order)); // klant
        }
    }
}
