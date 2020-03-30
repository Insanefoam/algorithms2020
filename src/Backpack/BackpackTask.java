package Backpack;

import java.util.*;

public class BackpackTask {
    private int n = 4;
    private ArrayList<Item> availableItems;
    private int capacity = 15;

    public void run() {
        availableItems = new ArrayList<Item>(n);
        //createItems();
        availableItems.add(new Item(2, 10));
        availableItems.add(new Item(4, 10));
        availableItems.add(new Item(6, 12));
        availableItems.add(new Item(9, 18));

        System.out.println("Все доступные предметы: \n" + availableItems);
        Backpack bruteBackpack = new Backpack(capacity);
        bruteForce(bruteBackpack);
        System.out.println("\n\n");

        Backpack branchesBackpack = new Backpack(capacity);
        branchesAndBorders(branchesBackpack);
        System.out.println("LC-BB answer: \n" + branchesBackpack.summaryCost);
    }

    private void bruteForce(Backpack backpack) {
        ArrayList<Item> sortedCopy = new ArrayList<>(availableItems);
        sortedCopy.sort(weightCostComparator);
        System.out.println("Сортированные предметы по убыванию (цена/вес): \n" + sortedCopy);
        while (sortedCopy.size() != 0 && backpack.addItem(sortedCopy.remove(0))) ;
        System.out.println("Bruteforce answer: \n" + backpack.summaryCost);
    }

    public void branchesAndBorders(Backpack backpack) {
        ArrayList<Item> sortedCopy = new ArrayList<>(availableItems);
        sortedCopy.sort(weightCostComparator);
        if (sortedCopy.size() == 0) {
            System.out.println("No available items");
            return;
        }
        System.out.println("Сортированные предметы по возрастанию цены: \n" + sortedCopy);
        lessCostBranchesAndBorder(backpack, sortedCopy);
    }

    private void lessCostBranchesAndBorder(Backpack backpack, ArrayList<Item> sortedCopy) {
        ArrayList<Item> items = sortedCopy;
        int size = sortedCopy.size();
        int globalUpper = Integer.MAX_VALUE;

        MyNode root = new MyNode(size, -1, false);
        bound(root, items);

        Queue<MyNode> queue = new PriorityQueue<MyNode>();
        queue.add(root);
        MyNode current;
        int level = 0;
        while (level != items.size()) {
            level++;
            current = queue.poll();
            if (current.upperBound < globalUpper) {
                globalUpper = current.upperBound;
                killNodes(queue, globalUpper);
            }
            createChild(current);
            bound(current.leftChild, items);
            bound(current.rightChild, items);
            queue.add(current.leftChild);
            queue.add(current.rightChild);
        }
        MyNode answer = queue.poll();
        for (int i = 0; i < answer.mask.length; i++) {
            if (answer.mask[i] == 1) {
                backpack.addItem(items.get(i));
            }
        }
    }

    private void killNodes(Queue<MyNode> queue, int upperBound) {
        ArrayList<MyNode> reserve = new ArrayList<MyNode>();
        MyNode current;
        while (!queue.isEmpty()) {
            current = queue.poll();
            if (current.costBound <= upperBound) {
                reserve.add(current);
            }
        }
        while (reserve.size() != 0) {
            queue.add(reserve.remove(0));
        }
    }

    private void bound(MyNode node, ArrayList<Item> items) {
        Backpack tmpBackpack = new Backpack(capacity);

        for (int i = node.stopIndex; i < items.size(); i++) {
            if (i == -1) {
                i++;
            }
            if (node.isRight & i == node.stopIndex) {
                node.mask[i] = 0;
                continue;
            }
            if (tmpBackpack.freeSpace >= items.get(i).weight) {
                node.mask[i] = 1;
            } else {
                break;
            }
        }

        for (int i = 0; i < node.mask.length; i++) {
            if (node.mask[i] == 1) {
                tmpBackpack.addItem(items.get(i));
            }
        }

        node.upperBound = tmpBackpack.summaryCost * (-1);
        int freeSpace = tmpBackpack.freeSpace;
        int summaryCostOfUnpicked = 0;
        int summaryWeightOfUnpicked = 0;
        for (int i = 0; i < node.mask.length; i++) {
            if (node.mask[i] == 0 && i != node.stopIndex) {
                summaryCostOfUnpicked += items.get(i).cost;
                summaryWeightOfUnpicked += items.get(i).weight;
            }
        }
        double addable = ((double) summaryCostOfUnpicked / (double) summaryWeightOfUnpicked) * freeSpace;
        node.costBound = node.upperBound + (int) addable * (-1);
    }

    public void createChild(MyNode node) {
        MyNode leftChild = new MyNode(node, false);
        MyNode rightChild = new MyNode(node, true);
        node.leftChild = leftChild;
        node.rightChild = rightChild;
    }

    private static Comparator<Item> weightCostComparator = Comparator.comparingInt((Item i) -> (i.cost / i.weight)).reversed();

    private static Comparator<Item> costComparator = Comparator.comparingInt((Item i) -> i.cost);

    private void createItems() {
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            availableItems.add(new Item(random.nextInt(10) + 1, random.nextInt(10) + 1));
        }
    }
}
