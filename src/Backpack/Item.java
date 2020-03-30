package Backpack;

public class Item implements Comparable<Item> {
    public int weight;
    public int cost;

    public Item(int weight, int cost) {
        this.weight = weight;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return new String("weight: " + weight + " cost: " + cost);
    }

    @Override
    public int compareTo(Item item) {
        return ((item.cost / item.weight) - (this.cost / this.weight));
    }
}
