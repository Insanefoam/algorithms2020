package backpack;

import java.util.ArrayList;

public class Backpack {
    public int capacity;
    public int freeSpace;
    public ArrayList<Item> items;

    public Backpack(int capacity) {
        this.capacity = capacity;
        this.freeSpace = capacity;
        this.items = new ArrayList<>(10);
    }

    public boolean addItem(Item item) {
        if (this.freeSpace - item.weight >= 0) {
            this.freeSpace -= item.weight;
            item.fired = true;
            items.add(item);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        int summaryCost = 0;
        for (Item item : items) {
            summaryCost += item.cost;
        }
        return "Содержимое рюкзака: " + items.toString() + " суммарная стоимость: " + summaryCost;
    }
}
