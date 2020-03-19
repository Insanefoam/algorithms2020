package DotsPair;

import java.util.*;

public class DotTask {
    public int n = 60000;
    public ArrayList<Dot> dots = null;

    public void run() {
        initDots();
        System.out.println("brute force: " + bruteForce(dots));
        System.out.println("decomposing: " + decomposingMethod(dots));
    }

    public double bruteForce(List<Dot> dots) {
        double minDist = Double.MAX_VALUE;
        Dot[] minPair = new Dot[]{null, null};
        for (int i = 0; i < dots.size(); ++i) {
            for (int j = i + 1; j < dots.size(); ++j) {
                if (distance(dots.get(i), dots.get(j)) < minDist) {
                    minDist = distance(dots.get(i), dots.get(j));
                    minPair[0] = dots.get(i);
                    minPair[1] = dots.get(j);
                }
            }
        }
        System.out.println("Closest minPair: ");
        System.out.println(minPair[0]);
        System.out.println(minPair[1]);
        return minDist;
    }

    public double decomposingMethod(ArrayList<Dot> arr) {
        //Sort by X
        arr.sort(xComparator);
        return closestUtil(arr);
    }

    public double closestUtil(List<Dot> arr) {
        int n = arr.size();
        if (n <= 3) {
            return bruteForce(arr);
        }

        int mid = n / 2;
        Dot midDot = arr.get(mid);
        double dl = closestUtil(arr.subList(0, mid));
        double dr = closestUtil(arr.subList(mid, n));

        double d = Math.min(dl, dr);
        List<Dot> strip = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            if (Math.abs(dots.get(i).x - midDot.x) < d) {
                strip.add(dots.get(i));
            }
        }
        return Math.min(d, stripClosest(strip, d));
    }

    private double stripClosest(List<Dot> strip, double d) {
        double min = d;
        strip.sort(yComparator);
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < min; j++) {
                if (distance(strip.get(i), strip.get(j)) < min) {
                    min = distance(strip.get(i), strip.get(j));
                }
            }
        }
        return min;
    }

    public double distance(Dot dot1, Dot dot2) {
        return Math.sqrt(Math.pow((dot1.x - dot2.x), 2) + Math.pow((dot1.y - dot2.y), 2));
    }

    public static Comparator<Dot> xComparator = (dot, t1) -> (int) ((dot.x - t1.x) * 10000);

    public static Comparator<Dot> yComparator = (dot, t1) -> (int) ((dot.y - t1.y) * 10000);


    public void initDots() {
        dots = new ArrayList<>(n);
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            dots.add(new Dot(random.nextDouble(), random.nextDouble()));
        }
    }
}
