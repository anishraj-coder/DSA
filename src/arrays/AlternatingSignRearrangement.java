package arrays;

import java.util.Arrays;

public class AlternatingSignRearrangement {

    /**
     * Rearranges the array elements by sign while preserving relative order.
     * @param arr An even-length array with equal numbers of positive and negative integers.
     * @return The rearranged array starting with a positive integer.
     */
    public int[] rearrangeArray(int[] arr) {
        int n=arr.length, s=0,e=n/2;
        if(n==0)return arr;
        int[]res=new int [n];
        int pos=0,neg=1;
        for(int i=0;i<n;i++){
            if(arr[i]<0){
                res[neg]=arr[i];
                neg+=2;
            }else{
                res[pos]=arr[i];
                pos+=2;
            }
        }
        return res;
    }
    public void print(int[] arr,String message){
        System.out.println(message);
        System.out.println("____________________");
        for(int a:arr){
            System.out.print(a+",");
        }
        System.out.println("\n______________________");
    }
    public void swap(int[]arr,int i,int j){
        int t=arr[i];
        arr[i]=arr[j];
        arr[j]=t;
    }

    public static void main(String[] args) {
        AlternatingSignRearrangement runner = new AlternatingSignRearrangement();

        // Test Cases: {Input Array, Expected Array, Description}
        Object[][] testCases = {
                {
                        new int[]{3, 1, -2, -5, 2, -4},
                        new int[]{3, -2, 1, -5, 2, -4},
                        "Standard mixed case"
                },
                {
                        new int[]{-1, 1},
                        new int[]{1, -1},
                        "Smallest possible even length"
                },
                {
                        new int[]{1, 2, 3, -1, -2, -3},
                        new int[]{1, -1, 2, -2, 3, -3},
                        "All positives followed by all negatives"
                },
                {
                        new int[]{-1, -2, -3, 1, 2, 3},
                        new int[]{1, -1, 2, -2, 3, -3},
                        "All negatives followed by all positives"
                },
                {
                        new int[]{10, -10, 20, -20, 30, -30},
                        new int[]{10, -10, 20, -20, 30, -30},
                        "Already correctly ordered"
                },
                {
                        new int[]{2, -1, 2, -1},
                        new int[]{2, -1, 2, -1},
                        "Duplicate values"
                },
                {
                        new int[]{1, -2, 3, -4, 5, -6, 7, -8},
                        new int[]{1, -2, 3, -4, 5, -6, 7, -8},
                        "Longer alternating sequence"
                },
                {
                        new int[]{-5, -4, 1, 2},
                        new int[]{1, -5, 2, -4},
                        "Negatives at the start"
                },
                {
                        new int[]{100000, -100000, 5, -5},
                        new int[]{100000, -100000, 5, -5},
                        "Large constraint values"
                },
                {
                        new int[]{1, 5, -2, 6, -3, -8},
                        new int[]{1, -2, 5, -3, 6, -8},
                        "Scattered signs"
                }
        };

        int passed = 0;

        System.out.println("--- Starting Test Execution ---\n");

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int[] expected = (int[]) testCases[i][1];
            String description = (String) testCases[i][2];

            int[] actual = runner.rearrangeArray(nums);
            boolean isCorrect = Arrays.equals(actual, expected);

            if (isCorrect) {
                passed++;
            }

            System.out.printf("Test Case %d: %s\n", i + 1, description);
            System.out.printf("Input: %s\n", Arrays.toString(nums));
            System.out.printf("Expected: %s\n", Arrays.toString(expected));
            System.out.printf("Actual:   %s\n", Arrays.toString(actual));
            System.out.println("Result: " + (isCorrect ? "✅ PASSED" : "❌ FAILED"));
            System.out.println("----------------------------------------");
        }

        System.out.printf("\nFinal Result: %d/%d Passed.\n", passed, testCases.length);
    }
}