package DynamicProgramming;

import java.util.Arrays;

public class PartitionEqualSubsetSum {

    /**
     * Checks if the array can be partitioned into two subsets such that
     * the sum of the elements in both subsets is equal.
     *
     * @param arr The input array of integers.
     * @return true if an equal partition exists, otherwise false.
     */
    public boolean canPartition(int[] arr) {
        int n=arr.length;
        int sum=0;
        for(int a:arr)sum+=a;
        if(sum%2!=0)return false;
        int k=sum/2;
        boolean[]prev=new boolean[k+1];
        prev[0]=true;
        if(arr[0]<=k){
            prev[arr[0]]=true;
        }

        for(int i=1;i<n;i++){
            boolean []curr=new boolean[k+1];
            curr[0]=true;
            for(int j=1;j<=k;j++){
                boolean not=prev[j],take=false;
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
        PartitionEqualSubsetSum solver = new PartitionEqualSubsetSum();
        int passedTests = 0;
        int totalTests = 10;

        // Test Case 1: Example 1 (Valid partition possible)
        passedTests += runTestCase(1, solver, new int[]{1, 5, 11, 5}, true);

        // Test Case 2: Example 2 (Total sum is odd, partition impossible)
        passedTests += runTestCase(2, solver, new int[]{1, 2, 3, 5}, false);

        // Test Case 3: Minimum constraints (Array size 1, partition impossible)
        passedTests += runTestCase(3, solver, new int[]{100}, false);

        // Test Case 4: Array size 2 with identical elements (Valid partition)
        passedTests += runTestCase(4, solver, new int[]{20, 20}, true);

        // Test Case 5: Total sum is even, but no subset can sum up to totalSum / 2
        passedTests += runTestCase(5, solver, new int[]{1, 2, 5}, false);

        // Test Case 6: All elements are identical, even length (Valid partition)
        passedTests += runTestCase(6, solver, new int[]{3, 3, 3, 3}, true);

        // Test Case 7: All elements are identical, odd length (Partition impossible)
        passedTests += runTestCase(7, solver, new int[]{4, 4, 4}, false);

        // Test Case 8: Large array elements matching perfectly
        passedTests += runTestCase(8, solver, new int[]{90, 10, 20, 30, 40, 10}, true);

        // Test Case 9: One element is strictly greater than the half-sum
        passedTests += runTestCase(9, solver, new int[]{10, 2, 3, 3}, false);

        // Test Case 10: Multi-element combination required to match one heavy element
        passedTests += runTestCase(10, solver, new int[]{2, 2, 2, 2, 8}, true);

        System.out.println("\n-------------------------------------------");
        System.out.println("Result: " + passedTests + "/" + totalTests + " test cases passed.");
        System.out.println("-------------------------------------------");
    }

    private static int runTestCase(int id, PartitionEqualSubsetSum solver, int[] nums, boolean expected) {
        try {
            boolean obtained = solver.canPartition(nums);
            if (obtained == expected) {
                System.out.println("Test Case " + id + ": [PASS]");
                return 1;
            } else {
                System.out.println("Test Case " + id + ": [FAIL]");
                System.out.println("   Input nums: " + Arrays.toString(nums));
                System.out.println("   Expected  : " + expected);
                System.out.println("   Obtained  : " + obtained);
                return 0;
            }
        } catch (Exception e) {
            System.out.println("Test Case " + id + ": [FAIL due to Exception]");
            e.printStackTrace();
            return 0;
        }
    }
}