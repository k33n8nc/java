package nl.cybernetix.demo.items;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Menu {
    private final List<MenuItem> menuItems = new ArrayList<>();

    public void addMenuItem(MenuItem item) {
        this.menuItems.add(item);
    }
}
