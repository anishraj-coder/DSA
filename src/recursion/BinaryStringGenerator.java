package recursion;

import java.util.*;

/**
 * Problem: Generate Binary Strings Without Adjacent Zeros
 * Constraint: 1 <= n <= 18
 */
public class BinaryStringGenerator {

    /**
     * Generates all valid binary strings of length n.
     * A string is valid if no two '0's are adjacent.
     *
     * @param n length of the binary strings
     * @return List of valid binary strings
     */
    public List<String> validStrings(int n) {
        StringBuilder curr=new StringBuilder();
        List<String>ans=new ArrayList<>();
        helper(curr,n,ans);
        return ans;
    }

    private void helper(StringBuilder curr,int n,List<String>ans){
        int size=curr.length();
        if(size==n){
            ans.add(curr.toString());
            return;
        }
        boolean isZero= size > 0 && curr.charAt(size - 1) == '0';
        if(!isZero){
            curr.append('0');
            helper(curr,n,ans);
            curr.deleteCharAt(size);
        }
        curr.append('1');
        helper(curr,n,ans);
        curr.setLength(size);
    }

    public static void main(String[] args) {
        BinaryStringGenerator instance = new BinaryStringGenerator();

        // Test Cases: {Input N, Expected Output List}
        Map<Integer, List<String>> testCases = new LinkedHashMap<>();

        testCases.put(1, Arrays.asList("0", "1"));
        testCases.put(2, Arrays.asList("01", "10", "11"));
        testCases.put(3, Arrays.asList("010", "011", "101", "110", "111"));
        testCases.put(4, Arrays.asList("0101", "0110", "0111", "1010", "1011", "1101", "1110", "1111"));
        testCases.put(5, Arrays.asList(
                "01010", "01011", "01101", "01110", "01111",
                "10101", "10110", "10111", "11010", "11011", "11101", "11110", "11111"
        ));

        // Edge/Larger cases (Count based validation for very large N to keep console clean)
        int[] largerN = {6, 7, 8, 10, 18};
        int[] expectedCounts = {21, 34, 55, 144, 6765}; // Fibonacci-based counts

        int passed = 0;
        int total = testCases.size() + largerN.length;

        // Run small test cases with full output validation
        for (Map.Entry<Integer, List<String>> entry : testCases.entrySet()) {
            int n = entry.getKey();
            List<String> expected = new ArrayList<>(entry.getValue());
            List<String> actual = instance.validStrings(n);

            Collections.sort(expected);
            if (actual != null) Collections.sort(actual);

            boolean isMatch = expected.equals(actual);
            printResult(n, expected, actual, isMatch);
            if (isMatch) passed++;
        }

        // Run larger test cases with count validation
        for (int i = 0; i < largerN.length; i++) {
            int n = largerN[i];
            int expectedCount = expectedCounts[i];
            List<String> actual = instance.validStrings(n);

            boolean isMatch = (actual != null && actual.size() == expectedCount && isValidSet(actual));
            System.out.println("Test N=" + n + " (Large Constraint)");
            System.out.println("Expected Count: " + expectedCount);
            System.out.println("Actual Count:   " + (actual == null ? 0 : actual.size()));
            System.out.println("Status:         " + (isMatch ? "PASS" : "FAIL"));
            System.out.println("---");
            if (isMatch) passed++;
        }

        System.out.println("Final Result: " + passed + "/" + total + " cases passed.");
    }

    private static void printResult(int n, List<String> expected, List<String> actual, boolean pass) {
        System.out.println("Test N=" + n);
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + actual);
        System.out.println("Status:   " + (pass ? "PASS" : "FAIL"));
        System.out.println("---");
    }

    private static boolean isValidSet(List<String> results) {
        for (String s : results) {
            if (s.contains("00")) return false;
        }
        return true;
    }
}