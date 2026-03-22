package nl.cybernetix.restaurant.order.notification;

import nl.cybernetix.restaurant.config.RestaurantConfig;
import nl.cybernetix.restaurant.menu.Menu;
import nl.cybernetix.restaurant.menu.MenuCategory;
import nl.cybernetix.restaurant.menu.MenuFactory;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationApplication {

    public static void main(String[] args) {
        INotification emailNotification = NotificationFactory.createNotification("email");

        SmsNotification smsNotification = new SmsNotification();
        smsNotification.setRecipient("123456789");

        RestaurantConfig restaurantConfig = new RestaurantConfig();
        MenuFactory menuFactory = new MenuFactory(restaurantConfig);

        Menu myMenu = menuFactory.createMenu(MenuCategory.BREAKFAST);

//        System.out.println(myMenu.getMenuItems());
//
//        Order myOrder = new Order(myMenu.getMenuItems());
//
//        System.out.println(smsNotification.getRecipient());
//        System.out.println(emailNotification.notifyUser(order));
//        System.out.println(smsNotification.notifyUser(order));
    }
}
