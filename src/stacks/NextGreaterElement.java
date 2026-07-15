package stacks;

import java.util.*;

public class NextGreaterElement {

    /**
     * Finds the next greater element for each nums1[i] in nums2.
     * Constraints: 1 <= nums1.length <= nums2.length <= 1000
     * Time Complexity Requirement: O(nums1.length + nums2.length)
     */
    public int[] nextGreaterElement(int[] arr1, int[] arr2) {
        int n1=arr1.length,n2=arr2.length;
        if(n1==0)return new int[0];
        HashMap<Integer,Integer>map=new HashMap<>();
        Stack<Integer>st=new Stack<>();
        for(int i=0;i<n2;i++){
            while(!st.isEmpty()&&arr2[i]>arr2[st.peek()]){
                int idx=st.pop();
                map.put(arr2[idx],arr2[i]);
            }
            st.push(i);
        }
        while(!st.isEmpty()){
            int idx=st.pop();
            map.put(arr2[idx],-1);
        }
        for(int i=0;i<n1;i++){
            arr1[i]=map.getOrDefault(arr1[i],-1);
        }
        return arr1;
    }

    public static void main(String[] args) {
        NextGreaterElement solver = new NextGreaterElement();

        // Test Case Definitions
        int[][][] testCases = {
                {{4, 1, 2}, {1, 3, 4, 2}, {-1, 3, -1}},    // Example 1
                {{2, 4}, {1, 2, 3, 4}, {3, -1}},          // Example 2
                {{1, 3, 5, 2, 4}, {6, 5, 4, 3, 2, 1, 7}, {7, 7, 7, 7, 7}}, // All find the last element
                {{6, 2, 4}, {6, 5, 4, 3, 2, 1}, {-1, -1, -1}}, // Strictly decreasing
                {{1, 2, 3}, {1, 2, 3}, {2, 3, -1}},       // Strictly increasing
                {{10}, {10}, {-1}},                       // Single element
                {{5, 10}, {10, 5, 12, 11}, {12, 12}},     // Mixed order
                {{1}, {2, 1, 3}, {3}},                    // Middle element query
                {{1, 5}, {1, 4, 7, 5, 8}, {4, 8}},        // Gap between elements
                {{8, 1, 4}, {4, 1, 2, 8, 10}, {10, 2, 8}} // Random distribution
        };

        int passed = 0;
        System.out.println("Running Next Greater Element I Tests...\n");

        for (int i = 0; i < testCases.length; i++) {
            int[] nums1 = testCases[i][0];
            int[] nums2 = testCases[i][1];
            int[] expected = testCases[i][2];

            int[] actual = solver.nextGreaterElement(nums1, nums2);
            boolean isMatch = Arrays.equals(actual, expected);

            if (isMatch) passed++;

            System.out.printf("Test %d: %s%n", i + 1, isMatch ? "PASS" : "FAIL");
            if (!isMatch) {
                System.out.println("   Input nums1: " + Arrays.toString(nums1));
                System.out.println("   Input nums2: " + Arrays.toString(nums2));
                System.out.println("   Expected: " + Arrays.toString(expected));
                System.out.println("   Actual:   " + Arrays.toString(actual));
            }
        }

        System.out.println("\n-------------------------------------------------------");
        System.out.printf("Final Result: %d/%d Tests Passed%n", passed, testCases.length);
    }
}