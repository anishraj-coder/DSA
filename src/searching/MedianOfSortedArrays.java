package searching;

import java.net.Inet4Address;
import java.util.*;

public class MedianOfSortedArrays {

    /**
     * Returns the median of two sorted arrays.
     * The overall run time complexity should be O(log (m+n)).
     *
     * @param arr1 Sorted array of size m.
     * @param arr2 Sorted array of size n.
     * @return      The median as a double.
     */
    public double findMedianSortedArrays(int[] arr1, int[] arr2) {
        if(arr1.length>arr2.length)return findMedianSortedArrays(arr2,arr1);
        int n1=arr1.length,n2=arr2.length;
        int low=0,hi=n1;
        while(low<=hi){
            int part1=(hi-low)/2+low;
            int part2=(n1+n2+1)/2-part1;

            int left1=part1==0?Integer.MIN_VALUE:arr1[part1-1];
            int left2=part2==0?Integer.MIN_VALUE:arr2[part2-1];

            int right1=part1==n1?Integer.MAX_VALUE:arr1[part1];
            int right2=part2==n2? Integer.MAX_VALUE:arr2[part2];

            if(left1<=right2&&left2<=right1){
                if((n1+n2)%2==0){
                    return (Math.max(left1,left2)+Math.min(right1,right2))/2.0;
                }else return Math.max(left1,left2);
            }else if(left1>right2){
                hi=part1-1;
            }else low=part1+1;
        }
        return 0.0;
    }

    public static void main(String[] args) {
        MedianOfSortedArrays solver = new MedianOfSortedArrays();
        int passed = 0;
        int total = 10;

        // Test Case 1: Standard odd total length
        passed += runTest(1, solver, new int[]{1, 3}, new int[]{2}, 2.0);

        // Test Case 2: Standard even total length
        passed += runTest(2, solver, new int[]{1, 2}, new int[]{3, 4}, 2.5);

        // Test Case 3: One array is empty
        passed += runTest(3, solver, new int[]{}, new int[]{1}, 1.0);

        // Test Case 4: One array is empty (even length)
        passed += runTest(4, solver, new int[]{2, 3}, new int[]{}, 2.5);

        // Test Case 5: All elements in nums1 < nums2
        passed += runTest(5, solver, new int[]{1, 2}, new int[]{5, 6, 7, 8}, 5.5);

        // Test Case 6: Interleaved elements
        passed += runTest(6, solver, new int[]{1, 5, 8}, new int[]{2, 3, 10}, 4.0);

        // Test Case 7: Single elements in both
        passed += runTest(7, solver, new int[]{1}, new int[]{2}, 1.5);

        // Test Case 8: Duplicate elements
        passed += runTest(8, solver, new int[]{1, 1, 1}, new int[]{1, 1, 1}, 1.0);

        // Test Case 9: Negative values
        passed += runTest(9, solver, new int[]{-5, -3, -1}, new int[]{-2, 0}, -2.0);

        // Test Case 10: Large arrays with specific partition (Edge Case)
        passed += runTest(10, solver, new int[]{100000}, new int[]{100001}, 100000.5);

        System.out.println("\n---------------------------------------");
        System.out.println("Final Result: " + passed + "/" + total + " cases passed.");
        System.out.println("---------------------------------------");
    }

    private static int runTest(int id, MedianOfSortedArrays solver, int[] nums1, int[] nums2, double expected) {
        double actual = solver.findMedianSortedArrays(nums1, nums2);
        boolean isCorrect = Math.abs(actual - expected) < 1e-6;

        System.out.printf("Test Case %d: %s\n", id, isCorrect ? "PASSED" : "FAILED");
        System.out.printf("   Input: nums1=%s, nums2=%s\n", Arrays.toString(nums1), Arrays.toString(nums2));
        System.out.printf("   Expected: %.5f\n", expected);
        System.out.printf("   Actual:   %.5f\n", actual);
        System.out.println();

        return isCorrect ? 1 : 0;
    }
}