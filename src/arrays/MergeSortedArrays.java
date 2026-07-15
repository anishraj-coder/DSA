package arrays;

import java.util.Arrays;

public class MergeSortedArrays {

    /**
     * Problem: Merge nums2 into nums1 as one sorted array.
     * Note: Modify nums1 in-place. Do not return anything.
     * Time Complexity Goal: O(m + n)
     * Space Complexity Goal: O(1)
     */
    public void merge(int[] arr1, int m, int[] arr2, int n) {
        if(n==0)return;
        int p1=m-1,p2=n-1,i=m+n-1;
        while(p1>=0&&p2>=0){
            if(arr1[p1]>arr2[p2]){
                arr1[i]=arr1[p1];
                p1--;
            }else{
                arr1[i]=arr2[p2];
                p2--;
            }
            i--;
        }

        while(p1>=0)arr1[i--]=arr1[p1--];
        while(p2>=0)arr1[i--]=arr2[p2--];
    }

    public void swap(int[]arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static void main(String[] args) {
        MergeSortedArrays solver = new MergeSortedArrays();

        // Test Cases: {nums1, m, nums2, n, expected_nums1_content}
        Object[][] testCases = {
                { // 1. Standard merge
                        new int[]{1, 2, 3, 0, 0, 0}, 3,
                        new int[]{2, 5, 6}, 3,
                        new int[]{1, 2, 2, 3, 5, 6}
                },
                { // 2. nums2 is empty
                        new int[]{1}, 1,
                        new int[]{}, 0,
                        new int[]{1}
                },
                { // 3. nums1 is empty (only placeholder 0)
                        new int[]{0}, 0,
                        new int[]{1}, 1,
                        new int[]{1}
                },
                { // 4. All elements in nums1 are smaller
                        new int[]{1, 2, 3, 0, 0, 0}, 3,
                        new int[]{4, 5, 6}, 3,
                        new int[]{1, 2, 3, 4, 5, 6}
                },
                { // 5. All elements in nums1 are larger
                        new int[]{4, 5, 6, 0, 0, 0}, 3,
                        new int[]{1, 2, 3}, 3,
                        new int[]{1, 2, 3, 4, 5, 6}
                },
                { // 6. Duplicate elements across arrays
                        new int[]{2, 2, 2, 0, 0, 0}, 3,
                        new int[]{2, 2, 2}, 3,
                        new int[]{2, 2, 2, 2, 2, 2}
                },
                { // 7. Negative numbers
                        new int[]{-5, -2, 0, 0, 0}, 3,
                        new int[]{-3, 1}, 2,
                        new int[]{-5, -3, -2, 0, 1}
                },
                { // 8. nums1 has much more space than needed for m
                        new int[]{1, 5, 8, 0, 0, 0, 0}, 3,
                        new int[]{2, 3, 10, 12}, 4,
                        new int[]{1, 2, 3, 5, 8, 10, 12}
                },
                { // 9. Single element each, nums2 smaller
                        new int[]{10, 0}, 1,
                        new int[]{1}, 1,
                        new int[]{1, 10}
                },
                { // 10. Large range difference
                        new int[]{-100, 100, 0, 0}, 2,
                        new int[]{-50, 50}, 2,
                        new int[]{-100, -50, 50, 100}
                }
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            int[] nums1 = (int[]) testCases[i][0];
            int m = (int) testCases[i][1];
            int[] nums2 = (int[]) testCases[i][2];
            int n = (int) testCases[i][3];
            int[] expected = (int[]) testCases[i][4];

            // Create a copy of nums1 for the input display before modification
            int[] inputCopy = Arrays.copyOf(nums1, nums1.length);

            solver.merge(nums1, m, nums2, n);

            boolean isMatch = Arrays.equals(nums1, expected);
            if (isMatch) passed++;

            System.out.println("Test Case " + (i + 1) + ": " + (isMatch ? "PASSED" : "FAILED"));
            System.out.println("   Input Nums1: " + Arrays.toString(inputCopy) + " (m=" + m + ")");
            System.out.println("   Input Nums2: " + Arrays.toString(nums2) + " (n=" + n + ")");
            System.out.println("   Expected:    " + Arrays.toString(expected));
            System.out.println("   Actual:      " + Arrays.toString(nums1));
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Result: " + passed + "/" + testCases.length + " cases passed.");
    }
}