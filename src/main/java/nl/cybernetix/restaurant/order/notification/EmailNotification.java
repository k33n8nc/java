package nl.cybernetix.restaurant.order.notification;

import nl.cybernetix.restaurant.order.Order;

public class EmailNotification implements INotification {

    @Override
    public String notifyUser(Order order) {
        return "Email notification for order: " + order.getOrderId() + " sent!";
    }
}
