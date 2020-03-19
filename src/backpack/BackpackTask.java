package backpack;

import java.util.*;

public class BackpackTask {
    private int n = 3;
    private ArrayList<Item> availableItems = new ArrayList<>(n);
    private int capacity = 15;

    public void run() {
        createItems();
        System.out.println("Все доступные предметы: \n" + availableItems);
        /*Backpack bruteBackpack = new Backpack(capacity);
        bruteForce(bruteBackpack);*/
        Backpack branchesBackpack = new Backpack(capacity);
        branchesAndBorders(branchesBackpack);
    }

    private void bruteForce(Backpack backpack) {
        ArrayList<Item> sortedCopy = new ArrayList<>(availableItems);
        sortedCopy.sort(weightCostComparator);
        System.out.println("Сортированные предметы по убыванию (цена/вес): \n" + sortedCopy);
        while (backpack.addItem(sortedCopy.remove(0))) ;
        System.out.println(backpack);
    }

    private void branchesAndBorders(Backpack backpack) {
        ArrayList<Item> sortedCopy = new ArrayList<>(availableItems);
        sortedCopy.sort(costComparator);
        System.out.println("Сортированные предметы по убыванию цены: \n" + sortedCopy);
        System.out.println(possibleCountOfItemToAdd(backpack, sortedCopy));
    }

    private ArrayList<Item> possibleCountOfItemToAdd(Backpack backpack, ArrayList<Item> availableItems) {
        ArrayList<Item> availableToAdd = new ArrayList<>();

        for (int i = 0; i < availableItems.size(); i++) {
            if (backpack.addItem(availableItems.get(i))) {
                availableToAdd.add(availableItems.get(i));
            }
        }
        return availableToAdd;
    }

    private int borderFunc() {
        return 0;
    }

    private static Comparator<Item> weightCostComparator = Comparator.comparingInt((Item i) -> (i.cost / i.weight)).reversed();

    private static Comparator<Item> costComparator = Comparator.comparingInt((Item i) -> i.cost).reversed();

    private void createItems() {
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            availableItems.add(new Item(random.nextInt(10) + 1, random.nextInt(10) + 1));
        }
    }
}
