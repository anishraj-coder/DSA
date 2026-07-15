package DynamicProgramming;

import java.util.Arrays;

public class FrogJump {

    // IMPLEMENT YOUR SOLUTION HERE
    public int minEnergy(int[] heights) {

        return helper(heights,heights.length-1,2);
    }

    private int helper(int[]arr,int i,int k){
        if(i==0)return 0;
        int min=Integer.MAX_VALUE;
        for(int j=i-1;j>=0&&j>=i-k;j--){
            int curr=helper(arr,j,k);
            min=Math.min(curr+Math.abs(arr[i]-arr[j]),min);
        }
        return min;
    }

    public static void main(String[] args) {
        FrogJump solver = new FrogJump();

        // Test Cases Definitions
        int[][] testInputs = {
                {2, 1, 3, 5, 4},                        // Test 1: Example 1
                {7, 5, 1, 2, 6},                        // Test 2: Example 2
                {10},                                   // Test 3: Edge Case - Only 1 step (0 energy needed)
                {10, 20},                               // Test 4: Edge Case - Exactly 2 steps
                {10, 30, 20},                           // Test 5: 3 steps (Direct jump vs sequential jump)
                {1, 1, 1, 1, 1},                        // Test 6: Flat heights (0 total energy)
                {5, 10, 15, 20, 25},                    // Test 7: Strictly increasing heights
                {100, 80, 60, 40, 20},                  // Test 8: Strictly decreasing heights
                {1, 100, 1, 100, 1, 100},                // Test 9: Alternating high peaks (tests 2-step jump choice)
                {40, 10, 20, 70, 80, 10, 20, 70, 80, 60} // Test 10: Larger complex wavy terrain
        };

        int[] expectedOutputs = {
                2,   // Test 1
                9,   // Test 2
                0,   // Test 3
                10,  // Test 4
                10,  // Test 5
                0,   // Test 6
                20,  // Test 7
                80,  // Test 8
                4,   // Test 9
                110  // Test 10
        };

        int passed = 0;

        System.out.println("==================================================");
        System.out.printf("%-10s | %-15s | %-15s | %-10s\n", "Test Case", "Expected", "Actual", "Result");
        System.out.println("==================================================");

        for (int i = 0; i < testInputs.length; i++) {
            int[] heights = testInputs[i];
            int expected = expectedOutputs[i];

            // Invoke the user's implementation
            int actual = solver.minEnergy(heights);

            boolean isCorrect = (actual == expected);
            String resultStatus = isCorrect ? "PASSED" : "FAILED";

            if (isCorrect) {
                passed++;
            }

            System.out.printf("Test %-5d  | %-15d | %-15d | %-10s\n", (i + 1), expected, actual, resultStatus);
            if (!isCorrect) {
                System.out.println("   ↳ Failed Input: " + Arrays.toString(heights));
            }
        }

        System.out.println("==================================================");
        System.out.printf("Final Score: %d/%d Test Cases Passed.\n", passed, testInputs.length);
        System.out.println("==================================================");
    }
}