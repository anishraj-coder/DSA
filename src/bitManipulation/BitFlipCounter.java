package bitManipulation;

public class BitFlipCounter {

    /**
     * Implementation Task:
     * Calculate the minimum number of bit flips to convert 'start' to 'goal'.
     * * @param start The initial integer.
     * @param goal The target integer.
     * @return The number of bits that need to be flipped.
     */
    public int minBitFlips(int start, int goal) {
        int count=0,n=goal^start;
        while(n!=0){
            count++;
            n=n&(n-1);
        }
        return count;
    }

    public static void main(String[] args) {
        BitFlipCounter counter = new BitFlipCounter();

        // Test Cases: {start, goal, expected result}
        Object[][] testCases = {
                {10, 7, 3},              // Example 1: 1010 vs 0111 -> 3 flips
                {3, 4, 3},               // Example 2: 011 vs 100 -> 3 flips
                {0, 0, 0},               // Same numbers (Zero)
                {15, 15, 0},             // Same numbers (Positive)
                {0, 15, 4},              // All bits different in range
                {1, 2, 2},               // 01 vs 10 -> 2 flips
                {1073741824, 0, 1},      // Power of 2 vs 0
                {2147483647, 0, 31},     // Max Int vs 0
                {Integer.MAX_VALUE, Integer.MIN_VALUE, 32}, // All bits differ in 2's complement
                {12345, 54321, 6}        // Random large integers
        };

        int passed = 0;

        System.out.println("Running Minimum Bit Flips Tests...\n");
        System.out.printf("%-12s | %-12s | %-12s | %-12s | %-10s%n",
                "Start", "Goal", "Expected", "Actual", "Result");
        System.out.println("-----------------------------------------------------------------------");

        for (Object[] test : testCases) {
            int startNum = (int) test[0];
            int goalNum = (int) test[1];
            int expected = (int) test[2];
            int actual = counter.minBitFlips(startNum, goalNum);

            String status = (actual == expected) ? "PASSED" : "FAILED";
            if (status.equals("PASSED")) passed++;

            System.out.printf("%-12d | %-12d | %-12d | %-12d | %-10s%n",
                    startNum, goalNum, expected, actual, status);
        }

        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("Final Score: %d/%d%n", passed, testCases.length);
    }
}