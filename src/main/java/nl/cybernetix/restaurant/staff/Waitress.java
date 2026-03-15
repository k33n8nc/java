package nl.cybernetix.restaurant.staff;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import nl.cybernetix.restaurant.order.events.OrderCookedEvent;
import nl.cybernetix.restaurant.order.events.OrderServedEvent;
import nl.cybernetix.restaurant.order.events.OrderTakenEvent;
import nl.cybernetix.restaurant.menu.Menu;
import nl.cybernetix.restaurant.menu.MenuItem;
import nl.cybernetix.restaurant.order.Order;
import nl.cybernetix.restaurant.utils.Communicator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component @RequiredArgsConstructor
public class Waitress {
    private static final Logger log = LoggerFactory.getLogger(Waitress.class);
    private final ApplicationEventPublisher publisher;
    private final Communicator communicator;

    @Setter
    private Menu menu;

    @Getter @Setter
    private String uuid;

    @Getter @Setter
    private String name;

    public void askCategory(){
        log.info("Waitress {} asks for category.", name);
    }

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
            log.info("Waitress {}: Order taken with {}. Now publishing OrderTakenEvent.", name, newOrder.getItems());
            publisher.publishEvent(new OrderTakenEvent(newOrder));
        }
    }

    // Event listener en publisher
    @EventListener
    public void serveCustomer(OrderCookedEvent event){
        Order cookedOrder = event.getOrder();
        log.info("Waitress {}: Received cooked order {}. Serving to customer and publishing OrderServedEvent.", name, cookedOrder.getOrderId());
        publisher.publishEvent(new OrderServedEvent(cookedOrder));
    }
}
