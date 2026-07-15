package twoPointer;

import java.util.*;

public class SubsequenceSumCondition {

    /**
     * Return the number of non-empty subsequences of nums such that the sum of
     * the minimum and maximum element is less or equal to target.
     * Since the answer may be too large, return it modulo 10^9 + 7.
     */
    public int numSubseq(int[] arr, int target) {
        int n=arr.length,mod=(int)1e9 +7 ;
        if(n==1){
            return ((arr[0]<<1)<=target)?1:0;
        }
        int count=0;
        int[]pow=new int[n];
        pow[0]=1;
        for(int i=1;i<n;i++)pow[i]=(pow[i-1]<<1)%mod;
        Arrays.sort(arr);
        int j=n-1;
        for(int i=0;i<=j;i++){
            while(i<=j&&arr[i]+arr[j]>target)j--;
            if(i<=j){
                int power=pow[j-i]%mod;
                count=(count+power)%mod;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        SubsequenceSumCondition instance = new SubsequenceSumCondition();
        int mod = 1000000007;

        Object[][] tests = {
                {new int[]{3, 5, 6, 7}, 9, 4, "Basic case"},
                {new int[]{3, 3, 6, 8}, 10, 6, "Repeated numbers"},
                {new int[]{2, 3, 3, 4, 6, 7}, 12, 61, "Large array with mixed values"},
                {new int[]{5, 2, 4, 1, 7, 6, 8}, 16, 127, "Unsorted input"},
                {new int[]{10}, 20, 1, "Single element (within target)"},
                {new int[]{10}, 5, 0, "Single element (exceeds target)"},
                {new int[]{1, 1, 1}, 2, 7, "All identical elements"},
                {new int[]{10, 20, 30}, 5, 0, "All elements exceed target"},
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10, 126, "Range of elements"},
                {generateLargeArray(100000, 1), 2, 999999937, "Max constraint (Result modded)"}
        };

        int passed = 0;
        for (int i = 0; i < tests.length; i++) {
            int[] nums = (int[]) tests[i][0];
            int target = (int) tests[i][1];
            int expected = (int) tests[i][2];
            String description = (String) tests[i][3];

            int actual = instance.numSubseq(nums.clone(), target);

            System.out.println("Test Case " + (i + 1) + ": " + description);
            System.out.println("Expected: " + expected + ", Actual: " + actual);

            if (actual == expected) {
                System.out.println("Result: PASSED");
                passed++;
            } else {
                System.out.println("Result: FAILED");
            }
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Score: " + passed + "/" + tests.length);
    }

    private static int[] generateLargeArray(int size, int value) {
        int[] arr = new int[size];
        Arrays.fill(arr, value);
        return arr;
    }
}