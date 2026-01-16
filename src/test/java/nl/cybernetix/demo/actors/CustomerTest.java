package nl.cybernetix.demo.actors;

import nl.cybernetix.demo.events.OrderServedEvent;
import nl.cybernetix.demo.items.Order;
import nl.cybernetix.demo.items.MenuItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
class CustomerTest {

    @Test
    void happyCustomerShouldBeGrateful(CapturedOutput output) {
        Customer customer = new Customer();
        List<MenuItem> items = List.of(
                new MenuItem("1", "Fries", 4.50),
                new MenuItem("2", "Pizza", 11.00)
        );
        Order order = new Order(items);

        customer.sayThanks(new OrderServedEvent(order));

        assertThat(output.getOut()).contains("Customer says thanks! Order " + order.getOrderId() + " has been received.");
    }
}
