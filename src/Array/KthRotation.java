package Array;
/*Certainly! Here's a theoretical explanation of the k-th rotation solution as implemented in the code:

Theory: K-th Rotation of an Array

The k-th rotation of an array involves shifting all elements of the array by k positions, either to the left or right. This implementation provides methods for both right rotation (fromBack) and left rotation (fromFront).

Key Concepts:

1. Array Reversal:
   The core technique used in this solution is array reversal. By strategically reversing portions of the array, we can achieve rotation efficiently.

2. Right Rotation (fromBack):
   To rotate an array of n elements k positions to the right:
   a) Reverse the entire array.
   b) Reverse the first k elements.
   c) Reverse the remaining n-k elements.

3. Left Rotation (fromFront):
   To rotate an array of n elements k positions to the left:
   a) Reverse the entire array.
   b) Reverse the first n-k elements.
   c) Reverse the last k elements.

Algorithm Explanation:

1. Reversal Method (rev):
   - This method reverses a portion of the array from index s to e.
   - It uses a two-pointer approach, swapping elements from both ends and moving towards the center.

2. Right Rotation (fromBack):
   - First, reverse the entire array. This brings the last k elements to the front, but in reverse order.
   - Then, reverse the first k elements to correct their order.
   - Finally, reverse the remaining n-k elements to correct their order.

3. Left Rotation (fromFront):
   - First, reverse the entire array. This brings the first k elements to the end, but in reverse order.
   - Then, reverse the first n-k elements to correct their order.
   - Finally, reverse the last k elements to correct their order.

Time and Space Complexity:
- Time Complexity: O(n), where n is the number of elements in the array.
  Each reversal operation takes O(n/2) time, and we perform a constant number of reversals.
- Space Complexity: O(1), as the rotation is performed in-place without using any extra space.

Advantages:
1. In-place rotation without requiring additional memory.
2. Efficient for large arrays.
3. Works for both left and right rotations with minimal code changes.

Considerations:
- The implementation assumes that k is less than or equal to the array length. For a more robust solution, k should be normalized by taking k modulo the array length.

This approach provides an elegant and efficient solution to the array rotation problem, utilizing the properties of array reversal to achieve the desired rotation.*/

public class KthRotation {
    // Method to reverse a portion of the array from index s to e
    public static void rev(int[] arr, int s, int e) {
        while (s < e) {
            // Swap elements at s and e
            int t = arr[s];
            arr[s] = arr[e];
            arr[e] = t;
            // Move pointers towards center
            s++;
            e--;
        }
    }

    // Method to rotate array to the right by k positions
    public static void fromBack(int[] arr, int k) {
        // Step 1: Reverse the entire array
        rev(arr, 0, arr.length - 1);
        // Step 2: Reverse the first k elements
        rev(arr, 0, k - 1);
        // Step 3: Reverse the remaining elements
        rev(arr, k, arr.length - 1);
    }

    // Method to rotate array to the left by k positions
    public static void fromFront(int[] arr, int k) {
        // Step 1: Reverse the entire array
        rev(arr, 0, arr.length - 1);
        // Step 2: Reverse the first n-k elements
        rev(arr, 0, arr.length - k - 1);
        // Step 3: Reverse the last k elements
        rev(arr, arr.length - k, arr.length - 1);
    }

    // Method to print the array
    public static void view(int[] arr) {
        for (int i : arr)
            System.out.print(i);
        System.out.println();
    }

    public static void main(String[] args) {
        // Initialize the array
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        // Demonstrate right rotation by 3 positions
        fromBack(arr, 3);
        view(arr);

        // Reset the array
        for (int i = 0; i < arr.length; i++)
            arr[i] = i + 1;

        // Demonstrate left rotation by 3 positions
        fromFront(arr, 3);
        view(arr);
    }
}
