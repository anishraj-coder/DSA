package hashmap;

import java.util.*;

public class PalindromePermutation {

    /**
     * Problem: Palindrome Permutation
     * Check if any permutation of the string 's' can form a palindrome.
     * Constraints: 1 <= s.length <= 10^5, lowercase English alphabets.
     */
    public boolean canPermutePalindrome(String s) {
        int n=s.length();
        if(n<=1)return true;
        HashMap<Character,Integer>map=new HashMap<>();
        for(char ch:s.toCharArray())map.put(ch,map.getOrDefault(ch,0)+1);
        int odd=0;
        for(char ch:map.keySet()){
            int count=map.get(ch);
            if(count%2!=0)odd++;
        }
        return odd<=1;
    }

    public static void main(String[] args) {
        PalindromePermutation solver = new PalindromePermutation();

        // Test Cases: {String input, boolean expected}
        Object[][] testCases = {
                {"carrace", true},   // "racecar"
                {"ferel", false},
                {"aab", true},       // "aba"
                {"code", false},
                // Edge Case: Single character
                {"a", true},
                // Edge Case: All identical characters
                {"aaaaa", true},
                // Edge Case: Two of each character (Even length)
                {"aabbcc", true},
                // Edge Case: Two of each plus two extra different (Should be false)
                {"aabbcd", false},
                // Hard Case: Large string with all even counts
                {"abcdefghijabcdefghij", true},
                // Hard Case: Large string with one odd count
                {"abcdefghijabcdefghijk", true},
                // Edge Case: String with two odd counts (Should be false)
                {"aabbcde", false}
        };

        int passedCount = 0;
        for (int i = 0; i < testCases.length; i++) {
            String s = (String) testCases[i][0];
            boolean expected = (boolean) testCases[i][1];

            boolean actual = solver.canPermutePalindrome(s);

            boolean isPassed = (actual == expected);
            if (isPassed) passedCount++;

            System.out.printf("Test Case %d: s = \"%s\"%n", i + 1, s);
            System.out.printf("Expected: %b | Actual: %b%n", expected, actual);
            System.out.println("Result: " + (isPassed ? "✅ PASSED" : "❌ FAILED"));
            System.out.println("--------------------------------------------------");
        }

        System.out.printf("Final Score: %d/%d cases passed.%n", passedCount, testCases.length);
    }
}