package hashmap;

import java.util.*;

public class ShortestCompletingWord {

    /**
     * Finds the shortest completing word in the words array.
     *
     * @param str The string containing characters to match.
     * @param words        The array of candidate words.
     * @return The shortest completing word.
     */
    public String shortestCompletingWord(String str, String[] words) {
        int n=str.length();
        int[]sFreq=new int[26];
        int length=0;
        for(char ch: str.toCharArray()){
            if(Character.isDigit(ch)||Character.isSpaceChar(ch))continue;
            ch=Character.toLowerCase(ch);
            length++;
            sFreq[ch-'a']++;
        }
        String ans="1";
        for(String s:words){
            int match=0;

            int[]temp=new int[26];
            for(char ch: s.toCharArray()){
                ch=Character.toLowerCase(ch);
                temp[ch-'a']++;
                if((temp[ch-'a']>0&&sFreq[ch-'a']>0)&&(temp[ch-'a']<=sFreq[ch-'a']))match++;
            }
            if(match==length){
                if("1".equals(ans)||ans.length()>s.length()){
                    ans=s;
                }
            }
        }
        return "1".equals(ans)?"":ans;
    }

    public static void main(String[] args) {
        ShortestCompletingWord solution = new ShortestCompletingWord();

        Object[][] testCases = {
                // Case 1: Standard case from example
                {"1s3 PSt", new String[]{"step", "steps", "stripe", "stepple"}, "steps"},

                // Case 2: Multiple shortest words - return the first occurring
                {"1s3 456", new String[]{"looks", "pest", "stew", "show"}, "pest"},

                // Case 3: Mixed casing and digits
                {"Ah71752", new String[]{"suggest", "letter", "of", "husband", "easy", "education", "drug", "prevent", "writer", "old"}, "husband"},

                // Case 4: Plate with no letters (all words are candidates)
                {"123 456", new String[]{"apple", "pie", "ox"}, "ox"},

                // Case 5: Duplicate characters in license plate (must have at least 3 'a's)
                {"Aaa 123", new String[]{"apple", "banana", "aardvark", "alaska"}, "banana"},

                // Case 6: Exact match (word is exactly the plate's letters)
                {"rO1234", new String[]{"road", "or", "roar"}, "or"},

                // Case 7: High frequency of a single letter
                {"s s s s", new String[]{"stress", "assess", "shush", "successes"}, "assess"},

                // Case 8: All words same length, target is at the end
                {"iS21abc", new String[]{"isab", "abcis", "isabc"}, "isabc"},

                // Case 9: Plate has characters that none of the early words have
                {"P2 t", new String[]{"apple", "pear", "temp", "top"}, "temp"},

                // Case 10: Long words vs short words
                {"Og32248", new String[]{"enough", "these", "play", "wide", "relative"}, "enough"},

                // Case 11: Single letter plate, multiple matches
                {"a", new String[]{"cat", "dog", "ant", "bat"}, "ant"}
        };

        int passed = 0;
        System.out.println(String.format("%-20s | %-15s | %-15s | %-10s", "License Plate", "Expected", "Actual", "Result"));
        System.out.println("---------------------------------------------------------------------------------------");

        for (Object[] test : testCases) {
            String plate = (String) test[0];
            String[] words = (String[]) test[1];
            String expected = (String) test[2];

            String actual = solution.shortestCompletingWord(plate, words);
            boolean isMatch = expected.equals(actual);

            if (isMatch) passed++;

            System.out.println(String.format("%-20s | %-15s | %-15s | %-10s",
                    plate, expected, actual, isMatch ? "PASSED" : "FAILED"));
        }

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Total Tests: " + testCases.length + " | Passed: " + passed + " | Failed: " + (testCases.length - passed));
    }
}