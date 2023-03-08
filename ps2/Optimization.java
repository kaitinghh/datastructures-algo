/**
 * The Optimization class contains a static routine to find the maximum in an array that changes direction at most once.
 */

import java.lang.Math;
public class Optimization {

    /**
     * A set of test cases.
     */
    static int[][] testCases = {
            {1, 3, 5, 7, 9, 11, 10, 8, 6, 4},
            {67, 65, 43, 42, 23, 17, 9, 100},
            {4, -100, -80, 15, 20, 25, 30},
            {2, 3, 4, 5, 6, 7, 8, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83}
    };

    /**
     * Returns the maximum item in the specified array of integers which changes direction at most once.
     *
     * @param dataArray an array of integers which changes direction at most once.
     * @return the maximum item in data Array
     */
    public static int searchMax(int[] dataArray) {
        // TODO: Implement this
        int L = dataArray.length;

        if (L == 0) {
            return 0; //invalid input: empty array
        }
        else if (L == 1) {
            return dataArray[0];
        }
        else if (dataArray[0] > dataArray[1]) { //convex
            return Math.max(dataArray[0], dataArray[L - 1]);
        }
        else { //concave
            return searchMax1(dataArray, 0, L - 1, L);
        }
    }

    private static int searchMax1(int[] arr, int begin, int end, int L) {
        int point = (begin + end) / 2;
        int left = point - 1;
        int right = point + 1;
        if (point == 0) {
            return arr[point] > arr[right] ? arr[point] : searchMax1(arr, right, end, L);
        }
        else if (point == L - 1) {
            return arr[point] > arr[left] ? arr[point] : searchMax1(arr, begin, left, L);
        }
        else if (arr[point] > arr[left] && arr[point] > arr[right]) { //is max
            return arr[point];
        }
        else if (arr[point] > arr[right]) {
            return searchMax1(arr, begin, left, L);
        }
        else {
            return searchMax1(arr, right, end, L);
        }
    }

    /**
     * A routine to test the searchMax routine.
     */
    public static void main(String[] args) {
        for (int[] testCase : testCases) {
            System.out.println(searchMax(testCase));
        }
    }
}
