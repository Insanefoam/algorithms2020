package Backpack;

public class MyNode implements Comparable<MyNode> {
    public int upperBound;
    public int costBound;
    public int[] mask;
    public int stopIndex;
    public MyNode leftChild;
    public MyNode rightChild;
    public boolean isRight;

    public MyNode(int size, int stopIndex, boolean isRight) {
        this.mask = new int[size];
        this.upperBound = 0;
        this.costBound = 0;
        this.stopIndex = stopIndex;
        this.leftChild = null;
        this.rightChild = null;
        this.isRight = isRight;
    }

    public MyNode(MyNode parent, boolean isRight) {
        this.mask = new int[parent.mask.length];
        this.stopIndex = parent.stopIndex + 1;
        this.isRight = isRight;
        this.leftChild = null;
        this.rightChild = null;
        for (int i = 0; i < this.mask.length; i++) {
            this.mask[i] = parent.mask[i];
        }
        if (!isRight) {
            this.upperBound = parent.upperBound;
            this.costBound = parent.costBound;

        } else {
            this.upperBound = 0;
            this.costBound = 0;
        }
    }

    @Override
    public String toString() {
        String maskStr = "";
        for (int el : mask) {
            maskStr += "" + el + " ";
        }
        return "ub:" + upperBound + " cb:" + costBound + " [ " + maskStr + "]";
    }

    @Override
    public int compareTo(MyNode node) {
        return node.upperBound * (-1) - this.upperBound * (-1);
    }
}
