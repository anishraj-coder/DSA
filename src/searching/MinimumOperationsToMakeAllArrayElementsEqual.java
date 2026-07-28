package searching;

import java.util.*;

public class MinimumOperationsToMakeAllArrayElementsEqual {

    /**
     * Complete the method below.
     * 
     * Return a List<Long> containing the minimum operations for each query.
     */
    public List<Long> minOperations(int[] arr, int[] queries) {
        int n=arr.length,q=queries.length;
        Arrays.sort(arr);
        Long[]ans=new Long[q];
        int[]pre=new int[n+1];
        for(int i=0;i<n;i++)pre[i+1]=arr[i]+pre[i];
        for(int i=0;i<q;i++) {
        	int target=queries[i],hi=n,low=0;
        	long total=0l;
        	while(low<hi) {
        		int mid=low+(hi-low)/2;
        		if(arr[mid]>=target) {
        			hi=mid;
        		}else low=mid+1;
        	}
        	int idx=low;
        	total+=(long)target*idx-pre[idx];
        	total+=(long)(pre[n]-pre[idx])-target*(n-idx);
        	ans[i]=total;
        }
        
        return Arrays.asList(ans);
    }

    // =========================================================================
    // TEST HARNESS & DRIVER CODE (DO NOT MODIFY BELOW THIS LINE)
    // =========================================================================
    public static void main(String[] args) {
        MinimumOperationsToMakeAllArrayElementsEqual solver = new MinimumOperationsToMakeAllArrayElementsEqual();

        TestCase[] testCases = new TestCase[] {
            // Test 1: Example 1 from problem description
            new TestCase(
                new int[]{3, 1, 6, 8},
                new int[]{1, 5},
                Arrays.asList(14L, 10L),
                "Example 1"
            ),
            // Test 2: Example 2 from problem description
            new TestCase(
                new int[]{2, 9, 6, 3},
                new int[]{10},
                Arrays.asList(20L),
                "Example 2"
            ),
            // Test 3: Query smaller than all elements in nums
            new TestCase(
                new int[]{5, 10, 15},
                new int[]{2},
                Arrays.asList(24L),
                "Query < min(nums)"
            ),
            // Test 4: Query larger than all elements in nums
            new TestCase(
                new int[]{5, 10, 15},
                new int[]{20},
                Arrays.asList(30L),
                "Query > max(nums)"
            ),
            // Test 5: Single element array
            new TestCase(
                new int[]{42},
                new int[]{10, 42, 100},
                Arrays.asList(32L, 0L, 58L),
                "Single element array"
            ),
            // Test 6: Array with duplicate elements
            new TestCase(
                new int[]{4, 4, 4, 4},
                new int[]{2, 4, 6},
                Arrays.asList(8L, 0L, 8L),
                "Array with identical elements"
            ),
            // Test 7: Query matches an exact element in the middle
            new TestCase(
                new int[]{1, 3, 5, 7, 9},
                new int[]{5},
                Arrays.asList(8L),
                "Query matches middle element"
            ),
            // Test 8: Large inputs testing 64-bit integer overflow (long requirements)
            new TestCase(
                new int[]{1000000000, 1000000000, 1},
                new int[]{1000000000, 1},
                Arrays.asList(999999999L, 1999999998L),
                "Large 10^9 values (Long overflow check)"
            ),
            // Test 9: Unsorted nums with duplicate queries
            new TestCase(
                new int[]{10, 2, 8, 4, 6},
                new int[]{5, 5, 1},
                Arrays.asList(9L, 9L, 25L),
                "Unsorted array with duplicate queries"
            ),
            // Test 10: Query value falls between two inner elements
            new TestCase(
                new int[]{10, 20, 30, 40},
                new int[]{25},
                Arrays.asList(40L),
                "Query value in between inner elements"
            )
        };

        int passedCount = 0;

        System.out.println("==========================================================================================================");
        System.out.printf("%-11s | %-20s | %-20s | %-8s | %s%n", "Test Case", "Expected", "Actual", "Status", "Details");
        System.out.println("==========================================================================================================");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            
            // Execute student's code
            List<Long> actual = solver.minOperations(tc.nums, tc.queries);
            boolean passed = tc.expected.equals(actual);
            
            if (passed) {
                passedCount++;
            }

            String expectedStr = tc.expected != null ? tc.expected.toString() : "null";
            String actualStr = actual != null ? actual.toString() : "null";
            String statusStr = passed ? "PASSED" : "FAILED";

            System.out.printf("%-11d | %-20s | %-20s | %-8s | %s%n",
                    (i + 1),
                    truncate(expectedStr, 20),
                    truncate(actualStr, 20),
                    statusStr,
                    tc.details);
        }

        System.out.println("==========================================================================================================");
        System.out.printf("SUMMARY: Passed %d / %d test cases. (Failed: %d)%n", 
                passedCount, testCases.length, (testCases.length - passedCount));
        System.out.println("==========================================================================================================");
    }

    private static String truncate(String str, int maxLen) {
        if (str.length() <= maxLen) return str;
        return str.substring(0, maxLen - 3) + "...";
    }

    private static class TestCase {
        int[] nums;
        int[] queries;
        List<Long> expected;
        String details;

        TestCase(int[] nums, int[] queries, List<Long> expected, String details) {
            this.nums = nums;
            this.queries = queries;
            this.expected = expected;
            this.details = details;
        }
    }
}