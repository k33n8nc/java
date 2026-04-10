package nl.cybernetix.restaurant.staff;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import nl.cybernetix.restaurant.order.OrderManager;
import nl.cybernetix.restaurant.order.events.OrderCookedEvent;
import nl.cybernetix.restaurant.menu.Menu;
import nl.cybernetix.restaurant.menu.MenuItem;
import nl.cybernetix.restaurant.order.Order;
import nl.cybernetix.restaurant.utils.Communicator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component @RequiredArgsConstructor
public class Waitress {
    // ik kan hier focussen op main taken:
    // - praten met klanten (Communicator)
    // - orders opnemen (OrderManager)
    private static final Logger log = LoggerFactory.getLogger(Waitress.class);

    private final OrderManager orderManager;
    private final Communicator communicator;

    @Setter
    private Menu menu;

    @Getter @Setter
    private String uuid;

    @Getter @Setter
    private String name;

    public void takeOrder() {
        log.info("Waitress {} is taking an order.", name);

        List<MenuItem> noteBook = new ArrayList<>();

        for (MenuItem menuItem : menu.getMenuItems()) {
            if (communicator.askYesNoQuestion(name + ": Would you like a " + menuItem.getName() + "?")) {
                noteBook.add(menuItem);
            }
        }

        if (!noteBook.isEmpty()) {
            Order newOrder = new Order(noteBook);
            orderManager.takeOrder(newOrder);
            log.info("Waitress {}: Order sent to OrderManager: {}", name, newOrder.getItems());
        }
    }

    public void takeInstantOrder() {
        log.info("Waitress {}: taking order...", name);
        Order newOrder = new Order(menu.getMenuItems());
        orderManager.takeOrder(newOrder);
    }

    @Async
    @EventListener
    public void serveCustomer(OrderCookedEvent event){
        Order cookedOrder = event.getOrder();
        log.info("Waitress {}: Received cooked order {}. Serving to customer.", name, cookedOrder.getOrderId());
        orderManager.serveOrder(cookedOrder);
    }
}
