package Sets;

public class SetsTask {

    public void run() {
        char[] set = new char[]{'a', 'b', 'c'};
        int size = set.length;
        System.out.println("Iterative:");
        iterativeSolution(set, size);
        System.out.println("Recursive:");
        recursionSolution(set, 0, new char[]{});

    }

    //Итеративный подход к решению
    public void iterativeSolution(char[] set, int setSize) {
        //Ищем мощность конечного множества по теореме о мощности булеана множества
        long subsetsPower = (long) Math.pow(2, setSize);

        //Реализиуем побитовый сдвиг с помощью сложения, чтобы на каждой итерации один бит менялся с 0 на 1
        //Таким образом будут выведены все подмножества
        for (int i = 0; i < subsetsPower; i++) {
            for (int j = 0; j < setSize; j++) {
                if ((i & (1 << j)) > 0) {
                    System.out.print(set[j]);
                }
            }
            System.out.println();
        }
    }

    //Рекурсивное решение
    public void recursionSolution(char[] set, int index, char[] currentSet) {
        //set - изначальное множество
        //index - служебный индекс для деления множества
        //currentSet - текущее подмножество

        //Если дошли до конца множества - выводим полученное подмножество
        if (index == set.length) {
            for (char ch : currentSet)
                System.out.print(ch);
            System.out.println();
            return;
        }
        //Добавляем к текущему подмножеству элементы index-ный
        char[] newCurrentSet = new char[currentSet.length + 1];
        for (int i = 0; i < currentSet.length; i++) {
            newCurrentSet[i] = currentSet[i];
        }
        newCurrentSet[newCurrentSet.length - 1] = set[index];

        recursionSolution(set, index + 1, newCurrentSet);
        recursionSolution(set, index + 1, currentSet);
    }
}
