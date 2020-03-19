package DotsPair;

public class Dot {
    public double x;
    public double y;

    public Dot(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

/*    @Override
    public int compareTo(Dot dot1) {
        return (int) ((this.x - dot1.x) * 100);
    }*/
}
