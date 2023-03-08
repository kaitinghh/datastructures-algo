import java.util.Random;
import java.util.Arrays;
import java.text.DecimalFormat;

public class SortingTester {

    //checks if sorting algorithm sorts array
    public static boolean checkSort(ISort sorter, int size) {
        // TODO: implement this
        KeyValuePair[] testArray = new KeyValuePair[size];
        Random random = new Random();
        int val = 0;
        for (int i = 0; i < size; i++) {
            testArray[i] = new KeyValuePair(random.nextInt(200) - 100, val);
            val++;
        }

        long sortCost = sorter.sort(testArray);

        boolean sorted = true;
        for (int i = 0; i < size - 1; i++) {
            if (testArray[i + 1].getKey() < testArray[i].getKey()) { //compare keys to ensure ascending
                sorted = false;
                break;
            }
        }
        return sorted;
    }

    //checks if sorting algorithm is stable
    public static boolean isStable(ISort sorter, int size) {
        // TODO: implement this
        KeyValuePair[] testArray = new KeyValuePair[size];
        int val = 0;
        for (int i = 0; i < size; i++) {
            testArray[i] = new KeyValuePair(1, val); //generate array with all elems '1' to check stability
            val++;
        }

        long sortCost = sorter.sort(testArray);

        boolean stability = true;
        for (int i = 0; i < size - 1; i++) {
            if (testArray[i + 1].getKey() == testArray[i].getKey()) {
                if (testArray[i + 1].getValue() < testArray[i].getValue()) { //compare values to check stability
                    stability = false;
                    break;
                }
            }
        }
        return stability;
    }

    //returns summary of checkSort
    public static void checkSortSummary() { 
        System.out.println("Checking sort:");
        System.out.println("SorterA" + checkSort(new SorterA(), 10000));
        System.out.println("SorterB" + checkSort(new SorterB(), 10000));
        System.out.println("SorterC" + checkSort(new SorterC(), 10000));
        System.out.println("SorterD" + checkSort(new SorterD(), 10000));
        System.out.println("SorterE" + checkSort(new SorterE(), 10000));
        System.out.println("SorterF" + checkSort(new SorterF(), 10000));
    }

    //returns summary of isStable
    public static void isStableSummary() {
        System.out.println("Checking stability:");
        System.out.println("SorterA" + isStable(new SorterA(), 10000));
        System.out.println("SorterB" + isStable(new SorterB(), 10000));
        System.out.println("SorterC" + isStable(new SorterC(), 10000));
        System.out.println("SorterD" + isStable(new SorterD(), 10000));
        System.out.println("SorterE" + isStable(new SorterE(), 10000));
        System.out.println("SorterF" + isStable(new SorterF(), 10000));
    }

    //returns cost of sorting, random input array
    public static long cost(ISort sorter, int size) {
        // TODO: implement this
        KeyValuePair[] testArray = new KeyValuePair[size];
        Random random = new Random();
        int val = 0;
        for (int i = 0; i < size; i++) {
            testArray[i] = new KeyValuePair(random.nextInt(200) - 100, val);
            val++;
        }

        long sortCost = sorter.sort(testArray);

        return sortCost;
    }

    //returns sort cost ratio as input array size doubles, random input array
    public static String sortCostRatio(ISort sorter) {
        double[] arr = new double[4];
        arr[0] = (double) cost(sorter, 2000) / cost(sorter, 1000);
        arr[1] = (double) cost(sorter, 4000) / cost(sorter, 2000);
        arr[2] = (double) cost(sorter, 8000) / cost(sorter, 4000);
        arr[3] = (double) cost(sorter, 16000) / cost(sorter, 8000);
        return "[" + arr[0] + ", " + arr[1] + ", " + arr[2] + ", " + arr[3] + "]";
    }

    //returns runtime summary, random input array
    public static void runtimeSummary(ISort sorter, String sorterStr) {
        System.out.println("Runtime summary of" + sorterStr);
        System.out.println(sortCostRatio(sorter));
    }

    //returns cost of sorting, sorted input array
    public static long bestCost(ISort sorter, int size) {
        // TODO: implement this
        KeyValuePair[] testArray = new KeyValuePair[size];
        int key = 0;
        int val = 0;
        for (int i = 0; i < size; i++) {
            testArray[i] = new KeyValuePair(key, val);
            key++;
            val++;
        }

        long sortCost = sorter.sort(testArray);

        return sortCost;
    }

    //returns sort cost ratio as input array size doubles, sorted input array
    public static String bestSortCostRatio(ISort sorter) {
        double[] arr = new double[4];
        arr[0] = (double) bestCost(sorter, 2000) / bestCost(sorter, 1000);
        arr[1] = (double) bestCost(sorter, 4000) / bestCost(sorter, 2000);
        arr[2] = (double) bestCost(sorter, 8000) / bestCost(sorter, 4000);
        arr[3] = (double) bestCost(sorter, 16000) / bestCost(sorter, 8000);
        return "[" + arr[0] + ", " + arr[1] + ", " + arr[2] + ", " + arr[3] + "]";
    }

    //returns runtime summary, sorted input array
    public static void bestRuntimeSummary(ISort sorter, String sorterStr) {
        System.out.println("Runtime summary of " + sorterStr);
        System.out.println(bestSortCostRatio(sorter));
    }

    //returns cost of sorting, sorts input array [1, 1, 1, ...., 0]
    public static long CFCost(ISort sorter, int size) {
        // TODO: implement this
        KeyValuePair[] testArray = new KeyValuePair[size];
        int val = 0;
        for (int i = 0; i < size - 1; i++) {
            testArray[i] = new KeyValuePair(1, val);
            val++;
        }
        testArray[size - 1] = new KeyValuePair(0, val);

        long sortCost = sorter.sort(testArray);

        return sortCost;
    }

    //returns sort cost ratio as input array size doubles, sorts input array [1, 1, 1, ...., 0]
    public static String CFSortCostRatio(ISort sorter) {
        double[] arr = new double[4];
        arr[0] = (double) CFCost(sorter, 2000) / CFCost(sorter, 1000);
        arr[1] = (double) CFCost(sorter, 4000) / CFCost(sorter, 2000);
        arr[2] = (double) CFCost(sorter, 8000) / CFCost(sorter, 4000);
        arr[3] = (double) CFCost(sorter, 16000) / CFCost(sorter, 8000);
        return "[" + arr[0] + ", " + arr[1] + ", " + arr[2] + ", " + arr[3] + "]";
    }

    //returns runtime summary, sorts input array [1, 1, 1, ...., 0]
    public static void CFRuntimeSummary(ISort sorter, String sorterStr) {
        System.out.println("Runtime summary of " + sorterStr);
        System.out.println(CFSortCostRatio(sorter));
    }

    public static void main(String[] args) {
        // TODO: implement this
    }
}
