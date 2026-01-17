package nl.cybernetix.demo.actors;

import nl.cybernetix.demo.events.OrderServedEvent;
import nl.cybernetix.demo.items.Menu;
import nl.cybernetix.demo.items.MenuFactory;
import nl.cybernetix.demo.items.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
class CustomerTest {
    @Spy
    private Menu menu = new MenuFactory().createMenu();

    @Test
    void happyCustomerShouldBeGrateful(CapturedOutput output) {
        Customer customer = new Customer();

        Order order = new Order(
                menu.getMenuItems().subList(0, 2)
        );

        customer.sayThanks(new OrderServedEvent(order));

        assertThat(output.getOut()).contains("Customer says thanks! Order " + order.getOrderId() + " has been received.");
    }
}