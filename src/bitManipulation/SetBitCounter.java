package bitManipulation;

public class SetBitCounter {

    /**
     * Implementation Task:
     * Count the number of set bits (1s) in the binary representation of n.
     * * @param n The integer to analyze.
     * @return The total count of set bits.
     */
    public int countSetBits(int n) {
        if(n==0)return 0;
        int count=0;
        for(int i=0;i<32;i++){
            if((n&(1<<i))!=0)count++;
        }
        return count;
    }

    public static void main(String[] args) {
        SetBitCounter counter = new SetBitCounter();

        // Test Cases: {Input, Expected Result}
        // Note: For negative numbers, Java uses 2's complement (32-bit signed).
        Object[][] testCases = {
                {0, 0},                 // No bits set
                {1, 1},                 // 000...0001
                {7, 3},                 // 000...0111
                {8, 1},                 // 000...1000
                {1023, 10},             // 2^10 - 1 (ten 1s)
                {-1, 32},               // All bits set in 2's complement
                {-2, 31},               // 111...1110
                {2147483647, 31},       // Integer.MAX_VALUE (0 followed by thirty-one 1s)
                {-2147483648, 1},       // Integer.MIN_VALUE (1 followed by thirty-one 0s)
                {12345, 6}              // Binary: 11000000111001 (Specific case)
        };

        int passed = 0;

        System.out.println("Running Set Bit Count Tests...\n");
        System.out.printf("%-15s | %-12s | %-12s | %-10s%n", "Input", "Expected", "Actual", "Result");
        System.out.println("---------------------------------------------------------------");

        for (Object[] test : testCases) {
            int input = (int) test[0];
            int expected = (int) test[1];
            int actual = counter.countSetBits(input);

            String status = (actual == expected) ? "PASSED" : "FAILED";
            if (status.equals("PASSED")) passed++;

            System.out.printf("%-15d | %-12d | %-12d | %-10s%n", input, expected, actual, status);
        }

        System.out.println("---------------------------------------------------------------");
        System.out.printf("Final Score: %d/%d%n", passed, testCases.length);
    }
}