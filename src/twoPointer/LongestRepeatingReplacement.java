package twoPointer;

import java.util.*;

/**
 * Problem: Longest Repeating Character Replacement
 * Description: Given a string s and an integer k, find the length of the longest
 * substring containing the same letter after replacing at most k characters.
 */
public class LongestRepeatingReplacement {

    /**
     * Finds the length of the longest substring with same letters after k replacements.
     * * @param s The input string of uppercase English letters.
     * @param k The maximum number of replacements allowed.
     * @return The length of the longest valid substring.
     */
    public int characterReplacement(String s, int k) {
        int n=s.length(),i=0,j=0,max=0,maxFreq=0;
        int[]freq=new int[26];
        s=s.toUpperCase();
        while(j<n){
            char ch=s.charAt(j);
            int idx=(int)ch-'A';
            freq[idx]++;
            maxFreq=Math.max(maxFreq,freq[idx]);
            if(j-i+1-maxFreq>k){
                char rem=s.charAt(i);
                int remIdx=(int)rem-'A';
                freq[remIdx]--;
                i++;
            }
            if(j-i+1-maxFreq<=k){
                max=Math.max(max,j-i+1);
            }
            j++;
        }
        return max;
    }

    public static void main(String[] args) {
        LongestRepeatingReplacement solver = new LongestRepeatingReplacement();

        // Test Case Data: {String s, int k, int expectedOutput}
        Object[][] testCases = {
                {"ABAB", 2, 4},              // Standard: can replace all to one char
                {"AABABBA", 1, 4},           // Standard: optimal replacement in middle
                {"AAAA", 0, 4},              // Edge: no replacements allowed, all same
                {"ABCD", 1, 2},              // Edge: all different, k=1
                {"ABCD", 2, 3},              // Edge: all different, k=2
                {"BAAAAB", 0, 4},            // Edge: k=0, long sequence in middle
                {"AABABBA", 2, 5},           // Case: more k allows longer sequence
                {"ABAA", 0, 2},              // Case: end of string sequence
                {"ABCDE", 5, 5},             // Edge: k equals length
                {"XYYX", 2, 4},              // Standard: symmetric replacement
                {"AAABBCDDDEE", 2, 5},       // Hard: multiple potential candidates for max frequency
                {"QWERTYUIOP", 0, 1},
                {"AAABBCDDDDDDED", 2, 9}
        };

        int passed = 0;
        int failed = 0;

        System.out.println("Running Tests for: LongestRepeatingReplacement\n");
        System.out.printf("%-20s | %-3s | %-10s | %-10s | %-10s%n", "Input String", "k", "Expected", "Actual", "Result");
        System.out.println("-------------------------------------------------------------------------------");

        for (Object[] testCase : testCases) {
            String s = (String) testCase[0];
            int k = (int) testCase[1];
            int expected = (int) testCase[2];
            int actual = solver.characterReplacement(s, k);

            String status = (actual == expected) ? "PASSED" : "FAILED";
            if (status.equals("PASSED")) passed++; else failed++;

            String displayInput = s.length() > 15 ? s.substring(0, 12) + "..." : s;

            System.out.printf("%-20s | %-3d | %-10d | %-10d | %-10s%n", "\"" + displayInput + "\"", k, expected, actual, status);
        }

        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("Total Tests: %d | Passed: %d | Failed: %d%n", testCases.length, passed, failed);
    }
}