package nl.cybernetix.demo.actors;

import nl.cybernetix.demo.events.OrderServedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Customer {
    private static final Logger log = LoggerFactory.getLogger(Customer.class);

    @EventListener
    public void sayThanks(OrderServedEvent event){
        log.info("Customer says thanks! Order {} has been received.", event.getOrder().getOrderId());
    }
}
