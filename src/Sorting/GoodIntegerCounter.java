package Sorting;

import java.util.Arrays;

public class GoodIntegerCounter {

    /**
     * Finds the count of "Good Integers" in the given array.
     * A Good Integer is an element 'x' such that x is equal to the count of numbers strictly smaller than x in the array.
     *
     * @param arr the input array of integers
     * @return the count of good integers
     */
    public static int countGoodIntegers(int[] arr) {
        int n=arr.length,count=0,lessCount=0;
        if(n==0)return 0;
        if(arr[0]==0){
            count++;
        }
        for(int i=1;i<n;i++){
            if(arr[i]!=arr[i-1])lessCount=i;
            if(lessCount==arr[i])count++;
        }
        return count; // Placeholder return
    }

    public static void main(String[] args) {
        // Test Case Structure: {input_array, expected_output}
        Object[][] testCases = {
                // Basic Cases
                {new int[]{0, 1, 2}, 3},
                {new int[]{1, 2, 3}, 0},
                {new int[]{0, 0, 0}, 3}, // All zeros. Count of numbers < 0 is 0. 0==0. All are good.

                // Duplicates and Mixed
                {new int[]{1, 1, 2}, 1}, // Only 2 is good. Count of nums < 2 is 2 (two 1s). 2==2.
                {new int[]{0, 1, 1, 3}, 2}, // 0 is good (count<0 is 0). 1 is good (count<1 is 1 (the zero)). 3 is not (count<3 is 3, 3!=3? Wait. Elements < 3 are 0,1,1. Count is 3. 3==3. So 3 is good. Total 3? Let's re-evaluate.
                // Re-eval {0, 1, 1, 3}:
                // 0: count(<0) = 0. 0==0. Good.
                // 1: count(<1) = 1 (only 0). 1==1. Good.
                // 1: count(<1) = 1. 1==1. Good.
                // 3: count(<3) = 3 (0,1,1). 3==3. Good.
                // Expected should be 4. I will correct this in the actual test case below.

                // Edge Cases
                {new int[]{}, 0}, // Empty array
                {new int[]{5}, 0}, // Single element > 0. Count(<5) = 0. 5!=0.
                {new int[]{0}, 1}, // Single element 0. Count(<0) = 0. 0==0. Good.

                // Harder / Tricky Cases
                {new int[]{-1, 0, 1}, 2}, // -1: count(<-1)=0. -1!=0. 0: count(<0)=1 (-1). 0!=1. 1: count(<1)=2 (-1,0). 1!=2. Wait.
                // Re-eval {-1, 0, 1}:
                // -1: count(<-1) = 0. -1 != 0.
                // 0: count(<0) = 1 (element -1). 0 != 1.
                // 1: count(<1) = 2 (elements -1, 0). 1 != 2.
                // Expected 0.

                {new int[]{2, 2, 2, 2}, 0}, // Count(<2) = 0. 2!=0.
                {new int[]{0, 0, 1, 1, 2, 2}, 6},
                // 0: count(<0)=0. Good.
                // 0: count(<0)=0. Good.
                // 1: count(<1)=2 (two 0s). 1!=2. Not Good.
                // 1: count(<1)=2. Not Good.
                // 2: count(<2)=4 (two 0s, two 1s). 2!=4. Not Good.
                // 2: count(<2)=4. Not Good.
                // Expected 2.

                {new int[]{1, 2, 2, 3, 3, 3}, 0}
                // 1: count(<1)=0. 1!=0.
                // 2: count(<2)=1. 2!=1.
                // 3: count(<3)=3 (1,2,2). 3==3. Good.
                // 3: Good.
                // 3: Good.
                // Expected 3.
        };

        // Correcting the expected values based on manual dry run above for accuracy
        testCases[4] = new Object[]{new int[]{0, 1, 1, 3}, 4};
        testCases[7] = new Object[]{new int[]{0, 1, 1}, 3};
        testCases[9] = new Object[]{new int[]{0, 0, 1, 1, 2, 2}, 2};
        testCases[10] = new Object[]{new int[]{1, 2, 2, 3, 3, 3}, 3};

        int passed = 0;
        int failed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int[] input = (int[]) testCases[i][0];
            int expected = (int) testCases[i][1];

            // Create a copy for debugging if needed, though not strictly required for output
            int[] inputCopy = Arrays.copyOf(input, input.length);

            int actual = countGoodIntegers(inputCopy);

            boolean isPassed = (actual == expected);
            if (isPassed) {
                passed++;
                System.out.println("Test Case " + (i + 1) + ": PASSED");
            } else {
                failed++;
                System.out.println("Test Case " + (i + 1) + ": FAILED");
            }

            System.out.println("  Input   : " + Arrays.toString(input));
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual  : " + actual);
            System.out.println("-----------------------------------");
        }

        System.out.println("Total Passed: " + passed + "/" + testCases.length);
        System.out.println("Total Failed: " + failed + "/" + testCases.length);
    }
}