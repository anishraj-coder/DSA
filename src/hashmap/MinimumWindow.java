package hashmap;

import java.util.*;

public class MinimumWindow {

    /**
     * Problem: Minimum Window Substring
     * Return the minimum window substring of s such that every character
     * in t (including duplicates) is included in the window.
     */
    public String minWindow(String s, String t) {
        int m=t.length(),n=s.length();
        if(n<m||m==0)return "";
        HashMap<Character,Integer>tMap,sMap;
        tMap=new HashMap<>();
        sMap=new HashMap<>();
        for(char ch:t.toCharArray())tMap.put(ch,tMap.getOrDefault(ch,0)+1);
        int ansLen=Integer.MAX_VALUE,sp=0,ep=0,ansSp=0,match=0;
        while(ep<n){
            if(match==m){
                if(ep-sp+1<ansLen){
                    ansSp=sp;
                    ansLen=ep-sp+1;
                }
                char rem=s.charAt(sp);
                if(sMap.get(rem)<=tMap.getOrDefault(rem,0))match--;
                sMap.put(rem,sMap.get(rem)-1);
                if(sMap.get(rem)==0)sMap.remove(rem);
                sp++;
            }else{
                char ch=s.charAt(ep);
                sMap.put(ch,sMap.getOrDefault(ch,0)+1);
                if(sMap.get(ch)<=tMap.getOrDefault(ch,0))match++;
                ep++;
            }
        }
        return s.substring(ansSp,ansLen);
    }

    public static void main(String[] args) {
        MinimumWindow solver = new MinimumWindow();

        // Test Cases: {String s, String t, String expected}
        Object[][] testCases = {
                {"ADOBECODEBANC", "ABC", "BANC"},
                {"a", "a", "a"},
                {"a", "aa", ""},
                // Edge Case: t is longer than s
                {"abc", "abcd", ""},
                // Edge Case: s and t are identical
                {"test", "test", "test"},
                // Edge Case: Multiple potential windows, return the smallest
                {"caae", "cae", "cae"},
                // Edge Case: All characters of t at the very end
                {"qwertyabc", "abc", "abc"},
                // Edge Case: All characters of t at the very beginning
                {"abcqwerty", "abc", "abc"},
                // Hard Case: Duplicate characters in t and overlapping in s
                {"aaflslflabbccaaa", "aaa", "aaa"},
                // Hard Case: Characters of t scattered far apart
                {"aebcbda", "abc", "abc"}
        };

        int passedCount = 0;
        for (int i = 0; i < testCases.length; i++) {
            String s = (String) testCases[i][0];
            String t = (String) testCases[i][1];
            String expected = (String) testCases[i][2];

            String actual = solver.minWindow(s, t);

            boolean isPassed = actual.equals(expected);
            if (isPassed) passedCount++;

            System.out.printf("Test Case %d: s=\"%s\", t=\"%s\"%n", i + 1, s, t);
            System.out.printf("Expected: \"%s\" | Actual: \"%s\"%n", expected, actual);
            System.out.println("Result: " + (isPassed ? "✅ PASSED" : "❌ FAILED"));
            System.out.println("--------------------------------------------------");
        }

        System.out.printf("Final Score: %d/%d cases passed.%n", passedCount, testCases.length);
    }
}