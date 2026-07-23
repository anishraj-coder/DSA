package DynamicProgramming;

import java.util.Arrays;

public class TargetSum {

    // WRITE YOUR IMPLEMENTATION HERE
    public int findTargetSumWays(int[] arr, int t) {
        int n=arr.length,total=0;
        for(int a:arr)total+=a;
        if(Math.abs(t)>total||(Math.abs(t)+total)%2!=0)return 0;

        int size=(total+Math.abs(t))>>1;
        int[]prev=new int [size+1];
        if(arr[0]==0)prev[0]=2;
        else prev[0]=1;
        if(arr[0]!=0&&arr[0]<=size)prev[arr[0]]=1;

        for(int i=1;i<n;i++){
            int[] curr=new int[size+1];
            for(int j=0;j<=size;j++){
                int pick=(arr[i]<=j)?prev[j-arr[i]]:0;
                int not=prev[j];
                curr[j]=pick+not;
            }
            prev=curr;
        }


        return prev[size];
    }

    private int helper(int[]arr,int i,int curr,int t){
        if(i<0){
            if(t==curr)return 1;
            else return 0;
        }

        int add=helper(arr,i-1,curr+arr[i],t);
        int sub=helper(arr,i-1,curr-arr[i],t);

        return add+sub;
    }

    // Helper class to hold test case data
    static class TestCase {
        int id;
        int[] nums;
        int target;
        int expected;

        TestCase(int id, int[] nums, int target, int expected) {
            this.id = id;
            this.nums = nums;
            this.target = target;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        TargetSum tester = new TargetSum();

        // 10 Comprehensive Test Cases including Edge and Hard Cases
        TestCase[] testCases = new TestCase[] {
                // Case 1: Standard example case 1
                new TestCase(1, new int[]{1, 1, 1, 1, 1}, 3, 5),

                // Case 2: Standard example case 2 (single element match)
                new TestCase(2, new int[]{1}, 1, 1),

                // Case 3: Target cannot be reached (impossible sum)
                new TestCase(3, new int[]{1, 2, 3}, 10, 0),

                // Case 4: Total sum parity mismatch (impossible to divide into integer subsets)
                new TestCase(4, new int[]{1, 2, 4}, 2, 0),

                // Case 5: Target is negative (should be symmetrical to positive target)
                new TestCase(5, new int[]{1, 1, 1, 1, 1}, -3, 5),

                // Case 6: Edge case with multiple zeros (zeros double the number of ways per subset)
                new TestCase(6, new int[]{0, 0, 0, 0, 1}, 1, 16),

                // Case 7: Minimal array length boundary
                new TestCase(7, new int[]{100}, -100, 1),

                // Case 8: All elements are zero, target is zero
                new TestCase(8, new int[]{0, 0, 0}, 0, 8),

                // Case 9: Large target equals the exact sum of elements
                new TestCase(9, new int[]{1, 2, 3, 4, 5}, 15, 1),

                // Case 10: Hard/Max Constraint case (20 elements, balanced target)
                new TestCase(10, new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 0, 184756)
        };

        int passed = 0;

        System.out.println("--- Running TargetSum Tests ---");
        for (TestCase tc : testCases) {
            // Run the implementation
            int actual = tester.findTargetSumWays(tc.nums, tc.target);

            boolean isCorrect = (actual == tc.expected);
            if (isCorrect) {
                passed++;
                System.out.printf("Test Case %d: [PASS]\n", tc.id);
            } else {
                System.out.printf("Test Case %d: [FAIL]\n", tc.id);
                System.out.printf("   Input: nums = %s, target = %d\n", Arrays.toString(tc.nums), tc.target);
                System.out.printf("   Expected: %d\n", tc.expected);
                System.out.printf("   Actual:   %d\n", actual);
            }
        }

        System.out.println("---------------------------------");
        System.out.printf("Result: %d/%d Tests Passed.\n", passed, testCases.length);
    }
}