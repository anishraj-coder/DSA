package queues;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindowMaximum {

    // TO DO: Implement your solution inside this method
    public int[] maxSlidingWindow(int[] arr, int k) {
        int n=arr.length;
        if(k>n)return new int[0];
        if(k==n){
            int max=Integer.MIN_VALUE;
            for(int a:arr)max=Math.max(max,a);
            return new int[]{max};
        }
        int[]ans=new int[n-k+1];
        Deque<Integer> q=new ArrayDeque<>();
        for(int i=0;i<k;i++){
            while(!q.isEmpty()&&arr[i]>=arr[q.peekLast()])q.pollLast();
            q.offer(i);
        }
        ans[0]=arr[q.peekFirst()];
        int j=1;
        for(int i=k;i<n;i++){
            while(!q.isEmpty()&&q.peekFirst()==i-k)q.pollFirst();
            while(!q.isEmpty()&&arr[i]>=arr[q.peekLast()])q.pollLast();
            q.offer(i);
            ans[j++]=arr[q.peekFirst()];
        }
        return ans;
    }

    public static void main(String[] args) {
        SlidingWindowMaximum solver = new SlidingWindowMaximum();
        int passedTests = 0;
        int totalTests = 0;

        // --- TEST CASE 1: Standard Example 1 ---
        totalTests++;
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        int[] expected1 = {3, 3, 5, 5, 6, 7};
        passedTests += runTest(1, nums1, k1, expected1, solver.maxSlidingWindow(nums1, k1));

        // --- TEST CASE 2: Single Element (k=1) ---
        totalTests++;
        int[] nums2 = {1};
        int k2 = 1;
        int[] expected2 = {1};
        passedTests += runTest(2, nums2, k2, expected2, solver.maxSlidingWindow(nums2, k2));

        // --- TEST CASE 3: Window Size Equals Array Length ---
        totalTests++;
        int[] nums3 = {4, 2, 10, 8, 1};
        int k3 = 5;
        int[] expected3 = {10};
        passedTests += runTest(3, nums3, k3, expected3, solver.maxSlidingWindow(nums3, k3));

        // --- TEST CASE 4: Strictly Decreasing Array ---
        totalTests++;
        int[] nums4 = {9, 8, 7, 6, 5, 4, 3};
        int k4 = 3;
        int[] expected4 = {9, 8, 7, 6, 5};
        passedTests += runTest(4, nums4, k4, expected4, solver.maxSlidingWindow(nums4, k4));

        // --- TEST CASE 5: Strictly Increasing Array ---
        totalTests++;
        int[] nums5 = {1, 2, 3, 4, 5, 6};
        int k5 = 3;
        int[] expected5 = {3, 4, 5, 6};
        passedTests += runTest(5, nums5, k5, expected5, solver.maxSlidingWindow(nums5, k5));

        // --- TEST CASE 6: All Elements Identical ---
        totalTests++;
        int[] nums6 = {7, 7, 7, 7, 7};
        int k6 = 2;
        int[] expected6 = {7, 7, 7, 7};
        passedTests += runTest(6, nums6, k6, expected6, solver.maxSlidingWindow(nums6, k6));

        // --- TEST CASE 7: Negative Values and Zero ---
        totalTests++;
        int[] nums7 = {-7, -8, 0, -1, -5, -2, -6};
        int k7 = 3;
        int[] expected7 = {0, 0, 0, -1, -2};
        passedTests += runTest(7, nums7, k7, expected7, solver.maxSlidingWindow(nums7, k7));

        // --- TEST CASE 8: Window Size 1 ---
        totalTests++;
        int[] nums8 = {5, 2, 8, 1};
        int k8 = 1;
        int[] expected8 = {5, 2, 8, 1};
        passedTests += runTest(8, nums8, k8, expected8, solver.maxSlidingWindow(nums8, k8));

        // --- TEST CASE 9: Mountain Shape (Peaks and Valleys) ---
        totalTests++;
        int[] nums9 = {1, 10, 1, 1, 10, 1, 1};
        int k9 = 3;
        int[] expected9 = {10, 10, 10, 10, 10};
        passedTests += runTest(9, nums9, k9, expected9, solver.maxSlidingWindow(nums9, k9));

        // --- TEST CASE 10: Alternating Extreme Spikes ---
        totalTests++;
        int[] nums10 = {10000, -10000, 10000, -10000, 10000};
        int k10 = 2;
        int[] expected10 = {10000, 10000, 10000, 10000};
        passedTests += runTest(10, nums10, k10, expected10, solver.maxSlidingWindow(nums10, k10));

        // --- SUMMARY ---
        System.out.println("\n--------------------------------------------------");
        System.out.println("Execution Summary: " + passedTests + " / " + totalTests + " Tests Passed.");
        if (passedTests == totalTests) {
            System.out.println("Result: SUCCESS 🎉");
        } else {
            System.out.println("Result: FAILURE ❌");
        }
        System.out.println("--------------------------------------------------");
    }

    private static int runTest(int testNum, int[] nums, int k, int[] expected, int[] actual) {
        boolean isSuccess = Arrays.equals(expected, actual);
        System.out.println("Test Case " + testNum + ": " + (isSuccess ? "PASSED ✅" : "FAILED ❌"));
        System.out.println("  Input Array: " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("  Expected Output: " + Arrays.toString(expected));
        System.out.println("  Actual Output:   " + Arrays.toString(actual));
        System.out.println();
        return isSuccess ? 1 : 0;
    }
}