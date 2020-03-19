package Sets;

public class SetsTask {

    public void run() {
        char[] set = new char[]{'a', 'b', 'c'};
        int size = set.length;
        //iterativeSolution(set, size);
        recursionSolution(set, 0, new char[]{});

    }


    public void recursionSolution(char[] set, int index, char[] currentSet) {
        if (index == set.length) {
            for (char ch : currentSet)
                System.out.print(ch);
            System.out.println();
            return;
        }

        char[] newCurrentSet = new char[currentSet.length + 1];
        for (int i = 0; i < currentSet.length; i++) {
            newCurrentSet[i] = currentSet[i];
        }
        newCurrentSet[newCurrentSet.length - 1] = set[index];

        recursionSolution(set, index + 1, newCurrentSet);
        recursionSolution(set, index + 1, currentSet);
    }


    public void iterativeSolution(char[] set, int setSize) {
        long subsetsPower = (long) Math.pow(2, setSize);

        for (int i = 0; i < subsetsPower; i++) {
            for (int j = 0; j < setSize; j++) {
                if ((i & (1 << j)) > 0) {
                    System.out.print(set[j]);
                }
            }
            System.out.println();
        }
    }
}
