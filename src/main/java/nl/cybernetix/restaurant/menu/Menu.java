package nl.cybernetix.restaurant.menu;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Menu {
    private final List<MenuItem> menuItems;
}
