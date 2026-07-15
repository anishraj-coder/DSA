package searching;

import java.util.Objects;

/**
 * Class name derived from the problem: Finding the Floor Square Root.
 * Implementation left to the user.
 */
public class SquareRoot {

    /**
     * Finds the square root of n. If n is not a perfect square,
     * returns the floor value of the square root.
     * @param n The positive integer.
     * @return The floor of the square root.
     */
    public int floorSqrt(long n) {
        long low=1,hi=n/2;
        if(n==0)return 0;
        while(low<=hi){
            long mid=(hi-low)/2+low;
            if(mid*mid==n)return (int)mid;
            else if(mid*mid>n)hi=mid-1;
            else low=mid+1;
        }
        return (int)hi;
    }

    public static void main(String[] args) {
        SquareRoot engine = new SquareRoot();

        // Test Data: {Input, Expected Output}
        long[][] testCases = {
                {36L, 6L},          // Perfect square (Small)
                {28L, 5L},          // Non-perfect square
                {1L, 1L},           // Smallest positive integer
                {2L, 1L},           // Smallest non-perfect prime
                {0L, 0L},           // Edge case: zero
                {1023L, 31L},       // Just below a perfect square (32^2 = 1024)
                {1024L, 32L},       // Perfect square (Boundary)
                {1025L, 32L},       // Just above a perfect square
                {999999L, 999L},    // Large non-perfect square
                {2147483647L, 46340L}, // Integer Max Value boundary
                {10000000000L, 100000L} // Long value perfect square
        };

        int passed = 0;

        System.out.println("--------------------------------------------------");
        System.out.printf("%-15s | %-10s | %-10s | %-10s%n", "Input (n)", "Expected", "Actual", "Result");
        System.out.println("--------------------------------------------------");

        for (long[] test : testCases) {
            long n = test[0];
            int expected = (int) test[1];
            int actual = engine.floorSqrt(n);

            boolean isMatch = (actual == expected);
            if (isMatch) passed++;

            System.out.printf("%-15d | %-10d | %-10d | %-10s%n",
                    n, expected, actual, isMatch ? "PASS" : "FAIL");
        }

        System.out.println("--------------------------------------------------");
        System.out.printf("Final Score: %d/%d cases passed.%n", passed, testCases.length);
        System.out.println("--------------------------------------------------");
    }
}