package searching;

import java.util.Arrays;

public class PackageShippingDistributor {

    /**
     * Problem: Capacity To Ship Packages Within D Days
     * Find the least weight capacity of the ship that will result in all
     * packages being shipped within 'days' days.
     */
    public int shipWithinDays(int[] arr, int days) {
        int n=arr.length,max=Integer.MIN_VALUE,sum=0;
        for(int a:arr){
            max=Math.max(a,max);
            sum+=a;
        }
        int low=max,hi=sum;

        while(low<=hi){
            int mid=(hi-low)/2+low;
            long curr=calculate(arr,mid);
            if(curr<=days){
                hi=mid-1;
            }else low=mid+1;
        }

        return low;
    }

    public long calculate(int[]arr,int capacity){
        int n=arr.length;
        long sum=0,days=0;
        for(int a:arr){
            if(sum+a>capacity){
                days++;
                sum=a;
            }else sum+=a;
        }
        return days+1;
    }

    public static void main(String[] args) {
        PackageShippingDistributor distributor = new PackageShippingDistributor();

        // Test Cases: {weights, days, expectedOutput}
        Object[][] testCases = {
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5, 15},   // Standard case
                {new int[]{3, 2, 2, 4, 1, 4}, 3, 6},                // Standard case
                {new int[]{1, 2, 3, 1, 1}, 4, 3},                   // Multiple small weights
                {new int[]{10, 10, 10, 10}, 4, 10},                 // Capacity equals max element
                {new int[]{10, 10, 10, 10}, 1, 40},                 // Single day (sum of all)
                {new int[]{500}, 1, 500},                           // Single element
                {new int[]{1, 2, 10, 1}, 2, 10},                    // Middle element is very large
                {new int[]{1, 1, 1, 1, 1, 1, 1, 1}, 8, 1},          // Minimum possible capacity
                {new int[]{1, 5, 2, 4, 3}, 3, 5},                   // Partitioning logic check
                {new int[]{10, 20, 30, 40, 50}, 2, 90}              // Large weights, few days
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            int[] weights = (int[]) testCases[i][0];
            int days = (int) testCases[i][1];
            int expected = (int) testCases[i][2];

            int actual = distributor.shipWithinDays(weights, days);

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("  Weights: " + Arrays.toString(weights) + ", Days: " + days);
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual:   " + actual);

            if (actual == expected) {
                System.out.println("  Result:   PASSED");
                passed++;
            } else {
                System.out.println("  Result:   FAILED");
            }
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Total Passed: " + passed + "/" + testCases.length);
    }
}