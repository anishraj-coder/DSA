package arrays;

import java.util.*;

/**
 * Problem: 4Sum
 * Find all unique quadruplets in the array that sum up to the target.
 */
public class FourSum {

    /**
     * Finds all unique quadruplets [nums[a], nums[b], nums[c], nums[d]]
     * such that their sum equals the target.
     * * @param arr   An array of n integers
     * @param target The integer sum to reach
     * @return A list of unique quadruplets
     */
    public List<List<Integer>> fourSum(int[] arr, int target) {
        int n=arr.length;
        if(n<4)return new ArrayList<>();
        Arrays.sort(arr);
        List<List<Integer>>ans=new ArrayList<>();
        for(int i=0;i<n;i++){
            if(i>0&&arr[i]==arr[i-1])continue;
            for(int j=i+1;j<n;j++){
                if(j>i+1&&arr[j]==arr[j-1])continue;
                int k=j+1,l=n-1;
                while(k<l){
                    long sum=(long)arr[i]+arr[j]+arr[k]+arr[l];
                    if(sum==target){
                        ans.add(Arrays.asList(arr[i],arr[j],arr[k],arr[l]));
                        k++;l--;
                        while(k<l&&arr[k]==arr[k-1])k++;
                        while(k<l&&arr[l]==arr[l+1])l--;
                    }else if(sum<target)k++;
                    else l--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        FourSum solver = new FourSum();
        int testsPassed = 0;

        // Define Test Cases
        TestCase[] testCases = {
                new TestCase(new int[]{1, 0, -1, 0, -2, 2}, 0,
                        Arrays.asList(Arrays.asList(-2, -1, 1, 2), Arrays.asList(-2, 0, 0, 2), Arrays.asList(-1, 0, 0, 1))),

                new TestCase(new int[]{2, 2, 2, 2, 2}, 8,
                        Arrays.asList(Arrays.asList(2, 2, 2, 2))),

                new TestCase(new int[]{}, 0,
                        new ArrayList<>()), // Edge case: Empty array

                new TestCase(new int[]{1, 2, 3}, 6,
                        new ArrayList<>()), // Edge case: Less than 4 elements

                new TestCase(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, 0,
                        Arrays.asList(
                                Arrays.asList(-3, -2, 2, 3), Arrays.asList(-3, -1, 1, 3), Arrays.asList(-3, 0, 0, 3),
                                Arrays.asList(-3, 0, 1, 2), Arrays.asList(-2, -1, 0, 3), Arrays.asList(-2, -1, 1, 2),
                                Arrays.asList(-2, 0, 0, 2), Arrays.asList(-1, 0, 0, 1)
                        )),

                new TestCase(new int[]{-5, -5, -5, -5, -5}, -20,
                        Arrays.asList(Arrays.asList(-5, -5, -5, -5))), // All identical negatives

                new TestCase(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296,
                        new ArrayList<>()), // Edge case: Integer overflow check (sum exceeds 32-bit int)

                new TestCase(new int[]{-1, -5, -5, -3, 2, 5, 0, 4}, -7,
                        Arrays.asList(Arrays.asList(-5, -5, -1, 4), Arrays.asList(-5, -5, 0, 3), Arrays.asList(-5, -3, -1, 2))),

                new TestCase(new int[]{0, 0, 0, 0}, 0,
                        Arrays.asList(Arrays.asList(0, 0, 0, 0))), // Zero sum

                new TestCase(new int[]{1, -2, -5, -4, -3, 3, 3, 5}, -11,
                        Arrays.asList(Arrays.asList(-5, -4, -3, 1), Arrays.asList(-5, -4, -2, 0) /* Adjusting if 0 was present, but let's stick to valid calculation */))
                // Correction for Case 10: Let's use a verified set:
        };

        // Re-defining Case 10 for absolute accuracy
        testCases[9] = new TestCase(new int[]{1, 1, 1, 1, 1, 1}, 4, Arrays.asList(Arrays.asList(1, 1, 1, 1)));

        // Run Tests
        for (int i = 0; i < testCases.length; i++) {
            List<List<Integer>> actual = solver.fourSum(testCases[i].nums, testCases[i].target);

            // Sort inner and outer lists for comparison
            sortResults(actual);
            sortResults(testCases[i].expected);

            boolean passed = actual.equals(testCases[i].expected);
            if (passed) testsPassed++;

            System.out.println("Test Case " + (i + 1) + ": " + (passed ? "PASSED" : "FAILED"));
            System.out.println("   Input: nums=" + Arrays.toString(testCases[i].nums) + ", target=" + testCases[i].target);
            System.out.println("   Expected: " + testCases[i].expected);
            System.out.println("   Actual:   " + actual);
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Total Tests Passed: " + testsPassed + "/" + testCases.length);
    }

    private static void sortResults(List<List<Integer>> list) {
        for (List<Integer> inner : list) {
            Collections.sort(inner);
        }
        list.sort((a, b) -> {
            for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
                if (!a.get(i).equals(b.get(i))) return a.get(i) - b.get(i);
            }
            return a.size() - b.size();
        });
    }

    static class TestCase {
        int[] nums;
        int target;
        List<List<Integer>> expected;

        TestCase(int[] nums, int target, List<List<Integer>> expected) {
            this.nums = nums;
            this.target = target;
            this.expected = expected;
        }
    }
}
