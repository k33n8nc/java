package nl.cybernetix.demo.items;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Menu {
    public static final String starter = "Starter";
    public static final String mainCourse = "Main course";
    public static final String dessert = "Dessert";

    public static List<String> getItems(){
        List<String> menuItems = new ArrayList<>();
        menuItems.add(starter);
        menuItems.add(mainCourse);
        menuItems.add(dessert);
        return menuItems;
    }

}