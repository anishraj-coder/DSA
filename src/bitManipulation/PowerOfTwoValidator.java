package bitManipulation;

public class PowerOfTwoValidator {

    /**
     * Implementation Task:
     * Determine if a given integer n is a power of two.
     * Constraint: Solve without using loops or recursion.
     * * @param n The integer to check.
     * @return true if n is a power of two, false otherwise.
     */
    public boolean isPowerOfTwo(int n) {
        if(n<=0)return false;
        return (((n-1)&n)==0);
    }

    public static void main(String[] args) {
        PowerOfTwoValidator validator = new PowerOfTwoValidator();

        // Test Cases: {Input, Expected Result}
        Object[][] testCases = {
                {1, true},              // 2^0
                {16, true},             // 2^4
                {3, false},             // Prime, not power of 2
                {0, false},             // Edge case: Zero (not a power of 2)
                {-16, false},           // Edge case: Negative (powers of 2 are positive)
                {1073741824, true},     // 2^30 (Large power of 2)
                {2147483646, false},    // Large even number, not power of 2
                {-2147483648, false},   // Integer.MIN_VALUE
                {536870912, true},      // 2^29
                {2, true}               // 2^1
        };

        int passed = 0;

        System.out.println("Running Power of Two Tests...\n");
        System.out.printf("%-15s | %-12s | %-12s | %-10s%n", "Input", "Expected", "Actual", "Result");
        System.out.println("---------------------------------------------------------------");

        for (Object[] test : testCases) {
            int input = (int) test[0];
            boolean expected = (boolean) test[1];
            boolean actual = validator.isPowerOfTwo(input);

            String status = (actual == expected) ? "PASSED" : "FAILED";
            if (status.equals("PASSED")) passed++;

            System.out.printf("%-15d | %-12b | %-12b | %-10s%n", input, expected, actual, status);
        }

        System.out.println("---------------------------------------------------------------");
        System.out.printf("Final Score: %d/%d%n", passed, testCases.length);
    }
}