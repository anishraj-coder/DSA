package arrays;

import java.sql.SQLOutput;
import java.util.Arrays;

public class TrappingRain {

    /**
     * Computes the total amount of rain water that can be trapped after raining.
     * Given an elevation map represented by an array of non-negative integers.
     *
     * @param arr array representing elevation map where each bar has width 1
     * @return total units of trapped rain water
     */
    public static int trapRainWater(int[] arr) {
        if(arr==null)return 0;
        int n=arr.length;
        if(n<=2)return 0;
        int[]preMax=preMax(arr);
        int[]postMax=postMax(arr);

//        System.out.println("Array:");
//        for(int a:arr)System.out.print(a+",");
//        System.out.println("\nPreMax:");
//        for(int a:preMax)System.out.print(a+",");
//        System.out.println("\nPreMin:");
//        for(int a:postMax) System.out.print(a+",");
        int total=0;
        for(int i=1;i<n-1;i++){
            int contri=Math.min(preMax[i],postMax[i])-arr[i];
            total+=Math.max(contri,0);
        }
        return total;
    }

    public static int[] preMax(int[]arr){
        int n=arr.length;
        int[]preMax=new int[n];
        preMax[0]=arr[0];
        for(int i=1;i<n;i++){
            preMax[i]=Math.max(preMax[i-1],arr[i]);
        }
        return preMax;
    }
    public static int[] postMax(int[]arr){
        int n=arr.length;
        int[]postMax=new int [n];
        postMax[n-1]=arr[n-1];
        for(int i=n-2;i>=0;i--){
            postMax[i]=Math.max(postMax[i+1],arr[i]);
        }
        return postMax;
    }

    public static void main(String[] args) {
        // Test Case Structure: {input_array, expected_output}
        // All expected values manually verified using: water[i] = min(leftMax, rightMax) - height[i]
        Object[][] testCases = {
                // Provided Examples
                {new int[]{0,1,0,2,1,0,1,3,2,1,2,1}, 6},
                {new int[]{4,2,0,3,2,5}, 9},

                // Edge Cases: Single element, empty-like behavior
                {new int[]{1}, 0},
                {new int[]{0}, 0},

                // Edge Cases: Uniform / Monotonic arrays (no trapping possible)
                {new int[]{2,2,2,2}, 0},
                {new int[]{1,2,3,4,5}, 0},
                {new int[]{5,4,3,2,1}, 0},
                {new int[]{0,0,0}, 0},

                // Simple Valley Patterns
                {new int[]{3,0,3}, 3},
                {new int[]{10,0,10}, 10},

                // Complex / Asymmetric Patterns (Hard)
                {new int[]{5,4,1,2}, 1},
                {new int[]{3,0,2,0,4}, 7}
        };

        int passed = 0;
        int failed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int[] input = (int[]) testCases[i][0];
            int expected = (int) testCases[i][1];

            // Create a defensive copy to prevent accidental mutation by implementation
            int[] inputCopy = Arrays.copyOf(input, input.length);

            int actual = trapRainWater(inputCopy);

            boolean isPassed = (actual == expected);
            if (isPassed) {
                passed++;
                System.out.println("Test Case " + (i + 1) + ": PASSED ✓");
            } else {
                failed++;
                System.out.println("Test Case " + (i + 1) + ": FAILED ✗");
            }

            System.out.println("  Input   : " + Arrays.toString(input));
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual  : " + actual);
            System.out.println("-----------------------------------");
        }

        System.out.println("\n=== SUMMARY ===");
        System.out.println("Total Passed: " + passed + "/" + testCases.length);
        System.out.println("Total Failed: " + failed + "/" + testCases.length);
    }
}