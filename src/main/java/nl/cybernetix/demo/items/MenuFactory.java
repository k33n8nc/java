package nl.cybernetix.demo.items;

public class MenuFactory {
    public static Menu createMenu() {
        Menu menu = new Menu();
        // Initialiseer de menuItems lijst
        menu.getMenuItems().add(new MenuItem("1", "Spaghetti Bolognese", 12.50));
        menu.getMenuItems().add(new MenuItem("2", "Margherita Pizza", 10.00));
        menu.getMenuItems().add(new MenuItem("3", "Caesar Salad", 8.75));
        menu.getMenuItems().add(new MenuItem("4", "Tiramisu", 6.00));
        return menu;
    }
}
