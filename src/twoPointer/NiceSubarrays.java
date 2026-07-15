package twoPointer;

public class NiceSubarrays {

    /**
     * Given an array of integers nums and an integer k.
     * A continuous subarray is called nice if there are k odd numbers on it.
     * Return the number of nice sub-arrays.
     */
    public int numberOfSubarrays(int[] arr, int k) {

        return helper(arr,k)-helper(arr,k-1);
    }

    public int helper(int[]arr,int k){
        if(k<0)return 0;
        int i=0,j=0,n=arr.length,count=0,odd=0;
        while(j<n){
            odd+=((arr[j]&1)!=0)?1:0;
            while(odd>k){
                odd-=((arr[i]&1)!=0)?1:0;
                i++;
            }
            count+=j-i+1;
            j++;
        }
        return count;
    }

    public static void main(String[] args) {
        NiceSubarrays solver = new NiceSubarrays();

        Object[][] testCases = {
                // {nums, k, expected}
                {new int[]{1, 1, 2, 1, 1}, 3, 2},
                {new int[]{2, 4, 6}, 1, 0},
                {new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2, 16},
                {new int[]{1, 1, 1, 1, 1}, 1, 5},            // Edge case: All odd, k=1
                {new int[]{1, 1, 1, 1, 1}, 5, 1},            // Edge case: All odd, k=n
                {new int[]{2, 2, 2, 2}, 1, 0},               // Edge case: All even, k=1
                {new int[]{1, 2, 1, 2, 1, 2}, 2, 4},         // Mixed alternating
                {new int[]{1}, 1, 1},                        // Single element odd
                {new int[]{2}, 1, 0},                        // Single element even
                {new int[]{1, 0, 1, 0, 1}, 2, 4},            // Zeros and odds mixed
                {new int[]{1, 1, 2, 1, 1, 2, 2, 1}, 3, 5},   // Complex mix
                {new int[]{1, 2, 1, 1, 2, 1}, 2, 9}          // Multiple overlapping subarrays
        };

        int passed = 0;
        System.out.println("--- Starting Test Suite ---\n");

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int k = (int) testCases[i][1];
            int expected = (int) testCases[i][2];

            int actual = solver.numberOfSubarrays(nums, k);
            boolean isPassed = (actual == expected);
            if (isPassed) passed++;

            System.out.printf("Test Case %d: %s\n", i + 1, isPassed ? "PASSED ✅" : "FAILED ❌");
            System.out.printf("   Input: nums = %s, k = %d\n", arrayToString(nums), k);
            System.out.printf("   Expected: %d\n", expected);
            System.out.printf("   Actual:   %d\n", actual);
            System.out.println("--------------------------");
        }

        System.out.printf("\nFinal Result: %d/%d Passed\n", passed, testCases.length);
    }

    private static String arrayToString(int[] nums) {
        if (nums.length > 15) return "[" + nums.length + " elements]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
            if (i < nums.length - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}