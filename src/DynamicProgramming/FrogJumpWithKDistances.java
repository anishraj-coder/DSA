package DynamicProgramming;

import java.util.Arrays;

public class FrogJumpWithKDistances {

    // Implement your solution within this method
    public int minEnergy(int[] arr, int k) {
        int n=arr.length;
        int[]dp=new int[n];
        dp[0]=0;
        for(int i=1;i<n;i++){
            int min=Integer.MAX_VALUE;
            for(int j=i-1;j>=0&&j>=i-k;j--){
                int curr=dp[j];
                min=Math.min(curr+Math.abs(arr[i]-arr[j]),min);
            }
            dp[i]=min;
        }


        return dp[n-1];
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
        FrogJumpWithKDistances solver = new FrogJumpWithKDistances();

        // Test Cases Definitions
        int[][][] inputs = {
                // Test Case 1: Standard case from example 1
                {{10, 5, 20, 0, 15}, {2}},
                // Test Case 2: Standard case from example 2
                {{15, 4, 1, 14, 15}, {3}},
                // Test Case 3: Edge Case - Minimum array size (n = 1)
                {{42}, {3}},
                // Test Case 4: Edge Case - Small array size (n = 2), k is larger than needed
                {{10, 30}, {5}},
                // Test Case 5: Edge Case - All elements are identical (0 energy needed)
                {{5, 5, 5, 5, 5}, {2}},
                // Test Case 6: Edge Case - k = 1 (Strictly sequential jumps, acts like standard Frog Jump)
                {{10, 20, 30, 40}, {1}},
                // Test Case 7: Edge Case - k >= n (Can jump directly from start to end if optimal)
                {{40, 10, 20, 70, 45}, {10}},
                // Test Case 8: Strictly increasing heights where jumping maximum distance is not optimal
                {{10, 12, 15, 20, 100}, {3}},
                // Test Case 9: Strictly decreasing heights
                {{100, 80, 60, 30, 10}, {2}},
                // Test Case 10: Alternating heights mimicking steep drops and climbs
                {{10, 90, 20, 80, 30, 70}, {2}}
        };

        int[] expectedOutputs = {
                15, // TC 1
                2,  // TC 2
                0,  // TC 3 (Already at the last step)
                20, // TC 4
                0,  // TC 5
                30, // TC 6 (abs(10-20) + abs(20-30) + abs(30-40))
                5,  // TC 7 (Direct jump from 40 to 45 is best, cost = 5)
                12, // TC 8 (10->12->15->20, then 20->100 is unavoidable. Total: 2+3+5+80=90 is NOT optimal. Wait, let's verify optimal: 10->20 (10), 20->100 (80) = 90. 10->15(5)->100(85)=90. Best is 10->20->100 = 90)
                90, // TC 9 (100->60 (40), 60->10 (50) = 90)
                40  // TC 10 (10->20 (10), 20->30 (10), 30->70 (40) = 60? No: 10->20(10), 20->30(10), 30->70(40)=60. Let's check 10->30(20)->70(40)=60. Alternatively: 10->20(10)->80(60) no. 10->90(80). 10->20(10)->30(10)->70(40) = 60.)
        };

        // Correcting expected outputs for verified math:
        expectedOutputs[7] = 90;
        expectedOutputs[8] = 90;
        expectedOutputs[9] = 60;

        // Run tests
        int passed = 0;
        System.out.println("=========================================");
        System.out.println("RUNNING TEST CASES");
        System.out.println("=========================================");

        for (int i = 0; i < inputs.length; i++) {
            int[] heights = inputs[i][0];
            int k = inputs[i][1][0];
            int expected = expectedOutputs[i];

            // Execute the user's implementation
            int actual = solver.minEnergy(heights, k);

            boolean isCorrect = (actual == expected);
            if (isCorrect) {
                passed++;
            }

            System.out.printf("Test Case %d:\n", i + 1);
            System.out.printf("  Input Heights : %s\n", Arrays.toString(heights));
            System.out.printf("  Input K       : %d\n", k);
            System.out.printf("  Expected Out  : %d\n", expected);
            System.out.printf("  Actual Out    : %d\n", actual);
            System.out.printf("  Result        : %s\n", isCorrect ? "PASSED ✅" : "FAILED ❌");
            System.out.println("-----------------------------------------");
        }

        System.out.printf("Final Score: %d / %d Test Cases Passed.\n", passed, inputs.length);
    }
}