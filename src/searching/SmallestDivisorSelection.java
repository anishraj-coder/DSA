package searching;

import java.util.Arrays;

public class SmallestDivisorSelection {

    /**
     * Finds the smallest divisor such that the sum of the division results
     * is less than or equal to the threshold.
     *  @param arr      Array of integers.
     * @param threshold The maximum allowed sum.
     * @return          The smallest positive integer divisor.
     */
    public int smallestDivisor(int[] arr, int threshold) {
        int n=arr.length,max=Integer.MIN_VALUE;
        for(int a:arr){
            max=Math.max(max,a);
        }

        int low=1,hi=max;
        while(low<=hi){
            int mid=(hi-low)/2+low;

            long curr=calculate(arr,mid);

            if(curr<=threshold){
                hi=mid-1;
            }else low=mid+1;
        }
        return low;
    }

    private long calculate(int[]arr,int x){
        if(x==0)return Long.MAX_VALUE;
        long sum=0L;
        for(int a:arr){
            sum += (a + x - 1L) / x;
        }
        return sum;
    }

    public static void main(String[] args) {
        SmallestDivisorSelection solver = new SmallestDivisorSelection();

        Object[][] testCases = {
                // {nums, threshold, expectedOutput}
                {new int[]{1, 2, 5, 9}, 6, 5},               // Standard case
                {new int[]{44, 22, 33, 11, 1}, 5, 44},       // Threshold equals array length
                {new int[]{2147483647}, 2, 1073741824},      // Large single value
                {new int[]{1, 1, 1, 1}, 4, 1},               // Minimum divisor
                {new int[]{1, 1, 1, 1}, 100, 1},             // High threshold
                {new int[]{1000000, 1000000}, 2, 1000000},   // Large values, tight threshold
                {new int[]{19, 12, 8, 4, 8}, 18, 3},         // Mixed values
                {new int[]{2, 4, 8, 16}, 10, 4},             // Powers of 2
                {new int[]{5, 10, 15, 20}, 4, 20},           // Smallest possible sum
                {new int[]{1, 2, 3, 4, 5}, 15, 1}            // Divisor of 1 works
        };

        int passed = 0;

        System.out.println("Running Tests...\n" + "=".repeat(60));

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int threshold = (int) testCases[i][1];
            int expected = (int) testCases[i][2];

            int actual = solver.smallestDivisor(nums, threshold);

            boolean isCorrect = (actual == expected);
            if (isCorrect) passed++;

            System.out.printf("Test Case %d:\n", i + 1);
            System.out.printf("  Input: nums=%s, threshold=%d\n", Arrays.toString(nums), threshold);
            System.out.printf("  Expected: %d\n", expected);
            System.out.printf("  Actual:   %d\n", actual);
            System.out.printf("  Status:   %s\n", isCorrect ? "PASSED ✅" : "FAILED ❌");
            System.out.println("-".repeat(60));
        }

        System.out.printf("\nFinal Result: %d/%d Tests Passed\n", passed, testCases.length);
    }
}