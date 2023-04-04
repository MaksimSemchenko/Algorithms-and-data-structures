import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class lab1 {

    public static void main(String[] args) {
        int n = 100000; // количество элементов в списках
        int m = 1000; // количество элементов для вставки

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        fill(arrayList, n, "ArrayList");
        fill(linkedList, n, "LinkedList");

        randomAccess(arrayList, n, "ArrayList");
        randomAccess(linkedList, n, "LinkedList");

        sequentialAccess(arrayList, "ArrayList");
        sequentialAccess(linkedList, "LinkedList");

        insertAtBeginning(arrayList, m, "ArrayList");
        insertAtBeginning(linkedList, m, "LinkedList");

        insertAtEnd(arrayList, m, "ArrayList");
        insertAtEnd(linkedList, m, "LinkedList");

        insertInMiddle(arrayList, m, "ArrayList");
        insertInMiddle(linkedList, m, "LinkedList");
    }

    private static void fill(List<Integer> list, int n, String listType) {
        long start = System.currentTimeMillis();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            list.add(random.nextInt(n));
        }
        long end = System.currentTimeMillis();
        System.out.println("Fill " + listType + ": " + (end - start) + " ms");
    }

    private static void randomAccess(List<Integer> list, int n, String listType) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            int index = (int) (Math.random() * n);
            list.get(index);
        }
        long end = System.currentTimeMillis();
        System.out.println("Random access in " + listType + ": " + (end - start) + " ms");
    }

    private static void sequentialAccess(List<Integer> list, String listType) {
        long start = System.currentTimeMillis();
        for (Integer integer : list) {
            integer++;
        }
        long end = System.currentTimeMillis();
        System.out.println("Sequential access in " + listType + ": " + (end - start) + " ms");
    }

    private static void insertAtBeginning(List<Integer> list, int m, String listType) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < m; i++) {
            list.add(0, i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Insert at beginning of " + listType + ": " + (end - start) + " ms");
    }

    private static void insertAtEnd(List<Integer> list, int m, String listType) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < m; i++) {
            list.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Insert at end of " + listType + ": " + (end - start) + " ms");
    }

    private static void insertInMiddle(List<Integer> list, int m, String listType) {
        long start = System.currentTimeMillis();
        int size = list.size();
        for (int i = 0; i < m; i++) {
            list.add(size / 2, i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Insert in middle of " + listType + ": " + (end - start) + " ms");
    }
}
