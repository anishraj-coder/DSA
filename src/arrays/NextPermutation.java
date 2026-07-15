package arrays;

import java.util.Arrays;

public class NextPermutation {

    /**
     * Rearranges numbers into the lexicographically next greater permutation of numbers.
     * The replacement must be in place and use only constant extra memory.
     * @param arr An array of integers.
     */
    public void nextPermutation(int[] arr) {
        int n=arr.length;
        if(n==1)return;
        int point=-1;
        for(int i=n-2;i>=0;i--){
            if(arr[i]<arr[i+1]){
                point=i;
                break;
            }
        }
        if(point==-1){
            reverse(arr,0,n-1);
            return;
        }
        for(int i=n-1;i>=point;i--){
            if(arr[i]>arr[point]){
                int t=arr[i];
                arr[i]=arr[point];
                arr[point]=t;
                break;
            }
        }
        reverse(arr,point+1,n-1);
    }

    public void reverse(int[] arr,int s,int e){
        while(s<e){
            int t=arr[s];
            arr[s]=arr[e];
            arr[e]=t;
            s++;
            e--;
        }
    }

    public static void main(String[] args) {
        NextPermutation runner = new NextPermutation();

        // Test Cases: {Input Array, Expected Array, Description}
        Object[][] testCases = {
                {new int[]{1, 2, 3}, new int[]{1, 3, 2}, "Simple increasing sequence"},
                {new int[]{3, 2, 1}, new int[]{1, 2, 3}, "Entirely decreasing (Last permutation)"},
                {new int[]{1, 1, 5}, new int[]{1, 5, 1}, "Duplicate elements"},
                {new int[]{1, 3, 2}, new int[]{2, 1, 3}, "Pivot in the middle"},
                {new int[]{2, 3, 1}, new int[]{3, 1, 2}, "Standard case"},
                {new int[]{1}, new int[]{1}, "Single element"},
                {new int[]{1, 5, 1}, new int[]{5, 1, 1}, "Duplicates with pivot"},
                {new int[]{4, 2, 0, 2, 3, 2, 0}, new int[]{4, 2, 0, 3, 0, 2, 2}, "Complex mixed sequence"},
                {new int[]{1, 2}, new int[]{2, 1}, "Two elements ascending"},
                {new int[]{2, 1}, new int[]{1, 2}, "Two elements descending"}
        };

        int passed = 0;

        System.out.println("--- Starting Test Execution ---\n");

        for (int i = 0; i < testCases.length; i++) {
            int[] input = (int[]) testCases[i][0];
            int[] expected = (int[]) testCases[i][1];
            String description = (String) testCases[i][2];

            // Work on a copy since the operation is in-place
            int[] nums = Arrays.copyOf(input, input.length);
            runner.nextPermutation(nums);

            boolean isCorrect = Arrays.equals(nums, expected);

            if (isCorrect) {
                passed++;
            }

            System.out.printf("Test Case %d: %s\n", i + 1, description);
            System.out.printf("Input:    %s\n", Arrays.toString(input));
            System.out.printf("Expected: %s\n", Arrays.toString(expected));
            System.out.printf("Actual:   %s\n", Arrays.toString(nums));
            System.out.println("Result: " + (isCorrect ? "✅ PASSED" : "❌ FAILED"));
            System.out.println("----------------------------------------");
        }

        System.out.printf("\nFinal Result: %d/%d Passed.\n", passed, testCases.length);
    }
}