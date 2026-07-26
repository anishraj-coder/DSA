package greedy;

public class JumpGame {

    public boolean canJump(int[] arr) {
        int n=arr.length;
        if(n==1)return true;
        int max=0;
        for(int i=0;i<n;i++) {
        	if(i>max)return false;
        	max=Math.max(max, i+arr[i]);
        }
        
        
        return max>=n-1;
    }

    public static void main(String[] args) {
        JumpGame game = new JumpGame();

        int[][] testCases = {
            // Test 1: Standard true case (Example 1)
            {2, 3, 1, 1, 4},
            // Test 2: Standard false case (Example 2)
            {3, 2, 1, 0, 4},
            // Test 3: Edge Case - Single element (already at last index)
            {0},
            // Test 4: Edge Case - Single element with non-zero value
            {5},
            // Test 5: Trapped by zero early on
            {0, 2, 3},
            // Test 6: Zero in the middle that can be jumped over
            {2, 0, 0},
            // Test 7: Large jumps available
            {10, 0, 0, 0, 0, 0, 0},
            // Test 8: Trapped at the second-to-last index by a zero
            {1, 1, 1, 0, 1},
            // Test 9: Exact jumps required to clear zeros
            {2, 5, 0, 0, 0},
            // Test 10: Array with all zeros except first position
            {1, 0, 0, 0}
        };

        boolean[] expectedResults = {
            true,  // Test 1
            false, // Test 2
            true,  // Test 3
            true,  // Test 4
            false, // Test 5
            true,  // Test 6
            true,  // Test 7
            false, // Test 8
            true,  // Test 9
            false  // Test 10
        };

        int passed = 0;
        System.out.println("--- Running Test Cases ---\n");

        for (int i = 0; i < testCases.length; i++) {
            boolean actual = game.canJump(testCases[i]);
            boolean expected = expectedResults[i];
            boolean isPass = actual == expected;

            if (isPass) {
                passed++;
                System.out.printf("Test Case %2d: PASSED\n", i + 1);
            } else {
                System.out.printf("Test Case %2d: FAILED | Expected: %-5b | Actual: %-5b\n", 
                                  i + 1, expected, actual);
            }
        }

        System.out.printf("\nResult: %d/%d Test Cases Passed.\n", passed, testCases.length);
    }
}