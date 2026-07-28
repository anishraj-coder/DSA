package heap;

import java.util.*;

public class MaximumSumKSelection {

    /**
     * For each index i from 0 to n - 1:
     * Find all indices j where nums1[j] < nums1[i].
     * Choose at most k values of nums2[j] at these indices to maximize the total sum.
     * 
     * @param nums1 Array of integers
     * @param nums2 Array of integers
     * @param k Positive integer
     * @return Array of maximum sums for each corresponding index i
     */
    public long[] findMaxSum(int[] arr1, int[] arr2, int k) {
        
    	int n=arr1.length;
    	Pair[]pair=new Pair[n];
    	for(int i=0;i<n;i++) {
    		pair[i]=new Pair(i,arr1[i],arr2[i]);
    	}
    	
    	Arrays.sort(pair,(a,b)->Integer.compare(a.val, b.val));
    	
    	PriorityQueue<Pair>pq=new PriorityQueue<>((a,b)->Integer.compare(a.sum, b.sum));
    	long sum=0l;
    	long[]ans=new long[n];
    	
    	for(int i=0;i<n;) {
    		int j=i;
    		while (j < n && pair[j].val == pair[i].val) {
                j++;
            }
    		
    		for(int t=i;t<j;t++) {
    			ans[pair[t].idx]=sum;
    		}
    		
    		for(int t=i;t<j;t++) {
    			Pair curr=pair[t];
    			pq.offer(curr);
    			sum+=curr.sum;
    			if(pq.size()>k) {
    				sum-=pq.poll().sum;
    			}
    		}
    		i=j;
    	}    	
        return ans;
    }
    
    private static class Pair {
    	int idx,sum,val;
    	Pair(int idx,int val,int sum){
    		this.idx=idx;
    		this.val=val;
    		this.sum=sum;
    	}
    	
    }

    public static void main(String[] args) {
        MaximumSumKSelection solver = new MaximumSumKSelection();

        TestCase[] testCases = new TestCase[] {
            // Test Case 1: Standard example from description
            new TestCase(
                new int[]{4, 2, 1, 5, 3},
                new int[]{10, 20, 30, 40, 50},
                2,
                new long[]{80, 30, 0, 80, 50},
                "Standard case with varied values"
            ),
            // Test Case 2: All nums1 elements are equal
            new TestCase(
                new int[]{2, 2, 2, 2},
                new int[]{3, 1, 2, 3},
                1,
                new long[]{0, 0, 0, 0},
                "All nums1 values equal (no valid j < i)"
            ),
            // Test Case 3: Single element array
            new TestCase(
                new int[]{10},
                new int[]{50},
                1,
                new long[]{0},
                "Single element array (n = 1)"
            ),
            // Test Case 4: Duplicate nums1 elements with different nums2 values
            new TestCase(
                new int[]{3, 1, 3, 2, 3},
                new int[]{10, 20, 30, 40, 50},
                2,
                new long[]{90, 0, 90, 20, 90},
                "Duplicates in nums1 should not consider each other"
            ),
            // Test Case 5: k is larger than array length (k >= n)
            new TestCase(
                new int[]{1, 2, 3, 4},
                new int[]{10, 20, 30, 40},
                10,
                new long[]{0, 10, 30, 60},
                "k is larger than n (picks all valid nums2 elements)"
            ),
            // Test Case 6: Strictly decreasing nums1 array
            new TestCase(
                new int[]{5, 4, 3, 2, 1},
                new int[]{10, 20, 30, 40, 50},
                2,
                new long[]{90, 90, 90, 50, 0},
                "Strictly decreasing nums1 values"
            ),
            // Test Case 7: Strictly increasing nums1 array
            new TestCase(
                new int[]{1, 2, 3, 4, 5},
                new int[]{10, 20, 30, 40, 50},
                2,
                new long[]{0, 10, 30, 50, 70},
                "Strictly increasing nums1 values"
            ),
            // Test Case 8: k = 1 (Select at most 1 element)
            new TestCase(
                new int[]{3, 1, 4, 1, 5, 9},
                new int[]{100, 200, 300, 400, 500, 600},
                1,
                new long[]{400, 0, 400, 0, 400, 500},
                "k = 1 picking max element under condition"
            ),
            // Test Case 9: Large integer values (testing 64-bit sum overflow avoidance)
            new TestCase(
                new int[]{10, 20, 30},
                new int[]{1000000, 1000000, 1000000},
                2,
                new long[]{0, 1000000, 2000000},
                "Large numbers in nums2 requiring 64-bit long return type"
            ),
            // Test Case 10: Mixed values with multiple duplicate groups
            new TestCase(
                new int[]{5, 1, 5, 2, 1, 2},
                new int[]{100, 10, 200, 20, 30, 40},
                3,
                new long[]{100, 0, 100, 40, 0, 40},
                "Multiple duplicate clusters in nums1"
            )
        };

        int passed = 0;

        System.out.println("==================================================================================================");
        System.out.printf("%-10s | %-25s | %-25s | %-8s | %s%n", "Test Case", "Expected", "Actual", "Status", "Details");
        System.out.println("==================================================================================================");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            long[] actual = solver.findMaxSum(tc.nums1, tc.nums2, tc.k);
            boolean isCorrect = Arrays.equals(tc.expected, actual);

            if (isCorrect) passed++;

            String expectedStr = Arrays.toString(tc.expected);
            String actualStr = Arrays.toString(actual);
            String status = isCorrect ? "PASSED" : "FAILED";

            System.out.printf("%-10d | %-25s | %-25s | %-8s | %s%n", 
                              (i + 1), truncate(expectedStr, 25), truncate(actualStr, 25), status, tc.description);
        }

        System.out.println("==================================================================================================");
        System.out.printf("SUMMARY: Passed %d / %d test cases. (Failed: %d)%n", passed, testCases.length, (testCases.length - passed));
        System.out.println("==================================================================================================");
    }

    private static String truncate(String str, int width) {
        if (str.length() <= width) return str;
        return str.substring(0, width - 3) + "...";
    }

    private static class TestCase {
        int[] nums1;
        int[] nums2;
        int k;
        long[] expected;
        String description;

        TestCase(int[] nums1, int[] nums2, int k, long[] expected, String description) {
            this.nums1 = nums1;
            this.nums2 = nums2;
            this.k = k;
            this.expected = expected;
            this.description = description;
        }
    }
}