package hashmap;

import java.util.*;

public class AnagramIndices {

    /**
     * Problem: Find All Anagrams in a String
     * Given two strings s and p, return an array of all the start indices
     * of p's anagrams in s.
     */
    public List<Integer> findAnagrams(String s, String p) {
        int m=p.length(),n=s.length();
        if(m>n)return new ArrayList<>();
        HashMap<Character,Integer> pMap =new HashMap<>();
        for(char ch:p.toCharArray()){
            pMap.put(ch, pMap.getOrDefault(ch,0)+1);
        }
        int match=0;
        List<Integer>ans=new ArrayList<>();
        HashMap<Character,Integer>sMap=new HashMap<>();
        for(int i=0;i<m;i++){
            char ch=s.charAt(i);
            sMap.put(ch,sMap.getOrDefault(ch,0)+1);
            if(sMap.get(ch)<=pMap.getOrDefault(ch,0))match++;
        }
        if(match==m)ans.add(0);
        int j=m,i=0;
        while(j<n){
            char rem=s.charAt(i);
            char add=s.charAt(j);
            if(sMap.get(rem)<=pMap.getOrDefault(rem,0))match--;
            sMap.put(rem,sMap.get(rem)-1);
            if(sMap.get(rem)==0)sMap.remove(rem);
            sMap.put(add,sMap.getOrDefault(add,0)+1);
            if(sMap.get(add)<=pMap.getOrDefault(add,0))match++;
            j++;i++;
            if(match==m)ans.add(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        AnagramIndices solver = new AnagramIndices();

        // Test Cases: {String s, String p, List<Integer> expected}
        Object[][] testCases = {
                {"cbaebabacd", "abc", Arrays.asList(0, 6)},
                {"abab", "ab", Arrays.asList(0, 1, 2)},
                // Edge Case: p is longer than s
                {"abc", "abcd", Collections.emptyList()},
                // Edge Case: s and p are identical
                {"anagram", "anagram", Arrays.asList(0)},
                // Edge Case: s contains multiple overlapping anagrams
                {"aaaaa", "aa", Arrays.asList(0, 1, 2, 3)},
                // Edge Case: s and p are single but different characters
                {"a", "b", Collections.emptyList()},
                // Edge Case: p is length 1
                {"afgaw", "a", Arrays.asList(0, 3)},
                // Hard Case: p consists of all unique characters, s is long
                {"bcdefghia", "abcdefghi", Collections.emptyList()},
                // Hard Case: Large input simulation (not size, but variety)
                {"zzzyyyzzz", "zyz", Arrays.asList(0, 1, 2, 4, 5, 6)},
                // Edge Case: p has all same characters
                {"aaaaaaaaaa", "aaa", Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7)}
        };

        int passedCount = 0;
        for (int i = 0; i < testCases.length; i++) {
            String s = (String) testCases[i][0];
            String p = (String) testCases[i][1];
            List<Integer> expected = (List<Integer>) testCases[i][2];

            List<Integer> actual = solver.findAnagrams(s, p);

            // Sort lists to ensure comparison is fair regardless of return order
            Collections.sort(actual);
            List<Integer> sortedExpected = new ArrayList<>(expected);
            Collections.sort(sortedExpected);

            boolean isPassed = actual.equals(sortedExpected);
            if (isPassed) passedCount++;

            System.out.printf("Test Case %d: s=\"%s\", p=\"%s\"%n", i + 1, s, p);
            System.out.printf("Expected: %s | Actual: %s%n", sortedExpected, actual);
            System.out.println("Result: " + (isPassed ? "✅ PASSED" : "❌ FAILED"));
            System.out.println("--------------------------------------------------");
        }

        System.out.printf("Final Score: %d/%d cases passed.%n", passedCount, testCases.length);
    }
}