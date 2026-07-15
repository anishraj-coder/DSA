package twoPointer;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Problem: Permutation in String
 * Constraints: 1 <= s1.length, s2.length <= 10^4 (Lowercase English letters)
 */
public class PermutationSubstring {

    /**
     * Determines if s2 contains a permutation of s1.
     * @param p The string whose permutation we are looking for.
     * @param s The string to search within.
     * @return true if a permutation of s1 exists as a substring in s2, false otherwise.
     */
    public boolean checkInclusion(String p, String s) {
        int m=p.length(),n=s.length();
        if(n<m)return false;
        HashMap<Character,Integer>pMap=new HashMap<>();
        HashMap<Character,Integer>sMap=new HashMap<>();
        for(int i=0;i<m;i++){
            pMap.put(p.charAt(i),pMap.getOrDefault(p.charAt(i),0)+1);
        }
        int match=0;
        for(int i=0;i<m;i++){
            sMap.put(s.charAt(i),sMap.getOrDefault(s.charAt(i),0)+1);
            if(sMap.get(s.charAt(i))<=pMap.getOrDefault(s.charAt(i),0))match++;
        }
        if(match==m)return true;
        int i=0,j=m;
        while(j<n){
            char add=s.charAt(j);
            char rem=s.charAt(i);
            sMap.put(add,sMap.getOrDefault(add,0)+1);
            if(sMap.get(add)<=pMap.getOrDefault(add,0))match++;
            if(sMap.get(rem)<=pMap.getOrDefault(rem,0))match--;
            sMap.put(rem,sMap.get(rem)-1);
            if(sMap.get(rem)==0)sMap.remove(rem);
            if(match==m)return true;
            i++;
            j++;
        }
        return false;
    }

    public static void main(String[] args) {
        PermutationSubstring solver = new PermutationSubstring();

        Object[][] testCases = {
                {"ab", "eidbaooo", true},           // Basic case: permutation at the middle
                {"ab", "eidboaoo", false},          // Basic case: letters present but not together
                {"adc", "dcda", true},              // Overlapping letters but valid permutation
                {"hello", "ooolleoooleh", false},   // Character counts don't match for any window
                {"a", "ab", true},                  // Single character s1, present
                {"a", "b", false},                  // Single character s1, absent
                {"abc", "bbbca", true},             // Permutation at the end
                {"abcdxabcde", "abcdeabcdx", true}, // Longer strings with minor differences
                {"prosperity", "properties", false},// Same length, nearly same chars, but different counts
                {"xyz", "zyx", true},               // Exact match (permutation is the whole string)
                {"pi", "p", false},                 // s1 longer than s2
                {"abc", "ccccbbbbaaaa", false}      // Large s2, but no window matches counts
        };

        int passed = 0;
        System.out.println(String.format("%-5s | %-15s | %-15s | %-10s | %-10s | %-8s",
                "No.", "s1", "s2", "Expected", "Actual", "Result"));
        System.out.println("-".repeat(80));

        for (int i = 0; i < testCases.length; i++) {
            String s1 = (String) testCases[i][0];
            String s2 = (String) testCases[i][1];
            boolean expected = (boolean) testCases[i][2];
            boolean actual = solver.checkInclusion(s1, s2);

            boolean isCorrect = (actual == expected);
            if (isCorrect) passed++;

            System.out.println(String.format("%-5d | %-15s | %-15s | %-10b | %-10b | %-8s",
                    i + 1,
                    truncate(s1, 12),
                    truncate(s2, 12),
                    expected,
                    actual,
                    isCorrect ? "PASS" : "FAIL"));
        }

        System.out.println("-".repeat(80));
        System.out.println("Final Score: " + passed + "/" + testCases.length);
    }

    private static String truncate(String str, int len) {
        if (str.length() <= len) return str;
        return str.substring(0, len - 3) + "...";
    }
}