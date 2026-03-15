package nl.cybernetix.restaurant.order.events;

import lombok.Getter;
import nl.cybernetix.restaurant.order.Order;
import nl.cybernetix.restaurant.order.notification.NotificationFactory;
import nl.cybernetix.restaurant.order.notification.SmsNotification;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderServedEvent extends ApplicationEvent {
    private NotificationFactory notificationFactory;
    private final Order order;

    public OrderServedEvent(Order order) {
        super(order);
        this.order = order;
    }

    public SmsNotification sendNotification(String orderId){
        return ((SmsNotification) notificationFactory.createNotification("SMS"));
    }
}
