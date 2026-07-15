package arrays;

import java.util.Arrays;

public class InversionCount {



    /**
     * Computes the number of inversions in the array.
     * Two elements nums[i] and nums[j] form an inversion if nums[i] > nums[j] and i < j.
     * * @param arr The input array of integers.
     * @return The total count of inversions.
     */
    public long countInversions(int[] arr) {
        int n=arr.length;
        return mergeSort(arr,0,n-1);
    }

    public long mergeSort(int[]arr,int left,int right){
        if(left>=right)return 0L;
        long count=0L;
        int mid=(right-left)/2+left;
        count+=mergeSort(arr,left,mid);
        count+=mergeSort(arr,mid+1,right);
        count+=merge(arr,left,mid,right);
        return count;
    }

    public long merge(int []arr ,int left,int mid,int right){
        int n1=mid-left+1,n2=right-mid;
        int[]arr1=new int[n1];
        int []arr2=new int[n2];
        long count=0L;

        for(int i=0;i<n1;i++)arr1[i]=arr[left+i];
        for(int i=0;i<n2;i++)arr2[i]=arr[mid+i+1];
        int i=0,j=0,k=left;
        while(i<n1&&j<n2){
            if(arr1[i]<=arr2[j]){
                arr[k]=arr1[i];
                i++;k++;
            }else {
                arr[k]=arr2[j];
                j++;k++;
                count+=n1-i;
            }
        }
        while(i<n1)arr[k++]=arr1[i++];
        while(j<n2)arr[k++]=arr2[j++];
        return count;
    }


    public static void main(String[] args) {
        InversionCount counter = new InversionCount();

        // Test Case Definitions
        int[][] inputs = {
                {2, 3, 7, 1, 3, 5},                // Case 1: Standard case from example
                {-10, -5, 6, 11, 15, 17},          // Case 2: Already sorted (0 inversions)
                {5, 4, 3, 2, 1},                   // Case 3: Reverse sorted (Max inversions: n*(n-1)/2)
                {1, 1, 1, 1},                      // Case 4: All identical elements (0 inversions)
                {10},                              // Case 5: Single element (0 inversions)
                {},                                // Case 6: Empty array (0 inversions)
                {1, 5, 2, 6, 3, 0},                // Case 7: Mixed values with zero
                {10, -10, 10, -10},                // Case 8: Alternating duplicates
                {Integer.MAX_VALUE, 0, Integer.MIN_VALUE}, // Case 9: Large range/Edge values
                {1, 2, 3, 5, 4}                    // Case 10: Nearly sorted (1 inversion)
        };

        long[] expectedOutputs = {5, 0, 10, 0, 0, 0, 8, 3, 3, 1};

        int passed = 0;
        int failed = 0;

        System.out.println("--- Starting Test Execution ---\n");

        for (int i = 0; i < inputs.length; i++) {
            int[] currentInput = Arrays.copyOf(inputs[i], inputs[i].length);
            long expected = expectedOutputs[i];
            long actual = counter.countInversions(currentInput);

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(inputs[i]));
            System.out.println("Expected Output: " + expected);
            System.out.println("Actual Output:   " + actual);

            if (actual == expected) {
                System.out.println("Result: PASSED");
                passed++;
            } else {
                System.out.println("Result: FAILED");
                failed++;
            }
            System.out.println("-------------------------------");
        }

        System.out.println("\n--- Final Summary ---");
        System.out.println("Total Passed: " + passed);
        System.out.println("Total Failed: " + failed);
    }
}