package DynamicProgramming;


public class NinjasTraining {

    // Function to implement by the user
    public int getMaxMeritPoints(int[][] arr) {
        int n=arr.length;
        if(n==1){
            int max=Integer.MIN_VALUE;
            for(int a:arr[0])max=Math.max(a,max);
            return max;
        }

        int[]prev=new int[3],dp=new int[3];
        for(int i=0;i<3;i++)prev[i]=arr[0][i];
        for(int i=1;i<n;i++){
            for(int j=0;j<3;j++){
                int max=Integer.MIN_VALUE;
                for(int k=0;k<3;k++){
                    if(j!=k){
                        max=Math.max(arr[i][j]+prev[k],max);
                    }
                }
                dp[j]=max;
            }
            prev= dp.clone();
        }
        int max=Integer.MIN_VALUE;
        for(int a:dp)max=Math.max(a,max);
        return max;
    }



    public static void main(String[] args) {
        NinjasTraining trainer = new NinjasTraining();

        // Test Cases Definitions
        // Structure: { {Day 0 points}, {Day 1 points}, ... } -> Expected Output

        // TC 1: Sample Case 1
        int[][] tc1 = {{1, 2, 5}, {3, 1, 1}, {3, 3, 3}};
        int expected1 = 11;

        // TC 2: Sample Case 2 (Strictly alternate max choices not possible, checks DP state transition)
        int[][] tc2 = {{10, 40, 70}, {20, 50, 80}, {30, 60, 90}};
        int expected2 = 210;

        // TC 3: Single day (Base case)
        int[][] tc3 = {{15, 99, 42}};
        int expected3 = 99;

        // TC 4: Strictly increasing values on one activity, but forces others due to constraints
        int[][] tc4 = {
                {10, 1, 1},
                {10, 1, 1},
                {1, 10, 1}
        };
        int expected4 = 21; // Day 0: 10 (idx 0), Day 1: 1 (idx 1 or 2), Day 2: 10 (idx 1) -> 10 + 1 + 10 = 21

        // TC 5: All values identical
        int[][] tc5 = {
                {5, 5, 5},
                {5, 5, 5},
                {5, 5, 5},
                {5, 5, 5}
        };
        int expected5 = 20;

        // TC 6: Zig-zag dominance (Forcing the choice away from the local maximum)
        int[][] tc6 = {
                {100, 1, 1},
                {1, 100, 1},
                {100, 1, 1},
                {1, 100, 1}
        };
        int expected6 = 400; // Alternating 100s cleanly without consecutive conflict

        // TC 7: Large value gap preventing greedy choice on Day 0
        int[][] tc7 = {
                {5, 5, 90}, // If we pick 90, we block column 2 next day
                {5, 5, 100}, // Next day has 100 at column 2. Picking 90 costs us 100.
                {5, 100, 5}
        };
        // Best: Day 0: 5 (idx 1), Day 1: 100 (idx 2), Day 2: 100 (idx 1) -> 205
        int expected7 = 205;

        // TC 8: All low values except one giant hidden path
        int[][] tc8 = {
                {1, 1, 50},
                {1, 60, 1},
                {70, 1, 1},
                {1, 80, 1}
        };
        int expected8 = 260; // 50 + 60 + 70 + 80 = 260

        // TC 9: Minimum constraint bounds
        int[][] tc9 = {
                {1, 1, 1}
        };
        int expected9 = 1;

        // TC 10: Two days where greedy fails immediately
        int[][] tc10 = {
                {10, 11, 20}, // Greedy wants 20
                {1, 1, 100}   // But picking 20 blocks 100. Best is 11 + 100 = 111.
        };
        int expected10 = 111;

        // Evaluation Array List
        TestCase[] testCases = {
                new TestCase(1, tc1, expected1),
                new TestCase(2, tc2, expected2),
                new TestCase(3, tc3, expected3),
                new TestCase(4, tc4, expected4),
                new TestCase(5, tc5, expected5),
                new TestCase(6, tc6, expected6),
                new TestCase(7, tc7, expected7),
                new TestCase(8, tc8, expected8),
                new TestCase(9, tc9, expected9),
                new TestCase(10, tc10, expected10)
        };

        // Execution Loop
        int passedCount = 0;
        System.out.println("================ NINJA'S TRAINING TEST REPORT ================\n");
        for (TestCase tc : testCases) {
            int actual = trainer.getMaxMeritPoints(tc.input);
            boolean passed = (actual == tc.expected);
            if (passed) passedCount++;

            System.out.printf("Test Case %d:\n", tc.id);
            System.out.printf("  Input Matrix Size : %d x 3\n", tc.input.length);
            System.out.printf("  Expected Output    : %d\n", tc.expected);
            System.out.printf("  Actual Output      : %d\n", actual);
            System.out.printf("  Status             : %s\n\n", passed ? "PASSED ✅" : "FAILED ❌");
        }

        System.out.println("--------------------------------------------------------------");
        System.out.printf("Final Results: %d/%d Test Cases Passed.\n", passedCount, testCases.length);
        System.out.println("==============================================================");
    }

    // Helper class to map test inputs smoothly
    private static class TestCase {
        int id;
        int[][] input;
        int expected;

        TestCase(int id, int[][] input, int expected) {
            this.id = id;
            this.input = input;
            this.expected = expected;
        }
    }
}