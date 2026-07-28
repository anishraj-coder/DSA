package DynamicProgramming;

import java.util.Arrays;

public class HouseRobberII {

    /**
     * Complete this method to solve the House Robber II problem.
     * 
     * @param arr Array representing the amount of money in each house arranged in a circle.
     * @return Maximum money you can rob without robbing adjacent houses.
     */
    public int rob(int[] arr) {
        int n=arr.length;
        if(n==1)return arr[0];
        if(n==2)return Math.max(arr[0], arr[1]);
        int a=helper(arr,0,n-2),b=helper(arr,1,n-1);
        return Math.max(a, b);
    }
    
    private int helper(int[]arr,int start,int end) {
    	if(start>end)return 0;
    	int n=end-start+1;
    	if(n==0)return 0;
    	if(n==1)return arr[start];
    	if(n==2)return Math.max(arr[start], arr[end]);
    	int[]dp=new int[n];
    	dp[0]=arr[start];
    	dp[1]=Math.max(arr[start+1], dp[0]);
    	
    	for(int i=2;i<n;i++)dp[i]=Math.max(arr[start+i]+dp[i-2], dp[i-1]);
    	return dp[n-1];
    }

    public static void main(String[] args) {
        HouseRobberII solver = new HouseRobberII();

        TestCase[] testCases = new TestCase[] {
            // Case 1: Standard circular constraint (Example 1)
            new TestCase(new int[]{2, 3, 2}, 3),
            
            // Case 2: Standard non-adjacent pick (Example 2)
            new TestCase(new int[]{1, 2, 3, 1}, 4),
            
            // Case 3: Pick middle element (Example 3)
            new TestCase(new int[]{1, 2, 3}, 3),
            
            // Case 4: Single house (Edge case)
            new TestCase(new int[]{5}, 5),
            
            // Case 5: Two houses (Must pick the max)
            new TestCase(new int[]{1, 7}, 7),
            
            // Case 6: All houses zero (Edge case)
            new TestCase(new int[]{0, 0, 0, 0}, 0),
            
            // Case 7: High-value ends (Tests circular wrap-around conflict)
            new TestCase(new int[]{200, 3, 140, 20, 10}, 340),
            
            // Case 8: Equal values (Tests picking alternating houses)
            new TestCase(new int[]{10, 10, 10, 10, 10}, 20),
            
            // Case 9: Large peak in the middle
            new TestCase(new int[]{1, 3, 100, 1, 1}, 101),
            
            // Case 10: Alternating high/low values
            new TestCase(new int[]{1, 7, 9, 2, 8, 3, 8, 8, 7}, 27),
            
            // Case 11: Array of length 6 with strong end choices
            new TestCase(new int[]{6, 3, 10, 8, 2, 10}, 19)
        };

        int passed = 0;
        int total = testCases.length;

        System.out.println("==========================================================================");
        System.out.printf("%-11s | %-10s | %-10s | %-8s | %s%n", "Test Case", "Expected", "Actual", "Status", "Details");
        System.out.println("==========================================================================");

        for (int i = 0; i < total; i++) {
            TestCase tc = testCases[i];
            int actual = solver.rob(tc.nums);
            boolean isPassed = (actual == tc.expected);
            if (isPassed) passed++;

            String status = isPassed ? "PASSED" : "FAILED";
            String details = "nums: " + Arrays.toString(tc.nums);

            System.out.printf("%-11d | %-10d | %-10d | %-8s | %s%n", 
                              (i + 1), tc.expected, actual, status, details);
        }

        System.out.println("==========================================================================");
        System.out.printf("SUMMARY: Passed %d / %d test cases. (Failed: %d)%n", passed, total, (total - passed));
        System.out.println("==========================================================================");
    }

    private static class TestCase {
        int[] nums;
        int expected;

        TestCase(int[] nums, int expected) {
            this.nums = nums;
            this.expected = expected;
        }
    }
}