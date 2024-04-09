import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Java {

    // Reading CSV data
    public static List<Integer> readCsv(String fileName) {
        List<Integer> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                data.add(Integer.parseInt(scanner.next().trim()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    // Timing function
    public static long timeSort(Consumer<List<Integer>> sortFunction, List<Integer> data) {
        long startTime = System.nanoTime();
        sortFunction.accept(new ArrayList<>(data)); // Use a copy of data for each sort
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Selection Sort
    public static void selectionSort(List<Integer> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(j) < arr.get(minIdx)) {
                    minIdx = j;
                }
            }
            int temp = arr.get(minIdx);
            arr.set(minIdx, arr.get(i));
            arr.set(i, temp);
        }
    }

    // Bubble Sort
    public static void bubbleSort(List<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
    }

    // Insertion Sort
    public static void insertionSort(List<Integer> arr) {
        for (int i = 1; i < arr.size(); i++) {
            int key = arr.get(i);
            int j = i - 1;
            while (j >= 0 && arr.get(j) > key) {
                arr.set(j + 1, arr.get(j));
                j = j - 1;
            }
            arr.set(j + 1, key);
        }
    }

    // Merge Sort
    public static void mergeSort(List<Integer> arr) {
        if (arr.size() > 1) {
            int mid = arr.size() / 2;
            List<Integer> l = new ArrayList<>(arr.subList(0, mid));
            List<Integer> r = new ArrayList<>(arr.subList(mid, arr.size()));

            mergeSort(l);
            mergeSort(r);

            int i = 0, j = 0, k = 0;
            while (i < l.size() && j < r.size()) {
                if (l.get(i) <= r.get(j)) {
                    arr.set(k++, l.get(i++));
                } else {
                    arr.set(k++, r.get(j++));
                }
            }
            while (i < l.size()) {
                arr.set(k++, l.get(i++));
            }
            while (j < r.size()) {
                arr.set(k++, r.get(j++));
            }
        }
    }

    // Quick Sort
    public static void quickSort(List<Integer> arr) {
        quickSortRecursive(arr, 0, arr.size() - 1);
    }

    private static void quickSortRecursive(List<Integer> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSortRecursive(arr, low, pi - 1);
            quickSortRecursive(arr, pi + 1, high);
        }
    }

    private static int partition(List<Integer> arr, int low, int high) {
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

    public static void main(String[] args) {
        String fileName = "100000_Random_Numbers.csv"; // Update with your CSV file path
        List<Integer> dataList = readCsv(fileName);
        int numRuns = 10; // Number of runs for averaging

        Consumer<List<Integer>>[] sortingFunctions = new Consumer[]{
            list -> {
                if (list instanceof List) {
                    selectionSort((List<Integer>) list);
                }
            },
            list -> {
                if (list instanceof List) {
                    bubbleSort((List<Integer>) list);
                }
            },
            list -> {
                if (list instanceof List) {
                    insertionSort((List<Integer>) list);
                }
            },
            list -> {
                if (list instanceof List) {
                    mergeSort((List<Integer>) list);
                }
            },
            list -> {
                if (list instanceof List) {
                    quickSort((List<Integer>) list);
                }
            }
        };

        String[] names = {"Selection Sort", "Bubble Sort", "Insertion Sort", "Merge Sort", "Quick Sort"};

        for (int i = 0; i < sortingFunctions.length; i++) {
            long totalDuration = 0;
            for (int j = 0; j < numRuns; j++) {
                totalDuration += timeSort(sortingFunctions[i], dataList);
            }
            double avgDurationMs = totalDuration / (numRuns * 1e6); // Convert nanoseconds to milliseconds
            System.out.println(names[i] + " average duration over " + numRuns + " runs: " + avgDurationMs + " milliseconds.");
        }
    }
}
