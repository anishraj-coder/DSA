package recursion;

import java.util.*;

public class IntegerPermutator {

    /**
     * Returns all possible permutations of the given distinct integers.
     * @param arr an array of distinct integers
     * @return a list of all possible permutations
     */
    public List<List<Integer>> permute(int[] arr) {
        Set<Integer>vis=new HashSet<>();
        List<Integer>curr=new ArrayList<>();
        List<List<Integer>>ans=new ArrayList<>();
        helper(arr,vis,curr,ans);
        return ans;
    }

    private void helper(int[]arr,Set<Integer>vis,List<Integer>curr,List<List<Integer>>ans){
        int n=arr.length;
        if(vis.size()==n){
            ans.add(new ArrayList<>(curr));
            return;
        }

        for(int a:arr){
            if(!vis.contains(a)){
                curr.add(a);
                vis.add(a);
                helper(arr,vis,curr,ans);
                vis.remove(a);
                curr.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        IntegerPermutator permutator = new IntegerPermutator();

        // Map to store Input Array -> Expected Count of Permutations
        // (Storing full lists for smaller cases, counts for larger ones)
        Map<int[], List<List<Integer>>> testCases = new LinkedHashMap<>();

        testCases.put(new int[]{1}, Arrays.asList(Arrays.asList(1)));

        testCases.put(new int[]{0, 1}, Arrays.asList(
                Arrays.asList(0, 1), Arrays.asList(1, 0)
        ));

        testCases.put(new int[]{1, 2, 3}, Arrays.asList(
                Arrays.asList(1, 2, 3), Arrays.asList(1, 3, 2),
                Arrays.asList(2, 1, 3), Arrays.asList(2, 3, 1),
                Arrays.asList(3, 1, 2), Arrays.asList(3, 2, 1)
        ));

        // Case with negative numbers
        testCases.put(new int[]{-1, 0, 1}, Arrays.asList(
                Arrays.asList(-1, 0, 1), Arrays.asList(-1, 1, 0),
                Arrays.asList(0, -1, 1), Arrays.asList(0, 1, -1),
                Arrays.asList(1, -1, 0), Arrays.asList(1, 0, -1)
        ));

        int passed = 0;
        int total = 0;

        for (Map.Entry<int[], List<List<Integer>>> entry : testCases.entrySet()) {
            int[] nums = entry.getKey();
            List<List<Integer>> expected = entry.getValue();
            List<List<Integer>> actual = permutator.permute(nums);

            boolean isMatch = comparePermutations(expected, actual);
            total++;
            if (isMatch) passed++;

            System.out.println("Test Case " + total + ": nums = " + Arrays.toString(nums));
            System.out.println("Expected Size: " + expected.size());
            System.out.println("Actual Size:   " + (actual == null ? 0 : actual.size()));
            System.out.println("Result:        " + (isMatch ? "PASSED ✅" : "FAILED ❌"));
            System.out.println("--------------------------------------------------");
        }

        // Hard Test Cases: Validating by Count and Uniqueness
        int[][] hardCases = {
                {1, 2, 3, 4},       // 4! = 24
                {5, -5, 10, -10},   // 4! = 24
                {1, 2, 3, 4, 5},    // 5! = 120
                {0, 1, 2, 3, 4, 5}  // 6! = 720 (Max Constraint)
        };

        for (int[] nums : hardCases) {
            total++;
            System.out.println("Test Case " + total + ": nums = " + Arrays.toString(nums));
            List<List<Integer>> actual = permutator.permute(nums);
            int expectedSize = factorial(nums.length);

            boolean sizeCorrect = (actual != null && actual.size() == expectedSize);
            boolean uniqueCorrect = sizeCorrect && (new HashSet<>(actual).size() == expectedSize);

            if (sizeCorrect && uniqueCorrect) {
                System.out.println("Result:        PASSED ✅ (Found " + expectedSize + " unique permutations)");
                passed++;
            } else {
                System.out.println("Result:        FAILED ❌ (Size: " + (actual == null ? 0 : actual.size()) + ", Expected: " + expectedSize + ")");
            }
            System.out.println("--------------------------------------------------");
        }

        System.out.println("\nFinal Score: " + passed + "/" + total);
    }

    // Helper to compare two lists of lists regardless of order
    private static boolean comparePermutations(List<List<Integer>> list1, List<List<Integer>> list2) {
        if (list1 == null || list2 == null || list1.size() != list2.size()) return false;
        Set<List<Integer>> set1 = new HashSet<>(list1);
        Set<List<Integer>> set2 = new HashSet<>(list2);
        return set1.equals(set2);
    }

    private static int factorial(int n) {
        int res = 1;
        for (int i = 2; i <= n; i++) res *= i;
        return res;
    }
}