package DynamicProgramming;

import java.util.Arrays;

public class PartitionsWithGivenDifference {

    /**
     * Implement your solution inside this method.
     *
     * @param n The number of elements in the array
     * @param d The desired difference between subset S1 and S2 (S1 >= S2)
     * @param arr The input array of integers
     * @return The number of valid partitions modulo 10^9 + 7
     */
    public static int countPartitions(int n, int d, int[] arr) {
        int total=0,mod=(int)1e9+7;
        for(int a:arr)total+=a;
        if((0L+total+d)%2!=0||d>total)return 0;
        int size=(int)((0L+total+d)>>1);
        int[] prev=new int[size+1];
        if(arr[0]==0)prev[0]=2;
        else prev[0]=1;
        if(arr[0]!=0&&arr[0]<=size)prev[arr[0]]=1;
        for(int i=1;i<n;i++){
            int []curr=new int[size+1];
            for(int j=0;j<=size;j++){
                int pick=(arr[i]<=j)?prev[j-arr[i]]:0;
                int not=prev[j];

                curr[j]=(int)(((long)pick+not)%mod);
            }
            prev=curr;
        }

        return prev[size];
    }

    public static void main(String[] args) {
        // Each test case structure: { {n, d}, {arr_elements...}, {expected_output} }
        TestCase[] testCases = new TestCase[] {
                // 1. Sample 1 - Case 1
                new TestCase(4, 3, new int[]{5, 2, 6, 4}, 1, "Sample 1 - Case 1"),

                // 2. Sample 1 - Case 2 (Multiple duplicate elements resulting in 0 difference)
                new TestCase(4, 0, new int[]{1, 1, 1, 1}, 6, "Sample 1 - Case 2 (All 1s, D=0)"),

                // 3. Sample 2 - Case 1
                new TestCase(3, 1, new int[]{4, 6, 3}, 1, "Sample 2 - Case 1"),

                // 4. Edge Case: Impossible partition because (TotalSum + D) is odd
                new TestCase(4, 2, new int[]{1, 2, 3, 5}, 0, "Impossible partition (Odd Sum + D)"),

                // 5. Edge Case: Impossible partition because D is strictly greater than the total sum
                new TestCase(3, 20, new int[]{1, 2, 3}, 0, "D is greater than total array sum"),

                // 6. Edge Case: Elements containing zeros (Zeros exponentially increase valid paths)
                new TestCase(4, 3, new int[]{0, 0, 3, 0}, 8, "Array filled with multiple zeros"),

                // 7. Standard Case: Single element array
                new TestCase(1, 5, new int[]{5}, 1, "Single element matching D exactly"),

                // 8. Standard Case: D is 0, but elements are distinct and cannot match up
                new TestCase(3, 0, new int[]{1, 2, 4}, 0, "Distinct elements where D=0 is impossible"),

                // 9. Hard Case: Maximum constraint on N, array containing a mix of zeros and values
                new TestCase(20, 5, new int[]{0, 1, 0, 2, 0, 1, 2, 0, 1, 1, 0, 2, 1, 0, 1, 2, 0, 1, 1, 1}, 589824, "Larger input with mixed values and many zeros"),

                // 10. Hard Case: Large array producing a massive amount of partitions that require Modulo behavior
                new TestCase(40, 0, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 564474706, "All 40 zeros (Tests large modulo handling 2^39 % 10^9+7)")
        };

        System.out.println("==========================================================");
        System.out.println("Running Tests for: Partitions With Given Difference");
        System.out.println("==========================================================\n");

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int actual = countPartitions(tc.n, tc.d, tc.arr);
            boolean isCorrect = (actual == tc.expected);

            if (isCorrect) passed++;

            System.out.printf("Test Case %d: %s\n", i + 1, tc.description);
            System.out.printf("Inputs     : N = %d, D = %d, ARR = %s\n", tc.n, tc.d, Arrays.toString(tc.arr));
            System.out.printf("Expected   : %d\n", tc.expected);
            System.out.printf("Actual     : %d\n", actual);
            System.out.printf("Status     : %s\n", isCorrect ? "PASSED ✓" : "FAILED ✗");
            System.out.println("----------------------------------------------------------");
        }

        System.out.printf("\nFinal Result: Passed %d/%d test cases.\n", passed, testCases.length);
        System.out.println("==========================================================");
    }

    // Helper class to store test cases clean and structured
    private static class TestCase {
        int n;
        int d;
        int[] arr;
        int expected;
        String description;

        TestCase(int n, int d, int[] arr, int expected, String description) {
            this.n = n;
            this.d = d;
            this.arr = arr;
            this.expected = expected;
            this.description = description;
        }
    }
}