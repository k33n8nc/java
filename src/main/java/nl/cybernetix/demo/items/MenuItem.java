package nl.cybernetix.demo.items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class MenuItem {
    private String id;
    private String name;
    private double price;
    private List<FoodCategory> categories;
}