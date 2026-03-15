package nl.cybernetix.restaurant.order.notification;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import nl.cybernetix.restaurant.order.Order;

@RequiredArgsConstructor
public class SmsNotification implements INotification {
    @Getter
    @Setter
    private String recipient;
    @Override
    public String notifyUser(Order order) {
        return "SMS notification for order: " + order.getOrderId() + " sent!";
    }

}
