package heap;

import java.util.*;

public class KSmallestPairs {

    // Implement your solution here
    public List<List<Integer>> kSmallestPairs(int[] arr1, int[] arr2, int k) {
    	
    	int n=arr1.length,m=arr2.length;
    	if(n==0||m==0||k==0)return new ArrayList<>() ;
    	
    	PriorityQueue<List<Integer>> pq=new PriorityQueue<>((a,b)->
    	-Integer.compare(b.get(0)+b.get(1), a.get(0)+a.get(1)));
    	
    	for(int i=0;i<n&&i<k;i++)pq.offer(new ArrayList<>(Arrays.asList(arr1[i],arr2[0],0)));
    	List<List<Integer>>ans=new ArrayList<>();
    	while(k-->0&&!pq.isEmpty()) {
    		List<Integer>curr=pq.poll();
    		ans.add(new ArrayList<Integer>(Arrays.asList(curr.get(0),curr.get(1))));
    		if(curr.get(2)==m-1) {
    			continue;
    		}
    		
    		pq.offer(new ArrayList<>(Arrays.asList(curr.get(0),arr2[curr.get(2)+1],curr.get(2)+1)));
    	}
    	
    	
    	return ans;
    }

    public static void main(String[] args) {
        KSmallestPairs solver = new KSmallestPairs();

        // Test Case Data Structure
        class TestCase {
            int[] nums1;
            int[] nums2;
            int k;
            List<List<Integer>> expected;

            TestCase(int[] nums1, int[] nums2, int k, int[][] expectedArray) {
                this.nums1 = nums1;
                this.nums2 = nums2;
                this.k = k;
                this.expected = new ArrayList<>();
                for (int[] pair : expectedArray) {
                    this.expected.add(Arrays.asList(pair[0], pair[1]));
                }
            }
        }

        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1: Standard case from example 1
        testCases.add(new TestCase(
            new int[]{1, 7, 11},
            new int[]{2, 4, 6},
            3,
            new int[][]{{1, 2}, {1, 4}, {1, 6}}
        ));

        // Test Case 2: Duplicate elements in input arrays
        testCases.add(new TestCase(
            new int[]{1, 1, 2},
            new int[]{1, 2, 3},
            2,
            new int[][]{{1, 1}, {1, 1}}
        ));

        // Test Case 3: k is larger than possible pairs (k capped at nums1.length * nums2.length)
        testCases.add(new TestCase(
            new int[]{1, 2},
            new int[]{3, 4},
            5,
            new int[][]{{1, 3}, {1, 4}, {2, 3}, {2, 4}}
        ));

        // Test Case 4: Single element arrays
        testCases.add(new TestCase(
            new int[]{5},
            new int[]{10},
            1,
            new int[][]{{5, 10}}
        ));

        // Test Case 5: Negative numbers
        testCases.add(new TestCase(
            new int[]{-10, -4, 0},
            new int[]{-5, 1, 2},
            4,
            new int[][]{{-10, -5}, {-10, 1}, {-10, 2}, {-4, -5}}
        ));

        // Test Case 6: All elements identical across both arrays
        testCases.add(new TestCase(
            new int[]{2, 2, 2},
            new int[]{2, 2, 2},
            3,
            new int[][]{{2, 2}, {2, 2}, {2, 2}}
        ));

        // Test Case 7: One array significantly larger than the other
        testCases.add(new TestCase(
            new int[]{1, 2, 3, 4, 5, 6},
            new int[]{10},
            3,
            new int[][]{{1, 10}, {2, 10}, {3, 10}}
        ));

        // Test Case 8: Interleaved minimal sums with tied sum combinations
        testCases.add(new TestCase(
            new int[]{1, 2, 4, 5, 6},
            new int[]{3, 5, 7, 9},
            3,
            new int[][]{{1, 3}, {2, 3}, {1, 5}}
        ));

        // Test Case 9: Large integer values (testing bounds/overflow safety)
        testCases.add(new TestCase(
            new int[]{-1000000000, 1000000000},
            new int[]{-1000000000, 1000000000},
            2,
            new int[][]{{-1000000000, -1000000000}, {-1000000000, 1000000000}}
        ));

        // Test Case 10: Array with zeros
        testCases.add(new TestCase(
            new int[]{0, 0, 0, 1},
            new int[]{0, 1, 2},
            4,
            new int[][]{{0, 0}, {0, 0}, {0, 0}, {0, 1}}
        ));

        // Test Case 11: k equal to total number of pairs
        testCases.add(new TestCase(
            new int[]{1, 3},
            new int[]{2, 4},
            4,
            new int[][]{{1, 2}, {1, 4}, {3, 2}, {3, 4}}
        ));

        // Test Harness Output
        System.out.println("==========================================================================================");
        System.out.printf("%-10s | %-25s | %-25s | %-8s | %s%n", "Test Case", "Expected", "Actual", "Status", "Details");
        System.out.println("==========================================================================================");

        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            List<List<Integer>> actual = solver.kSmallestPairs(tc.nums1, tc.nums2, tc.k);

            boolean isCorrect = tc.expected.equals(actual);
            if (isCorrect) {
                passed++;
            }

            String expectedStr = tc.expected.toString();
            String actualStr = actual != null ? actual.toString() : "null";
            
            // Truncate long representation for clean tabular display
            if (expectedStr.length() > 24) expectedStr = expectedStr.substring(0, 21) + "...";
            if (actualStr.length() > 24) actualStr = actualStr.substring(0, 21) + "...";

            String details = String.format("nums1: %s, nums2: %s, k: %d",
                    Arrays.toString(tc.nums1), Arrays.toString(tc.nums2), tc.k);

            System.out.printf("%-10d | %-25s | %-25s | %-8s | %s%n",
                    (i + 1),
                    expectedStr,
                    actualStr,
                    isCorrect ? "PASSED" : "FAILED",
                    details
            );
        }

        System.out.println("==========================================================================================");
        System.out.printf("SUMMARY: Passed %d / %d test cases. (Failed: %d)%n", passed, testCases.size(), testCases.size() - passed);
        System.out.println("==========================================================================================");
    }
}