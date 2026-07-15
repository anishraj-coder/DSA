package searching;

import java.util.*;

public class GasStationPlacement {

    /**
     * Finds the minimum value of the maximum distance between adjacent gas stations
     * after adding 'k' new gas stations.
     * @param arr The sorted array of existing gas station positions.
     * @param k   The number of new gas stations to add.
     * @return    The minimized maximum distance.
     */
    public double minimizeMaxDistance(int[] arr, int k) {
        int n=arr.length;
        double low=0,hi=-1;
        for(int i=0;i<n-1;i++){
            hi=Math.max(hi,arr[i+1]-arr[i]);
        }
        double limit=1e-6;
        while(hi-low>limit){
            double mid=(hi-low)/2.0+low;
            int required=calculateRequired(arr,mid);
            if(required>k)low=mid;
            else hi=mid;

        }
        return hi;
    }

    public int calculateRequired(int[]arr,double dist){
        int required=0,n=arr.length;
        for(int i=0;i<n-1;i++){
            int diff=arr[i+1]-arr[i];
            int needed=(int)Math.floor((diff)*1.0/dist);
            if(needed*dist==diff*1.0/dist)needed--;
            required+=needed;
        }
        return required;
    }

    public static void main(String[] args) {
        GasStationPlacement solver = new GasStationPlacement();
        int passed = 0;
        int total = 10;

        // Test Case 1: Sample Case 1
        passed += runTest(1, solver, new int[]{1, 2, 3, 4, 5}, 4, 0.5);

        // Test Case 2: Sample Case 2
        passed += runTest(2, solver, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 1, 1.0);

        // Test Case 3: Large k, small gap
        passed += runTest(3, solver, new int[]{1, 7}, 6, 0.857142857);

        // Test Case 4: Single large gap vs small gaps
        passed += runTest(4, solver, new int[]{1, 10, 11, 12}, 2, 3.0);

        // Test Case 5: Large positions, k = 1
        passed += runTest(5, solver, new int[]{10, 100}, 1, 45.0);

        // Test Case 6: Multiple large gaps
        passed += runTest(6, solver, new int[]{1, 21, 41}, 2, 10.0);

        // Test Case 7: k is much larger than n
        passed += runTest(7, solver, new int[]{1, 2}, 1000000, 0.000000999);

        // Test Case 8: Array with large values (Constraints check)
        passed += runTest(8, solver, new int[]{1000000, 2000000, 3000000}, 1, 500000.0);

        // Test Case 9: Minimum n and minimum k
        passed += runTest(9, solver, new int[]{1, 10}, 1, 4.5);

        // Test Case 10: Non-uniform distribution
        passed += runTest(10, solver, new int[]{1, 5, 20, 21}, 4, 5.0);

        System.out.println("\n---------------------------------------");
        System.out.println("Final Result: " + passed + "/" + total + " cases passed.");
        System.out.println("---------------------------------------");
    }

    private static int runTest(int id, GasStationPlacement solver, int[] arr, int k, double expected) {
        double actual = solver.minimizeMaxDistance(arr, k);
        // Precision check for 10^-6
        boolean isCorrect = Math.abs(actual - expected) < 1e-6;

        System.out.printf("Test Case %d: %s\n", id, isCorrect ? "PASSED" : "FAILED");
        System.out.printf("   Input: arr=%s, k=%d\n", Arrays.toString(arr), k);
        System.out.printf("   Expected: %.9f\n", expected);
        System.out.printf("   Actual:   %.9f\n", actual);
        System.out.println();

        return isCorrect ? 1 : 0;
    }
}