package searching;

/**
 * Problem: Search in Rotated Sorted Array
 * Difficulty: Medium
 * Constraint: O(log n) time complexity
 */
public class RotatedSortedArraySearch1 {

    /**
     * Finds the index of the target element in a rotated sorted array.
     * @param arr   The rotated sorted array of unique integers.
     * @param target The integer to find.
     * @return The 0-indexed position of target, or -1 if not found.
     */
    public int search(int[] arr, int target) {
        int n=arr.length;
        if(n==1)return (arr[0]==target)?0:-1;
        int low=0,hi=n-1;
        while(low<=hi){
            int mid=(hi-low)/2+low;
            if(arr[mid]==target)return mid;
            if(arr[low]<arr[mid]){
                if(target>=arr[low]&&target<=arr[mid])hi=mid-1;
                else low=mid+1;
            }else{
                if(target>=arr[mid]&&target<=arr[hi])low=mid+1;
                else hi=mid-1;
            }
        }
        return -1; // Placeholder
    }

    public static void main(String[] args) {
        RotatedSortedArraySearch1 instance = new RotatedSortedArraySearch1();

        // Test Case Definitions
        TestCase[] testCases = {
                new TestCase(new int[]{4, 5, 6, 7, 0, 1, 2}, 0, 4, "Standard rotated array (target in right half)"),
                new TestCase(new int[]{4, 5, 6, 7, 0, 1, 2}, 3, -1, "Target not present"),
                new TestCase(new int[]{1}, 0, -1, "Single element array (target missing)"),
                new TestCase(new int[]{1}, 1, 0, "Single element array (target present)"),
                new TestCase(new int[]{6, 7, 1, 2, 3, 4, 5}, 6, 0, "Target at the very start (pivot point)"),
                new TestCase(new int[]{2, 3, 4, 5, 6, 7, 1}, 1, 6, "Target at the very end (after pivot)"),
                new TestCase(new int[]{3, 5, 1}, 3, 0, "Small rotated array (target at index 0)"),
                new TestCase(new int[]{5, 1, 3}, 3, 2, "Small rotated array (target at last index)"),
                new TestCase(new int[]{1, 3, 5}, 5, 2, "Array not rotated (standard binary search case)"),
                new TestCase(new int[]{8, 9, 2, 3, 4}, 9, 1, "Pivot in the middle, target in left sorted portion")
        };

        int passed = 0;

        System.out.println("Executing Search in Rotated Sorted Array Test Cases...\n");
        System.out.printf("%-5s | %-30s | %-8s | %-8s | %-8s%n", "ID", "Description", "Exp", "Act", "Result");
        System.out.println("---------------------------------------------------------------------------------------");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int actual = instance.search(tc.nums, tc.target);
            boolean isCorrect = (actual == tc.expected);
            if (isCorrect) passed++;

            String result = isCorrect ? "PASSED" : "FAILED";
            System.out.printf("%-5d | %-30s | %-8d | %-8d | %-8s%n",
                    i + 1, tc.description, tc.expected, actual, result);
        }

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("Final Score: %d/%d cases passed.%n", passed, testCases.length);
    }

    static class TestCase {
        int[] nums;
        int target;
        int expected;
        String description;

        TestCase(int[] nums, int target, int expected, String description) {
            this.nums = nums;
            this.target = target;
            this.expected = expected;
            this.description = description;
        }
    }
}