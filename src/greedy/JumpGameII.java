package greedy;

public class JumpGameII {

    public int jump(int[] arr) {
        int n=arr.length;
        if(n==1)return 0;
        int i=0,count=0;
        while(i<n) {
        	if(i+arr[i]>=n-1)return count+1;
        	int j=i+1,max=i+1;
        	while(j<n&&j<=arr[i]+i) {
        		if(arr[j]+j>=arr[max]+max)max=j;
        		j++;
        	}
        	i=max;
        	
        	count++;
        }
        return count;
    }

    public static void main(String[] args) {
        JumpGameII game = new JumpGameII();

        int[][] testCases = {
            // Test 1: Standard case (Example 1)
            {2, 3, 1, 1, 4},
            // Test 2: Standard case with zero (Example 2)
            {2, 3, 0, 1, 4},
            // Test 3: Edge Case - Single element (0 jumps needed)
            {0},
            // Test 4: Edge Case - Single element with large value
            {7},
            // Test 5: Two elements (1 jump needed)
            {3, 1},
            // Test 6: Linear step-by-step jumps
            {1, 1, 1, 1, 1},
            // Test 7: Single big jump directly to end
            {5, 1, 1, 1, 1},
            // Test 8: Staggered greedy choices
            {2, 1, 1, 1, 1},
            // Test 9: Forced jump over 0s
            {4, 1, 1, 3, 1, 1, 1},
            // Test 10: Jump choosing middle option for max reach
            {1, 3, 2, 1, 3, 1}
        };

        int[] expectedResults = {
            2, // Test 1
            2, // Test 2
            0, // Test 3
            0, // Test 4
            1, // Test 5
            4, // Test 6
            1, // Test 7
            3, // Test 8
            2, // Test 9
            3  // Test 10
        };

        int passed = 0;
        System.out.println("--- Running Test Cases ---\n");

        for (int i = 0; i < testCases.length; i++) {
            int actual = game.jump(testCases[i]);
            int expected = expectedResults[i];
            boolean isPass = actual == expected;

            if (isPass) {
                passed++;
                System.out.printf("Test Case %2d: PASSED\n", i + 1);
            } else {
                System.out.printf("Test Case %2d: FAILED | Expected: %-3d | Actual: %-3d\n", 
                                  i + 1, expected, actual);
            }
        }

        System.out.printf("\nResult: %d/%d Test Cases Passed.\n", passed, testCases.length);
    }
}