package bitManipulation;

public class RightmostUnsetBitModifier {

    /**
     * Implementation Task:
     * Set the rightmost unset bit (the first 0 from the right) of n to 1.
     * Example: 1011 (11) -> 1111 (15)
     * Example: 1000 (8) -> 1001 (9)
     * * @param n The integer to modify.
     * @return The integer with its rightmost unset bit turned to 1.
     */
    public int setRightmostUnsetBit(int n) {
        // TODO: Implement logic here
        return (n|(n+1));
    }

    public static void main(String[] args) {
        RightmostUnsetBitModifier modifier = new RightmostUnsetBitModifier();

        // Test Cases: {Input, Expected Result}
        Object[][] testCases = {
                {11, 15},               // Binary: 1011 -> 1111
                {8, 9},                 // Binary: 1000 -> 1001
                {0, 1},                 // Binary: 0000 -> 0001 (Edge case: zero)
                {7, 15},                // Binary: 0111 -> 1111
                {15, 31},               // Binary: 01111 -> 11111
                {-1, -1},               // Binary: All 1s -> No unset bits (stays same)
                {-2, -1},               // Binary: 111...1110 -> 111...1111
                {2147483646, 2147483647}, // Max Int - 1 -> Max Int
                {10, 11},               // Binary: 1010 -> 1011
                {20, 21}                // Binary: 10100 -> 10101
        };

        int passed = 0;

        System.out.println("Running Set Rightmost Unset Bit Tests...\n");
        System.out.printf("%-15s | %-12s | %-12s | %-10s%n", "Input", "Expected", "Actual", "Result");
        System.out.println("---------------------------------------------------------------");

        for (Object[] test : testCases) {
            int input = (int) test[0];
            int expected = (int) test[1];
            int actual = modifier.setRightmostUnsetBit(input);

            String status = (actual == expected) ? "PASSED" : "FAILED";
            if (status.equals("PASSED")) passed++;

            System.out.printf("%-15d | %-12d | %-12d | %-10s%n", input, expected, actual, status);
        }

        System.out.println("---------------------------------------------------------------");
        System.out.printf("Final Score: %d/%d%n", passed, testCases.length);
    }
}