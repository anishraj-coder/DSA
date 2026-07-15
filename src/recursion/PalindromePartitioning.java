package recursion;

import java.util.*;

/**
 * Problem: Palindrome Partitioning
 * Goal: Partition a string such that every substring in the partition is a palindrome.
 */
public class PalindromePartitioning {

    /**
     * Implement your solution here.
     *
     * @param s The input string containing only lowercase English letters.
     * @return A list of all possible palindrome partitionings.
     */
    public List<List<String>> partition(String s) {
        List<List<String>>ans=new ArrayList<>();
        helper(s,0,new ArrayList<>(),ans);
        return ans;
    }

    private  void helper(String s,int idx,List<String>curr,List<List<String>>ans){
        int n=s.length();
        if(idx==n){
            ans.add(new ArrayList<>(curr));
            return ;
        }

        for(int i=idx;i<n;i++){
            String substring = s.substring(idx, i + 1);
            if(!isPalindrome(substring))continue;
            curr.add(substring);
            helper(s,i+1,curr,ans);
            curr.removeLast();
        }
    }

    private boolean isPalindrome(String s){
        int i=0,j=s.length()-1;
        while(i<j){
            if(s.charAt(i)!=s.charAt(j))return false;
            i++;j--;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartitioning solver = new PalindromePartitioning();

        // Test Cases Definition
        Object[][] testCases = {
                {"aab", Arrays.asList(Arrays.asList("a", "a", "b"), Arrays.asList("aa", "b"))},
                {"a", Arrays.asList(Arrays.asList("a"))},
                {"ab", Arrays.asList(Arrays.asList("a", "b"))},
                {"efe", Arrays.asList(Arrays.asList("e", "f", "e"), Arrays.asList("efe"))},
                {"aabb", Arrays.asList(
                        Arrays.asList("a", "a", "b", "b"),
                        Arrays.asList("a", "a", "bb"),
                        Arrays.asList("aa", "b", "b"),
                        Arrays.asList("aa", "bb")
                )},
                {"racecar", Arrays.asList(
                        Arrays.asList("r", "a", "c", "e", "c", "a", "r"),
                        Arrays.asList("r", "a", "cec", "a", "r"),
                        Arrays.asList("r", "aceca", "r"),
                        Arrays.asList("racecar")
                )},
                {"bb", Arrays.asList(Arrays.asList("b", "b"), Arrays.asList("bb"))},
                {"aba", Arrays.asList(Arrays.asList("a", "b", "a"), Arrays.asList("aba"))},
                {"aaaa", Arrays.asList(
                        Arrays.asList("a", "a", "a", "a"),
                        Arrays.asList("a", "a", "aa"),
                        Arrays.asList("a", "aa", "a"),
                        Arrays.asList("aa", "a", "a"),
                        Arrays.asList("aa", "aa"),
                        Arrays.asList("a", "aaa"),
                        Arrays.asList("aaa", "a"),
                        Arrays.asList("aaaa")
                )},
                {"abc", Arrays.asList(Arrays.asList("a", "b", "c"))}
        };

        int passed = 0;
        System.out.println("--- Starting Palindrome Partitioning Tests ---\n");

        for (int i = 0; i < testCases.length; i++) {
            String input = (String) testCases[i][0];
            List<List<String>> expected = (List<List<String>>) testCases[i][1];

            List<List<String>> actual = solver.partition(input);

            // Sort lists for comparison as the order of partitions doesn't usually matter
            boolean isCorrect = compareResults(actual, expected);

            System.out.println("Test Case " + (i + 1) + ": Input = \"" + input + "\"");
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);

            if (isCorrect) {
                System.out.println("Result:   PASSED");
                passed++;
            } else {
                System.out.println("Result:   FAILED");
            }
            System.out.println("----------------------------------------------");
        }

        System.out.println("\nFinal Results: " + passed + "/" + testCases.length + " tests passed.");
    }

    /**
     * Helper method to compare two nested lists regardless of the order of the outer list.
     */
    private static boolean compareResults(List<List<String>> actual, List<List<String>> expected) {
        if (actual == null || actual.size() != expected.size()) return false;

        // Convert to Set of Sets (or sorted string representations) for order-independent comparison
        Set<String> actualSet = new HashSet<>();
        for (List<String> list : actual) actualSet.add(list.toString());

        for (List<String> list : expected) {
            if (!actualSet.contains(list.toString())) return false;
        }
        return true;
    }
}