package maths;

import java.util.Map;
import java.util.Objects;

/**
 * Problem: 50. Pow(x, n)
 * Description: Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
 * * Constraints:
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31 - 1
 * n is an integer.
 * Either x is not zero or n > 0.
 * -10^4 <= x^n <= 10^4
 */
public class Power {

    /**
     * Calculates x raised to the power n.
     * * @param x the base
     * @param n the exponent
     * @return the result of x^n
     */
    public double myPow(double x, int n) {
        if(x==0)return 0;
        if(x==1)return 1;
        if(n==1)return x;
        if(n==0)return 1;
        double power=1;
        boolean isNumberNegative=false,isPowerNegative=false;

        x=Math.abs(x);
        if(n<0)isPowerNegative=true;
        n=Math.abs(n);
        if(x<0&&n%2!=0) {
            isNumberNegative = true;
        }
        while(n!=0){
            if(n%2!=0){
                power=power*x;
                n--;
            }
            power*=x*x;
            n/=2;
        }

        if(isNumberNegative){
            power*=-1;
        }
        if(isPowerNegative)power=1/power;

        return power;
    }

    public static void main(String[] args) {
        Power powerInstance = new Power();

        // Test Cases: {x, n, expectedOutput}
        Object[][] testCases = {
                {2.00000, 10, 1024.00000},              // Basic positive power
                {2.10000, 3, 9.26100},                // Basic decimal base
                {2.00000, -2, 0.25000},               // Negative power
                {0.50000, 2, 0.25000},                // Base < 1
                {1.00000, 2147483647, 1.00000},       // Base 1 with Max Int power
                {-1.00000, 2147483647, -1.00000},     // Negative base with odd Max Int power
                {-1.00000, -2147483648, 1.00000},     // Negative base with Min Int power (even)
                {2.00000, -2147483648, 0.00000},      // Underflow case / Min Int handling
                {0.00001, 2147483647, 0.00000},       // Very small result (underflow)
                {1.00000, 0, 1.00000},                // Power of 0
                {-2.00000, 3, -8.00000},              // Negative base, odd power
                {-2.00000, 2, 4.00000}                // Negative base, even power
        };

        int passed = 0;
        int failed = 0;

        System.out.println("Running Test Cases...\n" + "=".repeat(60));

        for (int i = 0; i < testCases.length; i++) {
            double x = (double) testCases[i][0];
            int n = (int) testCases[i][1];
            double expected = (double) testCases[i][2];

            double actual = powerInstance.myPow(x, n);

            // Using a small epsilon for double comparison
            boolean isCorrect = Math.abs(actual - expected) < 1e-5;

            if (isCorrect) {
                passed++;
                System.out.printf("Test Case %d: PASSED | Input: x=%.5f, n=%d | Expected: %.5f | Actual: %.5f\n",
                        i + 1, x, n, expected, actual);
            } else {
                failed++;
                System.err.printf("Test Case %d: FAILED | Input: x=%.5f, n=%d | Expected: %.5f | Actual: %.5f\n",
                        i + 1, x, n, expected, actual);
            }
        }

        System.out.println("=".repeat(60));
        System.out.printf("Summary: %d Passed, %d Failed\n", passed, failed);
    }
}
