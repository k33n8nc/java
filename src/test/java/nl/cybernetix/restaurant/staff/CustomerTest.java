package nl.cybernetix.restaurant.staff;

import nl.cybernetix.restaurant.Customer;
import nl.cybernetix.restaurant.menu.MenuItem;
import nl.cybernetix.restaurant.order.Order;
import nl.cybernetix.restaurant.order.events.OrderServedEvent;
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

        Order order = new Order(List.of(MenuItem.builder().id("1").name("Croissant").price(3.0).build()));

        customer.sayThanks(new OrderServedEvent(order));

        assertThat(output.getOut()).contains("Customer says thanks! Order " + order.getOrderId() + " has been received.");
    }
}