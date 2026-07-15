package bitManipulation;

public class OddEvenChecker {

    /**
     * Implementation Task:
     * Determine if a given integer n is odd.
     * * @param n The integer to check.
     * @return true if the number is odd, false if it is even.
     */
    public boolean isOdd(int n) {

        return ((n&1)!=0);
    }

    public static void main(String[] args) {
        OddEvenChecker checker = new OddEvenChecker();

        // Test Cases: {Input, Expected Result}
        Object[][] testCases = {
                {0, false},            // Edge case: Zero (Even)
                {1, true},             // Smallest positive odd
                {2, false},            // Smallest positive even
                {15, true},            // Positive odd
                {100, false},          // Positive even
                {-1, true},            // Negative odd
                {-2, false},           // Negative even
                {2147483647, true},    // Integer.MAX_VALUE (Odd)
                {-2147483648, false},  // Integer.MIN_VALUE (Even)
                {123456789, true}      // Large odd number
        };

        int passed = 0;

        System.out.println("Running Tests...\n");
        System.out.printf("%-15s | %-12s | %-12s | %-10s%n", "Input", "Expected", "Actual", "Result");
        System.out.println("---------------------------------------------------------------");

        for (Object[] test : testCases) {
            int input = (int) test[0];
            boolean expected = (boolean) test[1];
            boolean actual = checker.isOdd(input);

            String status = (actual == expected) ? "PASSED" : "FAILED";
            if (status.equals("PASSED")) passed++;

            System.out.printf("%-15d | %-12b | %-12b | %-10s%n", input, expected, actual, status);
        }

        System.out.println("---------------------------------------------------------------");
        System.out.printf("Final Score: %d/%d%n", passed, testCases.length);
    }
}