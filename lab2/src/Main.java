import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

interface Sorter {
    ArrayList<Integer> sort(ArrayList<Integer> input);
}

class BubbleSorter implements Sorter {
    private static final int THRESHOLD = 10000;
    public ArrayList<Integer> sort(ArrayList<Integer> input) {
        ArrayList<Integer> arr = new ArrayList<Integer>(input);
        if (arr.size() > THRESHOLD) {
            ArrayList<Integer> left = new ArrayList<Integer>(arr.subList(0, arr.size() / 2));
            ArrayList<Integer> right = new ArrayList<Integer>(arr.subList(arr.size() / 2, arr.size()));
            left = sort(left);
            right = sort(right);
            arr = merge(left, right);
        }
        else {
            for (int i = 0; i < arr.size() - 1; i++) {
                for (int j = 0; j < arr.size() - i - 1; j++) {
                    if (arr.get(j) > arr.get(j + 1)) {
                        int temp = arr.get(j);
                        arr.set(j, arr.get(j + 1));
                        arr.set(j + 1, temp);
                    }
                }
            }
        }
        return arr;
    }
    private ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                result.add(left.get(i));
                i++;
            } else {
                result.add(right.get(j));
                j++;
            }
        }
        while (i < left.size()) {
            result.add(left.get(i));
            i++;
        }
        while (j < right.size()) {
            result.add(right.get(j));
            j++;
        }
        return result;
    }
}

class ShellSorter implements Sorter {
    public ArrayList<Integer> sort(ArrayList<Integer> input) {
        ArrayList<Integer> arr = new ArrayList<Integer>(input);
        int n = arr.size();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr.get(i);
                int j;
                for (j = i; j >= gap && arr.get(j - gap) > temp; j -= gap)
                    arr.set(j, arr.get(j - gap));
                arr.set(j, temp);
            }
        }
        return arr;
    }
}

class MergeSorter implements Sorter {
    public ArrayList<Integer> sort(ArrayList<Integer> input) {
        ArrayList<Integer> arr = new ArrayList<Integer>(input);
        mergeSort(arr, 0, arr.size() - 1);
        return arr;
    }
    private void mergeSort(ArrayList<Integer> arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private void merge(ArrayList<Integer> arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int L[] = new int[n1];
        int R[] = new int[n2];
        for (int i = 0; i < n1; ++i)
            L[i] = arr.get(l + i);
        for (int j = 0; j < n2; ++j)
            R[j] = arr.get(m + 1 + j);
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr.set(k, L[i]);
                i++;
            } else {
                arr.set(k, R[j]);
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr.set(k, L[i]);
            i++;
            k++;
        }
        while (j < n2) {
            arr.set(k, R[j]);
            j++;
            k++;
        }
    }
}

class QuickSorter implements Sorter {
    public ArrayList<Integer> sort(ArrayList<Integer> input) {
        ArrayList<Integer> arr = new ArrayList<>(input);
        quickSort(arr, 0, arr.size() - 1);
        return arr;
    }
    private void quickSort(ArrayList<Integer> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(ArrayList<Integer> arr, int low, int high) {
        int pivot = arr.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr.get(j) < pivot) {
                i++;
                int temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }
        int temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);
        return i + 1;
    }
}
enum SortingType {
    BUBBLE_SORT,
    SHELL_SORT,
    MERGE_SORT,
    QUICK_SORT
}

class SorterFactory {
    public static Sorter createSorter(SortingType type) {
        switch (type) {
            case BUBBLE_SORT:
                return new BubbleSorter();
            case SHELL_SORT:
                return new ShellSorter();
            case MERGE_SORT:
                return new MergeSorter();
            case QUICK_SORT:
                return new QuickSorter();
            default:
                throw new IllegalArgumentException("Unknown sorting type: " + type.name());
        }
    }
}

class SortingProgram {
    private static Random rand = new Random();

    public static void main(String[] args) {
        int[] sizes = {10, 1000, 10000, 1000000};
        for (int size : sizes) {
            System.out.println("Array size: " + size);
            ArrayList<Integer> array = generateRandomArray(size, size);
            for (SortingType type : SortingType.values()) {
                System.out.println("Sorting type: " + type.name());
                Sorter sorter = SorterFactory.createSorter(type);
                long time = measureSortingTime(array, sorter);
                System.out.println("Sorting time: " + time + " ns");
                ArrayList<Integer> sorted = sorter.sort(new ArrayList<>(array));
                System.out.println("Sorted array: " + sorted.subList(0, Math.min(50, size)));
            }
        }
    }

    public static ArrayList<Integer> generateRandomArray(int size, int range) {
        ArrayList<Integer> array = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            array.add(rand.nextInt(range));
        }
        return array;
    }

    public static long measureSortingTime(ArrayList<Integer> array, Sorter sorter) {
        long start = System.nanoTime();
        sorter.sort(array);
        long end = System.nanoTime();
        return end - start;
    }
}