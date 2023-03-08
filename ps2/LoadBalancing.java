/**
 * Contains static routines for solving the problem of balancing m jobs on p processors
 * with the constraint that each processor can only perform consecutive jobs.
 */

public class LoadBalancing {

    /**
     * Checks if it is possible to assign the specified jobs to the specified number of processors such that no
     * processor's load is higher than the specified query load.
     *
     * @param jobSizes the sizes of the jobs to be performed
     * @param queryLoad the maximum load allowed for any processor
     * @param p the number of processors
     * @return true iff it is possible to assign the jobs to p processors so that no processor has more than queryLoad load.
     */
    public static boolean isFeasibleLoad(int[] jobSizes, int queryLoad, int p) {
        // TODO: Implement this
        if (jobSizes.length == 0 || p <= 0 || queryLoad <= 0) {
            return false;
        }
        else if (jobSizes.length == 1) {
            return jobSizes[0] <= queryLoad ? true : false;
        }
        else {
            if (jobSizes[0] > queryLoad) {
                return false;
            } else {
                int point = 0;
                int currLoad = jobSizes[point];
                int count = 0;

                while (point < jobSizes.length - 1) {
                    if (count > p) {
                        return false;
                    } else {
                        if (currLoad + jobSizes[point + 1] <= queryLoad) {
                            point++;
                            currLoad += jobSizes[point];
                            if (point == jobSizes.length - 1) {
                                count++;
                            }
                        } else {
                            if (jobSizes[point + 1] > queryLoad) {
                                return false;
                            } else {
                                currLoad = 0;
                                count++;
                            }
                        }
                    }
                }
                return count > p ? false : true;
            }
        }
    }

    /**
     * Returns the minimum achievable load given the specified jobs and number of processors.
     *
     * @param jobSizes the sizes of the jobs to be performed
     * @param p the number of processors
     * @return the maximum load for a job assignment that minimizes the maximum load
     */
    public static int findLoad(int[] jobSizes, int p) {
        // TODO: Implement this
        int L = 1;
        boolean inputValidity = true;

        if (jobSizes.length == 0) {
            return -1; //invalid input: empty array
        }
        else if (p <= 0) {
            return -1; //invalid input: p <= 0
        }
        else {
            for (int i = 0; i < jobSizes.length; i++) {
                if (jobSizes[i] <= 0) { //invalid input: non-positive job size
                    inputValidity = false;
                    break;
                }
            }

            if (inputValidity) {
                //start binary search
                int max = findMax(jobSizes);
                return binarySearch(jobSizes, p, max, max * jobSizes.length);
            }
            else {
                return -1;
            }
        }
    }

    private static int binarySearch(int[] jobSizes, int p, int begin, int end) {
        int mid = (begin + end) / 2;
        if (!isFeasibleLoad(jobSizes, mid - 1, p) && isFeasibleLoad(jobSizes, mid, p)) { //minimax
            return mid;
        }
        else if (isFeasibleLoad(jobSizes, mid, p)) {
            return binarySearch(jobSizes, p, begin, mid - 1);
        }
        else {
            return binarySearch(jobSizes, p, mid + 1, end);
        }
    }

    private static int findMax(int[] jobSizes) {
        int max = 0;
        for (int i = 0; i < jobSizes.length; i++) {
            if (jobSizes[i] > max) {
                max = jobSizes[i];
            }
        }
        return max;
    }

    // These are some arbitrary testcases.
    public static int[][] testCases = {
            {1, 3, 5, 7, 9, 11, 10, 8, 6, 4},
            {67, 65, 43, 42, 23, 17, 9, 100},
            {4, 100, 80, 15, 20, 25, 30},
            {2, 3, 4, 5, 6, 7, 8, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83},
            {7}
    };

    /**
     * Some simple tests for the findLoad routine.
     */
    public static void main(String[] args) {
        for (int p = 1; p < 30; p++) {
            System.out.println("Processors: " + p);
            for (int[] testCase : testCases) {
                System.out.println(findLoad(testCase, p));
            }
        }
    }
}
