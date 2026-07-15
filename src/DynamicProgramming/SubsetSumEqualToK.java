package DynamicProgramming;

import java.util.Arrays;

public class SubsetSumEqualToK {

    /**
     * Checks if there exists a subset in 'arr' with a sum equal to 'k'.
     *
     * @param n   The number of elements in the array.
     * @param k   The target sum.
     * @param arr The array of positive integers (may contain 0 based on constraints).
     * @return true if a subset exists with sum equal to k, otherwise false.
     */
    public boolean subsetSumToK(int n, int k, int[] arr) {
        boolean[][]dp=new boolean[n][k+1];
        for(int i=0;i<n;i++)dp[i][0]=true;

        boolean[]prev=new boolean[k+1];
        if(arr[0]<=k){
            prev[arr[0]]=true;
        }
        prev[0]=true;
        for(int i=1;i<n;i++){
            boolean[]curr=new boolean[k+1];
            curr[0]=true;
            for(int j=1;j<=k;j++){
                boolean take=false, not=prev[j];
                if(arr[i]<=j){
                    take=prev[j-arr[i]];
                }

                curr[j]=take||not;
            }
            prev=curr;
        }
        return prev[k];
    }

    public static void main(String[] args) {
        SubsetSumEqualToK solver = new SubsetSumEqualToK();
        int passedTests = 0;
        int totalTests = 10;

        // Test Case 1: Sample Input 1 - Example 1
        passedTests += runTestCase(1, solver, 4, 5, new int[]{4, 3, 2, 1}, true);

        // Test Case 2: Sample Input 1 - Example 2
        passedTests += runTestCase(2, solver, 5, 4, new int[]{2, 5, 1, 6, 7}, false);

        // Test Case 3: Sample Input 2 - Example 1
        passedTests += runTestCase(3, solver, 4, 4, new int[]{6, 1, 2, 1}, true);

        // Test Case 4: Target K is 0 (Empty subset sum is always 0)
        passedTests += runTestCase(4, solver, 3, 0, new int[]{5, 8, 2}, true);

        // Test Case 5: Exact match with a single element
        passedTests += runTestCase(5, solver, 4, 10, new int[]{1, 2, 10, 4}, true);

        // Test Case 6: Array contains elements larger than K (should be ignored safely)
        passedTests += runTestCase(6, solver, 3, 6, new int[]{12, 2, 4}, true);

        // Test Case 7: Sum of all elements is less than K
        passedTests += runTestCase(7, solver, 4, 20, new int[]{1, 2, 3, 4}, false);

        // Test Case 8: Array includes zeros (0 is explicitly within constraints 0 <= ARR[i])
        passedTests += runTestCase(8, solver, 4, 5, new int[]{0, 2, 0, 3}, true);

        // Test Case 9: Large elements that exceed K but can't form K
        passedTests += runTestCase(9, solver, 3, 5, new int[]{1000000, 1000000000, 6}, false);

        // Test Case 10: No single element matches, but a combination of multiple elements does
        passedTests += runTestCase(10, solver, 6, 9, new int[]{1, 1, 1, 2, 2, 2}, true);

        System.out.println("\n-------------------------------------------");
        System.out.println("Result: " + passedTests + "/" + totalTests + " test cases passed.");
        System.out.println("-------------------------------------------");
    }

    private static int runTestCase(int id, SubsetSumEqualToK solver, int n, int k, int[] arr, boolean expected) {
        try {
            boolean obtained = solver.subsetSumToK(n, k, arr);
            if (obtained == expected) {
                System.out.println("Test Case " + id + ": [PASS]");
                return 1;
            } else {
                System.out.println("Test Case " + id + ": [FAIL]");
                System.out.println("   Input arr: " + Arrays.toString(arr) + ", K: " + k);
                System.out.println("   Expected : " + expected);
                System.out.println("   Obtained : " + obtained);
                return 0;
            }
        } catch (Exception e) {
            System.out.println("Test Case " + id + ": [FAIL due to Exception]");
            e.printStackTrace();
            return 0;
        }
    }
}