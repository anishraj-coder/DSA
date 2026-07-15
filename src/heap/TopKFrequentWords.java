package heap;

import java.util.*;

public class TopKFrequentWords {

    /**
     * TO DO: Implement this method to return the k most frequent strings.
     * Return the answer sorted by the frequency from highest to lowest.
     * Sort the words with the same frequency by their lexicographical order.
     */
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String,Integer>hm=new HashMap<>();
        for(String a:words)hm.put(a,hm.getOrDefault(a,0)+1);

        PriorityQueue<Pair>pq=new PriorityQueue<>((a,b)->{
            if(a.freq!=b.freq)return a.freq-b.freq;
            else return  b.word.compareTo(a.word);
        });


        for(String s: hm.keySet()){
            int freq=hm.get(s);
            pq.offer(new Pair(s,freq));
            if(pq.size()>k)pq.poll();
        }

        List<String>ans=new ArrayList<>();
        for(int i=0;i<k;i++)ans.add(0,pq.poll().word);
        return ans;
    }

    static class Pair{
        int freq;
        String word;
        Pair(String word,int freq){
            this.word=word;
            this.freq=freq;
        }
    }

    public static void main(String[] args) {
        TopKFrequentWords program = new TopKFrequentWords();

        // Define Test Cases
        TestCase[] testCases = new TestCase[] {
                // 1. Standard example case (lexicographical sorting tie)
                new TestCase(
                        new String[]{"i", "love", "leetcode", "i", "love", "coding"},
                        2,
                        Arrays.asList("i", "love")
                ),

                // 2. Clear strictly decreasing frequencies
                new TestCase(
                        new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"},
                        4,
                        Arrays.asList("the", "is", "sunny", "day")
                ),

                // 3. Single word element (Edge case)
                new TestCase(
                        new String[]{"abc"},
                        1,
                        Arrays.asList("abc")
                ),

                // 4. All words unique, entirely decided by lexicographical order
                new TestCase(
                        new String[]{"d", "c", "b", "a"},
                        2,
                        Arrays.asList("a", "b")
                ),

                // 5. All words identical
                new TestCase(
                        new String[]{"same", "same", "same", "same"},
                        1,
                        Arrays.asList("same")
                ),

                // 6. Tie-breaker with varying string lengths
                new TestCase(
                        new String[]{"apple", "app", "apple", "app", "banana"},
                        2,
                        Arrays.asList("app", "apple")
                ),

                // 7. k equals total number of unique elements
                new TestCase(
                        new String[]{"b", "a", "c"},
                        3,
                        Arrays.asList("a", "b", "c")
                ),

                // 8. Longest constraint word strings
                new TestCase(
                        new String[]{"abcdefghij", "abcdefghij", "xyz", "xyz", "aaaaa"},
                        2,
                        Arrays.asList("abcdefghij", "xyz")
                ),

                // 9. Interleaved frequencies testing steady state tracking
                new TestCase(
                        new String[]{"b", "a", "b", "c", "a", "d", "b"},
                        2,
                        Arrays.asList("b", "a")
                ),

                // 10. Complex mixture of multiple identical frequencies (3 words occur twice, 3 words occur once)
                new TestCase(
                        new String[]{"cc", "bb", "aa", "cc", "bb", "aa", "z", "y", "x"},
                        4,
                        Arrays.asList("aa", "bb", "cc", "x")
                )
        };

        int passed = 0;
        System.out.println("====== RUNNING TESTS ======");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];

            // Clone strings array to prevent side effects
            List<String> actual = program.topKFrequent(tc.words.clone(), tc.k);

            if (compareOutputs(actual, tc.expected)) {
                System.out.printf("Test Case %d: PASSED\n", i + 1);
                passed++;
            } else {
                System.out.printf("Test Case %d: FAILED\n", i + 1);
                System.out.printf("   Input   : words = %s, k = %d\n", Arrays.toString(tc.words), tc.k);
                System.out.printf("   Expected: %s\n", tc.expected);
                System.out.printf("   Actual  : %s\n", actual);
            }
        }

        System.out.println("===========================");
        System.out.printf("Result: %d/%d Passed.\n", passed, testCases.length);
    }

    // Strict sequential order comparison helper
    private static boolean compareOutputs(List<String> actual, List<String> expected) {
        if (actual == null || expected == null) return false;
        return actual.equals(expected);
    }

    // Class to wrap test case metadata
    private static class TestCase {
        String[] words;
        int k;
        List<String> expected;

        TestCase(String[] words, int k, List<String> expected) {
            this.words = words;
            this.k = k;
            this.expected = expected;
        }
    }
}