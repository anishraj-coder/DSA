package twoPointer;

import java.util.*;

/**
 * Problem: Longest Substring Without Repeating Characters
 * Description: Given a string s, find the length of the longest substring without duplicate characters.
 */
public class LongestSubstringUniqueChars {

    /**
     * Finds the length of the longest substring without repeating characters.
     * * @param s The input string containing letters, digits, symbols, and spaces.
     * @return The length of the longest unique character substring.
     */
    public int lengthOfLongestSubstring(String s) {
        int n=s.length();
        HashMap<Character,Integer>map=new HashMap<>();
        int i=0,j=0,max=0;
        while(j<n){
            char ch=s.charAt(j);
            map.put(ch,map.getOrDefault(ch,0)+1);
            while(map.get(ch)>1&&i<j){
                char rem=s.charAt(i);
                map.put(rem,map.get(rem)-1);
                i++;
            }
            max=Math.max(max,j-i+1);
            j++;
        }
        return max;
    }

    public static void main(String[] args) {
        LongestSubstringUniqueChars calculator = new LongestSubstringUniqueChars();

        // Test Case Data: {Input, Expected Output}
        Object[][] testCases = {
                {"abcabcbb", 3},            // Standard case: repeating pattern
                {"bbbbb", 1},               // Edge case: all same characters
                {"pwwkew", 3},              // Standard case: internal repeat
                {"", 0},                    // Edge case: empty string
                {" ", 1},                   // Edge case: single space
                {"au", 2},                  // Edge case: two distinct chars
                {"dvdf", 3},                // Hard case: requires sliding window restart/jump
                {"abba", 2},                // Hard case: requires checking if previous index is in current window
                {"tmmzuxt", 5},             // Hard case: multiple repeats at different positions
                {"!@#$%^&*() ", 11},        // Edge case: symbols and spaces
                {"abcdefghijklmnopqrstuvwxyz", 26}, // Boundary case: all unique
                {"aabacbebebe", 3}          // Complexity case: oscillating window size
        };

        int passed = 0;
        int failed = 0;

        System.out.println("Running Tests for: LongestSubstringUniqueChars\n");
        System.out.printf("%-25s | %-10s | %-10s | %-10s%n", "Input String", "Expected", "Actual", "Result");
        System.out.println("--------------------------------------------------------------------------");

        for (Object[] testCase : testCases) {
            String input = (String) testCase[0];
            int expected = (int) testCase[1];
            int actual = calculator.lengthOfLongestSubstring(input);

            String status = (actual == expected) ? "PASSED" : "FAILED";
            if (status.equals("PASSED")) passed++; else failed++;

            // Truncate long input strings for display formatting
            String displayInput = input.length() > 20 ? input.substring(0, 17) + "..." : input;
            System.out.printf("%-25s | %-10d | %-10d | %-10s%n", "\"" + displayInput + "\"", expected, actual, status);
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("Total Tests: %d | Passed: %d | Failed: %d%n", testCases.length, passed, failed);
    }
}