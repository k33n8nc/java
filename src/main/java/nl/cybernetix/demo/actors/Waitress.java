package nl.cybernetix.demo.actors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import nl.cybernetix.demo.events.OrderCookedEvent;
import nl.cybernetix.demo.events.OrderServedEvent;
import nl.cybernetix.demo.events.OrderTakenEvent;
import nl.cybernetix.demo.items.Menu;
import nl.cybernetix.demo.items.MenuItem;
import nl.cybernetix.demo.items.Order;
import nl.cybernetix.demo.utils.Communicator;
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

    public void takeOrder() {
        log.info("Waitress {} is taking an order.", name);

        List<MenuItem> noteBook = new ArrayList<>();

        menu.getMenuItems().forEach(menuItem -> {
            if (communicator.askYesNoQuestion(name + ": Would you like a " + menuItem + "?")) {
                noteBook.add(menuItem);
            }
        });
        Order newOrder = new Order(noteBook);
        log.info("Waitress {}: Order taken with {}. Now publishing OrderTakenEvent.", name, newOrder.getItems());
        publisher.publishEvent(new OrderTakenEvent(newOrder));
    }

    // Event listener en publisher
    @EventListener
    public void serveCustomer(OrderCookedEvent event){
        Order cookedOrder = event.getOrder();
        log.info("Waitress {}: Received cooked order {}. Serving to customer and publishing OrderServedEvent.", name, cookedOrder.getOrderId());
        publisher.publishEvent(new OrderServedEvent(cookedOrder));
    }
}
