package DotsPair;

import java.util.*;

public class DotTask {
    public int n = 10;
    public ArrayList<Dot> dots = null;

    public void run() {
        initDots();
        System.out.println("brute force: " + bruteForce(dots));
        System.out.println("decomposing: " + decomposingMethod(dots));
    }

    public double bruteForce(List<Dot> dots) {
        //Сложность O(n^2)
        //Простым перебором ищем минимальную пару
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

    //Инициализатор метода декомпозии
    public double decomposingMethod(ArrayList<Dot> arr) {
        //Сортируем по иксу
        arr.sort(xComparator);
        return recursiveSolution(arr);
    }

    //Ищем рекурсивно наиболее близкие точки по методу "Разделяй и властвуй"
    //Сложность O(n*log(n)*log(n))
    //Сложность можно улучшить до O(n*log(n)) если этап сортировки оставшихся точек по координате игрек
    //улучшить до O(n), сейчас ее сложность O(n*log(n))
    public double recursiveSolution(List<Dot> arr) {
        int n = arr.size();
        //Если в очередном массиве 3 или менее точек, то ищем наиболее близкие с помощью брутфорса
        if (n <= 3) {
            return bruteForce(arr);
        }

        //Делим массив пополам, реализуем механизм "Разделяй и властвуй"
        int mid = n / 2;
        Dot midDot = arr.get(mid);
        double dl = recursiveSolution(arr.subList(0, mid));
        double dr = recursiveSolution(arr.subList(mid, n));

        //Среди двух минимальных расстояний для первой половины и второй половины точек соответственно
        //ищем наименьшее расстояние и затем переходим в функцию ниже (checkAroundDistance)
        // для поиска еще более близких точек
        double d = Math.min(dl, dr);
        List<Dot> strip = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            if (Math.abs(dots.get(i).x - midDot.x) < d) {
                strip.add(dots.get(i));
            }
        }
        return Math.min(d, checkAroundDistance(strip, d));
    }

    //Проверяем нет ли внутри полуплоскости от -d до +d точек еще более близких
    private double checkAroundDistance(List<Dot> strip, double d) {
        double min = d;
        //Сортируем по игреку
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

    //Вынес в отдельную функцию нахождение расстояния между точками, для удобства
    public double distance(Dot dot1, Dot dot2) {
        return Math.sqrt(Math.pow((dot1.x - dot2.x), 2) + Math.pow((dot1.y - dot2.y), 2));
    }

    //Компаратор для сортировки по координате x
    public static Comparator<Dot> xComparator = (dot, t1) -> (int) ((dot.x - t1.x) * 10000);

    //Компаратор для соритровки по координате y
    public static Comparator<Dot> yComparator = (dot, t1) -> (int) ((dot.y - t1.y) * 10000);

    //Инициализируем массив с точками в единичном квадрате
    public void initDots() {
        dots = new ArrayList<>(n);
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            dots.add(new Dot(random.nextDouble(), random.nextDouble()));
        }
    }
}
