package nl.cybernetix.restaurant.order.notification;

import nl.cybernetix.restaurant.order.Order;

public interface INotification {
    public String notifyUser(Order order);
}
