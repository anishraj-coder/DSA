package bitManipulation;

public class IntegerDivider {

    /**
     * Implementation Task:
     * Divide two integers without using multiplication, division, or mod operators.
     * Truncate toward zero and handle 32-bit signed integer overflow.
     * * @param dividend The number to be divided.
     * @param divisor The number to divide by.
     * @return The truncated quotient.
     */
    public int divide(int dividend, int divisor) {
        if(dividend==0)return 0;
        if(divisor==-1)return dividend;
        if(dividend==Integer.MIN_VALUE&&divisor<0)return Integer.MAX_VALUE;
        if(divisor==dividend)return 1;
        int sign=1;
        if(dividend>=0&&divisor<0)sign=-1;
        if(dividend<0&&divisor>0)sign=-1;
        dividend=Math.abs(dividend);
        divisor=Math.abs(divisor);
        long n=dividend,d=divisor,sum=0;
        while(n>=d){
            int count=0;
            while(n>=(d<<(count+1)))count++;

            sum+=(1L<<count);
            n-=(d<<count);
        }
        if(sum>Integer.MAX_VALUE){
            if(sign==-1)return Integer.MIN_VALUE;
            else return Integer.MAX_VALUE;
        }
        return (int)sum*sign;
    }

    public static void main(String[] args) {
        IntegerDivider divider = new IntegerDivider();

        // Test Cases: {dividend, divisor, expected result}
        Object[][] testCases = {
                {10, 3, 3},                    // Standard positive division
                {7, -3, -2},                   // Positive/Negative division
                {0, 1, 0},                     // Dividend is zero
                {1, 1, 1},                     // Equal values
                {-2147483648, -1, 2147483647}, // OVERFLOW CASE: MIN_VALUE / -1
                {-2147483648, 1, -2147483648}, // MIN_VALUE / 1
                {2147483647, 1, 2147483647},   // MAX_VALUE / 1
                {-2147483648, 2, -1073741824}, // MIN_VALUE / 2
                {100, 10, 10},                 // Perfect division
                {-10, 3, -3}                   // Negative/Positive division
        };

        int passed = 0;

        System.out.println("Running Integer Division Tests...\n");
        System.out.printf("%-12s | %-12s | %-12s | %-12s | %-10s%n",
                "Dividend", "Divisor", "Expected", "Actual", "Result");
        System.out.println("-----------------------------------------------------------------------");

        for (Object[] test : testCases) {
            int dividend = (int) test[0];
            int divisor = (int) test[1];
            int expected = (int) test[2];
            int actual = divider.divide(dividend, divisor);

            String status = (actual == expected) ? "PASSED" : "FAILED";
            if (status.equals("PASSED")) passed++;

            System.out.printf("%-12d | %-12d | %-12d | %-12d | %-10s%n",
                    dividend, divisor, expected, actual, status);
        }

        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("Final Score: %d/%d%n", passed, testCases.length);
    }
}