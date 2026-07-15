package arrays;

public class SumOfDigits {

    /**
     * Computes the sum of all digits of a given positive integer n.
     *
     * @param n the input integer
     * @return the sum of the digits of n
     */
    public int sumDigits(int n) {

        return helper(n);
    }
    private int helper(int n){
        if(n/10==0)return n;
        return n%10+helper(n/10);
    }
    public static void main(String[] args) {
        SumOfDigits instance = new SumOfDigits();

        // Test Case Definitions: {Input, Expected Output}
        int[][] testCases = {
                {687, 21},       // Example 1
                {12, 3},         // Example 2
                {1, 1},          // Edge: Smallest single digit
                {9, 9},          // Edge: Largest single digit
                {10, 1},         // Trailing zero
                {100000, 1},     // Constraint: Upper bound 10^5
                {99999, 45},     // Constraint: Largest 5-digit number
                {10101, 3},      // Multiple zeros
                {555, 15},       // Repeated digits
                {2048, 14}       // Power of 2
        };

        int passed = 0;

        System.out.println("Running Test Cases for SumOfDigits...");
        System.out.println("------------------------------------");

        for (int i = 0; i < testCases.length; i++) {
            int input = testCases[i][0];
            int expected = testCases[i][1];
            int actual = instance.sumDigits(input);

            boolean isPassed = (actual == expected);
            if (isPassed) passed++;

            System.out.printf("Test Case %d: Input = %-8d | Expected = %-3d | Actual = %-3d | Result = %s%n",
                    (i + 1), input, expected, actual, isPassed ? "PASSED" : "FAILED");
        }

        System.out.println("------------------------------------");
        System.out.printf("Final Result: %d/%d Passed%n", passed, testCases.length);
    }
}