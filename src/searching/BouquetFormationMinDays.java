package searching;

import java.util.Arrays;
import java.util.Scanner;

public class BouquetFormationMinDays {

    /**
     * Finds the minimum number of days to wait to make m bouquets of k adjacent flowers.
     * @param arr Array representing the day each flower blooms.
     * @param m        The number of bouquets needed.
     * @param k        The number of adjacent flowers per bouquet.
     * @return         The minimum days, or -1 if impossible.
     */
    public int minDays(int[] arr, int m, int k) {

        int n=arr.length,max=Integer.MIN_VALUE;
        long needed=(long)m*k;

        if(n<needed)return -1;
        for(int a:arr)max=Integer.max(max,a);
        int low=1,hi=max;
        while(low<=hi){
            int mid=(hi-low)/2+low;
            if(calculate(arr,m,k,mid)>=m){
                hi=mid-1;
            }else low=mid+1;
        }
        if(calculate(arr,m,k,low)>=m)return low;
        return -1;
    }

    public int calculate(int[]arr,int m,int k,int d){

        int flowerRange=0,curr=0;
        for(int b:arr){
            if(b<=d){
                flowerRange++;
                if(flowerRange==k){
                    curr++;
                    flowerRange=0;
                }
            }else flowerRange=0;
        }

        return curr;
    }

    public static void main(String[] args) {
        BouquetFormationMinDays solver = new BouquetFormationMinDays();

        Object[][] testCases = {
                // {bloomDay, m, k, expectedOutput}
                {new int[]{1, 10, 3, 10, 2}, 3, 1, 3},                   // Standard case
                {new int[]{1, 10, 3, 10, 2}, 3, 2, -1},                  // Impossible: not enough flowers
                {new int[]{7, 7, 7, 7, 12, 7, 7}, 2, 3, 12},             // Adjacency check
                {new int[]{1000000000, 1000000000}, 1, 1, 1000000000},   // Large bloom days
                {new int[]{1, 1, 1, 1}, 2, 2, 1},                        // All bloom at once
                {new int[]{1, 2, 4, 9, 3, 4, 1}, 2, 2, 4},               // Distributed blooming
                {new int[]{5, 5, 5, 5, 5}, 2, 3, -1},                    // Enough flowers, but adjacency fails
                {new int[]{1, 10, 2, 9, 3, 8, 4, 7, 5, 6}, 4, 2, 9},     // Alternating high/low days
                {new int[]{100000, 100000}, 100000, 1, -1},              // Edge case: m * k > n
                {new int[]{1, 2, 1, 2, 1, 2, 1}, 3, 2, 2}                // Pattern matching
        };

        int passed = 0;

        System.out.println("Running Tests...\n" + "=".repeat(60));

        for (int i = 0; i < testCases.length; i++) {
            int[] bloomDay = (int[]) testCases[i][0];
            int m = (int) testCases[i][1];
            int k = (int) testCases[i][2];
            int expected = (int) testCases[i][3];

            int actual = solver.minDays(bloomDay, m, k);

            boolean isCorrect = (actual == expected);
            if (isCorrect) passed++;

            System.out.printf("Test Case %d:\n", i + 1);
            System.out.printf("  Input: bloomDay=%s, m=%d, k=%d\n", Arrays.toString(bloomDay), m, k);
            System.out.printf("  Expected: %d\n", expected);
            System.out.printf("  Actual:   %d\n", actual);
            System.out.printf("  Status:   %s\n", isCorrect ? "PASSED ✅" : "FAILED ❌");
            System.out.println("-".repeat(60));
        }

        System.out.printf("\nFinal Result: %d/%d Tests Passed\n", passed, testCases.length);
    }
}