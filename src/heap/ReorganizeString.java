package heap;

import java.util.*;

public class ReorganizeString {

    /**
     * Rearranges the characters of s so that any two adjacent characters are not the same.
     *
     * @param s The input string.
     * @return A rearranged string or "" if not possible.
     */
    public static String reorganize(String s) {
        if(s==null||s.isBlank())return  "";
        int[]map=new int[26];
        for(char ch:s.toCharArray()){
            map[ch-'a']++;
        }
        PriorityQueue<Pair>pq=new PriorityQueue<>((a,b)->{
            if(a.freq!=b.freq)return b.freq-a.freq;
            else return a.ch-b.ch;
        });

        for(int i=0;i<26;i++){
            char ch=(char)('a'+i);
            if(map[i]!=0)pq.offer(new Pair(ch,map[i]));
        }

        StringBuilder sb=new StringBuilder();

        while(!pq.isEmpty()){
            Pair p=pq.poll();
            if(sb.isEmpty()||sb.charAt(sb.length()-1)!=p.ch){
                sb.append(p.ch);
                p.freq--;
                if(p.freq>0)pq.offer(p);
            }else{
                if(pq.isEmpty())return "";
                Pair p2=pq.poll();
                sb.append(p2.ch);
                p2.freq--;
                if(p2.freq>0) pq.offer(p2);
                pq.offer(p);
            }
        }

        return sb.toString();
    }

    static class Pair{
        int freq;
        char ch;
        Pair(char ch,int freq){
            this.ch=ch;
            this.freq=freq;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Standard example
        testCase("aab", "aba", "Standard Example 1");

        // Test Case 2: Impossible case
        testCase("aaab", "", "Impossible Case Example 2");

        // Test Case 3: Single character
        testCase("a", "a", "Single Character");

        // Test Case 4: All distinct characters
        testCase("abcde", "abcde", "All Distinct (Any valid permutation)");
        // Note: For distinct chars, any permutation is valid. We check validity in helper.

        // Test Case 5: Two characters alternating
        testCase("abab", "abab", "Already Valid Alternating");

        // Test Case 6: High frequency of one char but possible
        testCase("aaabb", "ababa", "High Frequency Possible");

        // Test Case 7: High frequency of one char impossible
        testCase("aaaaab", "", "High Frequency Impossible");

        // Test Case 8: Empty string (Edge case, though constraints say length >= 1, good to handle)
        testCase("", "", "Empty String");

        // Test Case 9: Palindrome-like structure requiring reorg
        testCase("aabbcc", "abcabc", "Balanced Frequencies");

        // Test Case 10: Large block of one char at limit
        // Length 500, 'a' appears 250 times, 'b' appears 250 times -> Possible "abab..."
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<250; i++) sb.append('a');
        for(int i=0; i<250; i++) sb.append('b');
        testCase(sb.toString(), "valid", "Max Length Balanced");

        // Test Case 11: One char dominates just enough to fail
        // Length 5, 'a' 3 times, 'b' 1 time, 'c' 1 time. Max freq 3. N=5. (N+1)/2 = 3. Possible.
        testCase("aaabc", "valid", "Boundary Possible");

        // Test Case 12: One char dominates too much
        // Length 4, 'a' 3 times, 'b' 1 time. Max freq 3. N=4. (N+1)/2 = 2.5 -> 3? No.
        // If max count > (n+1)/2, impossible. 3 > 2.5 is true. So impossible.
        testCase("aaab", "", "Boundary Impossible");
    }

    private static void testCase(String input, String expectedOrValid, String testName) {
        System.out.println("Running Test: " + testName);
        System.out.println("Input: \"" + input + "\"");

        String result = reorganize(input);

        boolean passed = false;
        String message = "";

        if (expectedOrValid.equals("")) {
            // We expect an empty string
            passed = result.equals("");
            message = passed ? "Correctly returned empty string" : "Expected empty string but got: \"" + result + "\"";
        } else if (expectedOrValid.equals("valid")) {
            // We need to verify if the result is a valid reorganization
            if (result.length() != input.length()) {
                message = "Length mismatch. Expected " + input.length() + " but got " + result.length();
            } else if (!hasSameChars(input, result)) {
                message = "Characters do not match original string.";
            } else if (hasAdjacentDuplicates(result)) {
                message = "Result has adjacent duplicate characters: \"" + result + "\"";
            } else {
                passed = true;
                message = "Valid reorganization found: \"" + result + "\"";
            }
        } else {
            // Specific expected output (rarely unique, but used for simple cases)
            // Since order can vary, we usually check validity, but for specific examples:
            if (hasSameChars(input, result) && !hasAdjacentDuplicates(result) && result.length() == input.length()) {
                passed = true;
                message = "Valid reorganization found: \"" + result + "\"";
            } else {
                message = "Invalid result: \"" + result + "\"";
            }
        }

        System.out.println("Expected: \"" + expectedOrValid + "\"");
        System.out.println("Actual:   \"" + result + "\"");
        System.out.println("Result:   " + (passed ? "PASSED" : "FAILED"));
        System.out.println("Details:  " + message);
        System.out.println("-------------------------");
    }

    private static boolean hasSameChars(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }

    private static boolean hasAdjacentDuplicates(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                return true;
            }
        }
        return false;
    }
}