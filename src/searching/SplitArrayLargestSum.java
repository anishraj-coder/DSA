package searching;

import java.util.Arrays;

public class SplitArrayLargestSum {

    /**
     * Splits the array into k non-empty subarrays such that the largest sum
     * of any subarray is minimized.
     * * @param nums The integer array.
     * @param k    The number of subarrays to split into.
     * @return     The minimized largest sum.
     */
    public int splitArray(int[] arr, int k) {
        int n=arr.length;
        long sum=0,max=Integer.MIN_VALUE;
        for(int a:arr){
            sum+=a;
            max=Math.max(a,max);
        }
        long low=max,hi=sum;

        while(low<=hi){
            long mid=(hi-low)/2+low;
            long parts=calculate(arr,mid);
            if(parts<=k){
                hi=mid-1;
            }else low=mid+1;
        }

        return (int)low;
    }
    private long calculate(int[]arr,long limit){
        long sum=0,parts=1;
        for(int a:arr){
            if(sum+a>limit){
                sum=a;
                parts++;
            }else sum+=a;
        }
        return parts;
    }

    public static void main(String[] args) {
        SplitArrayLargestSum solver = new SplitArrayLargestSum();

        // Test Case Definitions: {nums, k, Expected Output}
        Object[][] testCases = {
                {new int[]{7, 2, 5, 10, 8}, 2, 18},        // Standard case
                {new int[]{1, 2, 3, 4, 5}, 2, 9},          // Simple progression
                {new int[]{1, 4, 4}, 3, 4},                // k equals array length
                {new int[]{10, 20, 30, 40}, 1, 100},       // k is 1 (sum of all)
                {new int[]{5, 5, 5, 5}, 2, 10},            // Uniform elements
                {new int[]{1, 2, 10, 1, 2}, 5, 10},        // Max element is the answer
                {new int[]{10, 5, 10, 5, 10}, 2, 25},      // Alternating values
                {new int[]{0, 0, 0, 0}, 2, 0},             // Zeros in array (Edge case)
                {new int[]{1, 100, 1, 100, 1}, 2, 102},    // Large gaps
                {new int[]{1000000, 1000000}, 1, 2000000}  // Large constraints
        };

        int passed = 0;

        System.out.println("Running DSA Practice: Split Array Largest Sum\n");
        System.out.printf("%-5s | %-25s | %-3s | %-10s | %-10s | %-10s%n",
                "No.", "Nums Input", "k", "Expected", "Actual", "Result");
        System.out.println("---------------------------------------------------------------------------------------");

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int k = (int) testCases[i][1];
            int expected = (int) testCases[i][2];

            int actual = solver.splitArray(nums, k);
            boolean isCorrect = (actual == expected);
            if (isCorrect) passed++;

            String arrayStr = Arrays.toString(nums);
            if (arrayStr.length() > 22) arrayStr = arrayStr.substring(0, 19) + "...";

            System.out.printf("%-5d | %-25s | %-3d | %-10d | %-10d | %-10s%n",
                    (i + 1), arrayStr, k, expected, actual, (isCorrect ? "PASSED" : "FAILED"));
        }

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("Final Score: %d/%d%n", passed, testCases.length);
    }
}