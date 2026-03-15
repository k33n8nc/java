package nl.cybernetix.restaurant.order.notification;

import nl.cybernetix.restaurant.menu.MenuCategory;
import nl.cybernetix.restaurant.menu.MenuFactory;
import nl.cybernetix.restaurant.menu.MenuItem;
import nl.cybernetix.restaurant.order.Order;

import java.util.List;

public class NotificationApplication {
    public static void main(String[] args) {
        INotification emailNotification = NotificationFactory.createNotification("email");

        SmsNotification smsNotification = new SmsNotification();
        smsNotification.setRecipient("123456789");

//        MenuFactory menuFactory = new MenuFactory(MenuCategory.BREAKFAST);
//        Order order = new Order(
//            menuFactory.createMenu(MenuCategory.BREAKFAST).getMenuItems()
//        );

//        System.out.println(smsNotification.getRecipient());
//        System.out.println(emailNotification.notifyUser(order));
//        System.out.println(smsNotification.notifyUser(order));
    }
}
