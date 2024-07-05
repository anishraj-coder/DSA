package BitManupulation;

/*Given a non-empty array of integers nums, every element appears 
 * twice except for one. Find that single one.

You must implement a solution with a linear runtime 
complexity and use only constant extra space.

 

Example 1:

Input: nums = [2,2,1]
Output: 1
Example 2:

Input: nums = [4,1,2,1,2]
Output: 4
Example 3:

Input: nums = [1]
Output: 1
 

Constraints:

1 <= nums.length <= 3 * 104
-3 * 104 <= nums[i] <= 3 * 104
Each element in the array appears twice except for
 one element which appears only once.*/




/**
 * This class defines a method `singleNumber` that finds the single element
 * that appears only once in an array where every other element appears twice.
 * It uses bitwise XOR (^) for a linear time and constant space solution.
 */
public class SingleNumber {

    public static int singleNumber(int[] arr) {
        // Initialize a variable 'ans' to store the single number.
        int ans = arr[0];

        // Loop through all elements in the array starting from the second element (index 1).
        for (int i = 1; i < arr.length; i++) {
            // Perform bitwise XOR between 'ans' and the current element (arr[i]).
            // - XOR of a number with itself is 0.
            // - XOR of a number with 0 is the number itself.
            // By XORing all elements (including duplicates), the duplicates cancel out
            // leaving the single number as the result.
            ans ^= arr[i];
        }

        // Return the final value of 'ans' which holds the single number.
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 2, 1, 2};
        System.out.println(singleNumber(arr)); // Output: 4
    }
}
