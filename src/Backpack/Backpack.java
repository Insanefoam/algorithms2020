package Backpack;

import java.util.ArrayList;

public class Backpack {
    public int capacity;
    public int freeSpace;
    public int summaryCost;
    public ArrayList<Item> items;

    public Backpack(int capacity) {
        this.capacity = capacity;
        this.freeSpace = capacity;
        this.summaryCost = 0;
        this.items = new ArrayList<>(10);
    }

    public boolean addItem(Item item) {
        if (this.freeSpace - item.weight >= 0) {
            this.freeSpace -= item.weight;
            this.summaryCost += item.cost;
            items.add(item);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Содержимое рюкзака: " + items.toString() + " суммарная стоимость: " + summaryCost;
    }
}
