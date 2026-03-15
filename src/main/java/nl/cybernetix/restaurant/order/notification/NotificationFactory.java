package nl.cybernetix.restaurant.order.notification;

public class NotificationFactory {
    public static INotification createNotification(String channel) {
        if (channel == null || channel.isEmpty()) {
            return null;
        }
        return switch (channel.toUpperCase()) {
            case "SMS" -> new SmsNotification();
            case "EMAIL" -> new EmailNotification();
            default -> throw new IllegalArgumentException("Unknown notification channel: " + channel);
        };
    }

}
