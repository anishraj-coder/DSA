package stacks;

import java.util.*;

public class RemoveKDigits {

    /**
     * Given string num representing a non-negative integer num, and an integer k,
     * return the smallest possible integer after removing k digits from num.
     * * Constraints:
     * 1 <= k <= num.length <= 10^5
     * num consists of only digits.
     */
    public String removeKdigits(String number, int k) {
        int n=number.length();
        if(k>=n)return "0";
        if(k==0)return number;
        Deque<Integer>st=new ArrayDeque<>();
        for(char ch:number.toCharArray()){
            int digit=(int)(ch-'0');
            while(!st.isEmpty()&&k>0&&digit<st.peek()){
                st.pop();
                k--;
            }
            if(!(digit==0&&st.isEmpty()))st.push(digit);
        }
        while(k>0&&!st.isEmpty()){
            st.pop();
            k--;
        }
        if(st.isEmpty())return "0";
        StringBuilder sb=new StringBuilder();
        while (!st.isEmpty()){
            sb.append(st.pop());
        }
        sb.reverse();

        return sb.toString();
    }

    public static void main(String[] args) {
        RemoveKDigits solver = new RemoveKDigits();

        // Test Case Definitions: {num, k, expected}
        String[][] tests = {
                {"1432219", "3", "1219"},       // Standard case
                {"10200", "1", "200"},         // Leading zero handling
                {"10", "2", "0"},              // Remove all digits
                {"4321", "2", "21"},           // Strictly decreasing
                {"12345", "2", "123"},         // Strictly increasing
                {"1111", "2", "11"},           // All digits same
                {"10001", "4", "0"},           // Result results in multiple zeros
                {"9", "1", "0"},                // Single digit, k=1
                {"112", "1", "11"},            // Duplicate digits at end
                {"543216789", "3", "216789"},  // Mixed peak and valley
                {"100", "1", "0"}              // Single non-zero followed by zeros
        };

        int passed = 0;

        System.out.println(String.format("%-15s | %-5s | %-15s | %-15s | %-8s",
                "Input Num", "k", "Expected", "Actual", "Result"));
        System.out.println("--------------------------------------------------------------------------------");

        for (String[] test : tests) {
            String num = test[0];
            int k = Integer.parseInt(test[1]);
            String expected = test[2];

            String actual = solver.removeKdigits(num, k);

            boolean isMatch = expected.equals(actual);
            if (isMatch) passed++;

            System.out.println(String.format("%-15s | %-5d | %-15s | %-15s | %-8s",
                    num, k, expected, actual, isMatch ? "PASSED" : "FAILED"));
        }

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Total Passed: " + passed + "/" + tests.length);
    }
}