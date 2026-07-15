package searching;

import java.util.Arrays;

public class KokoBananaConsumingRate {

    /**
     * Finds the minimum integer k such that Koko can eat all bananas within h hours.
     * @param arr Array of integers representing bananas in each pile.
     * @param h     The total hours available.
     * @return      The minimum eating speed k.
     */
    public int minEatingSpeed(int[] arr, int h) {
        int n=arr.length,max=Integer.MIN_VALUE;
        for(int a:arr)max=Math.max(a,max);
        int low=1,hi=max;
        while(low<hi){
            int mid=(hi-low)/2+low;
            long curr=calculateHours(arr,mid);
            if(curr<=h){
                hi=mid;
            }else low=mid+1;
        }

        return hi;
    }

    private long calculateHours(int[]arr,int x){
        long h=0;
        for(int a:arr){
            h+= (int) Math.ceil((double) a /x);
        }
        return h;
    }

    public static void main(String[] args) {
        KokoBananaConsumingRate solver = new KokoBananaConsumingRate();

        Object[][] testCases = {
                // {piles, h, expectedOutput}
                {new int[]{3, 6, 7, 11}, 8, 4},                          // Standard case
                {new int[]{30, 11, 23, 4, 20}, 5, 30},                  // h equals piles.length
                {new int[]{30, 11, 23, 4, 20}, 6, 23},                  // h slightly more than piles.length
                {new int[]{1000000000}, 1000000000, 1},                 // One massive pile, max hours
                {new int[]{1000000000}, 2, 500000000},                  // One massive pile, limited hours
                {new int[]{312884470, 9518262, 530851375}, 312884469, 4}, // Large h, small k
                {new int[]{1, 1, 1, 1}, 4, 1},                          // Minimum possible values
                {new int[]{5, 5, 5, 5}, 1000000000, 1},                 // Very high h relative to piles
                {new int[]{805306368, 805306368, 805306368}, 1000000000, 3}, // Precision/Rounding check
                {new int[]{2, 2}, 3, 2}                                 // Small case requiring careful rounding
        };

        int passed = 0;

        System.out.println("Running Tests...\n" + "=".repeat(50));

        for (int i = 0; i < testCases.length; i++) {
            int[] piles = (int[]) testCases[i][0];
            int h = (int) testCases[i][1];
            int expected = (int) testCases[i][2];

            int actual = solver.minEatingSpeed(piles, h);

            boolean isCorrect = (actual == expected);
            if (isCorrect) passed++;

            System.out.printf("Test Case %d:\n", i + 1);
            System.out.printf("  Input: piles = %s, h = %d\n", Arrays.toString(piles), h);
            System.out.printf("  Expected: %d\n", expected);
            System.out.printf("  Actual:   %d\n", actual);
            System.out.printf("  Status:   %s\n", isCorrect ? "PASSED ✅" : "FAILED ❌");
            System.out.println("-".repeat(50));
        }

        System.out.printf("\nFinal Result: %d/%d Tests Passed\n", passed, testCases.length);
    }
}