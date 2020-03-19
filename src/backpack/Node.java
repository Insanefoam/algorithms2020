package backpack;

public class Node {
    public int weight;
    public int level;
    public int profit;
    public int bound;

    public Node() {
    }

    public Node(int weight, int level, int profit, int bound) {
        this.weight = weight;
        this.level = level;
        this.profit = profit;
        this.bound = bound;
    }
}
