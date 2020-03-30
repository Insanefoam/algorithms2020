package Fibbonaci;

public class Fibbonaci {
    private int n = 15;

    public void runProgram() {
        System.out.println(findRecursive(0, 1, n));
        System.out.println(findByMatrix());
    }

    //Ищем ответ с помощью рекурсии
    public int findRecursive(int first, int second, int n) {
        return n == 1 ? second : findRecursive(second, first + second, n - 1);
    }

    //Ищем ответ с помощью матриц
    public int findByMatrix() {
        int[][] p = {{1, 1, 0}, {1, 0, 0}, {0, 1, 0}};
        //Чтобы найти n-ый член последовальности Фиббоначи надо возвести p в степень n-2 и умножить
        //на матрицу {1, 1, 0}
        return powMatrixByN(p, n - 2);
    }

    //Функция унможения двух матриц. Результат сохраняется в первую матрицу.
    public void multiplyMatrixes(int[][] a, int[][] b) {
        int[][] result = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = 0;
                for (int k = 0; k < 3; k++)
                    result[i][j] += a[i][k] * b[k][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = result[i][j];
            }
        }
    }

    //Возводим матрицу в степень
    public int powMatrixByN(int[][] a, int n) {
        int[][] m = {{1, 1, 0}, {1, 0, 0}, {0, 1, 0}};

        //Если во время рекурсии мы пришли к тому, что матрицу нужно возвести
        //в степень 1, то мы нашли ответ и возвращаем [0][0] + [0][1]
        if (n == 1) {
            return a[0][0] + a[0][1];
        }

        powMatrixByN(a, n / 2);

        //Иначе идем дальше и возводим матрицу в квадрат
        multiplyMatrixes(a, a);

        //Если попалась нечетная степень для возведения - умножаем на изначальную матрицу
        //Она сохранена прозапас в начале в матрице m[][]
        if (n % 2 != 0) {
            multiplyMatrixes(a, m);
        }

        return a[0][0] + a[0][1];
    }
}
