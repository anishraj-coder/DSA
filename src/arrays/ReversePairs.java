package arrays;

import java.util.Arrays;

public class ReversePairs {

    /**
     * Given an integer array nums, return the number of reverse pairs in the array.
     * A reverse pair is a pair (i, j) where 0 <= i < j < nums.length
     * and nums[i] > 2 * nums[j].
     */
    public int countReversePairs(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        return mergeSort(arr, 0, arr.length - 1);
    }

    public int mergeSort(int []arr ,int left,int right){
        if(left>=right)return 0;
        int count=0,mid=(right-left)/2+left;
        count+=mergeSort(arr,left,mid);
        count+=mergeSort(arr,mid+1,right);
        count+=count(arr,left,mid,right);
        merge(arr,left,mid,right);
        return count;
    }

    public int count(int[]arr,int low,int mid,int high){
        int right=mid+1,count=0;

        for(int i=low;i<=mid;i++){
            while(right<=high&&(long)arr[i]>(long)2*arr[right])right++;
            count+=right-(mid+1);
        }
        return count;
    }

    public void merge(int[]arr,int left,int mid,int right){
        int n1=mid-left+1,n2=right-mid;
        int[]arr1,arr2;
        arr1=new int[n1];
        arr2=new int[n2];

        for(int i=0;i<n1;i++)arr1[i]=arr[i+left];
        for(int i=0;i<n2;i++)arr2[i]=arr[mid+i+1];

        int i=0,j=0,k=left;
        while(i<n1&&j<n2){
            if(arr1[i]>arr2[j])arr[k++]=arr2[j++];
            else arr[k++]=arr1[i++];
        }
        while(i<n1)arr[k++]=arr1[i++];
        while(j<n2)arr[k++]=arr2[j++];
    }

    public static void main(String[] args) {
        ReversePairs solver = new ReversePairs();

        Object[][] testCases = {
                {new int[]{1, 3, 2, 3, 1}, 2, "Simple case with duplicates"},
                {new int[]{2, 4, 3, 5, 1}, 3, "Standard unsorted case"},
                {new int[]{1, 2, 3, 4, 5}, 0, "Already sorted ascending"},
                {new int[]{5, 4, 3, 2, 1}, 4, "Sorted descending"},
                {new int[]{0}, 0, "Single element"},
                {new int[]{2147483647, 2147483647, -2147483647, -2147483647}, 2, "Integer overflow check (Max/Min values)"},
                {new int[]{-5, -5}, 1, "Negative numbers same value (-5 > 2*-5 is -5 > -10)"},
                {new int[]{10, 2, 2, 2, 1}, 4, "One large number with many small successors"},
                {new int[]{1, 1, 1, 1}, 0, "All same small values"},
                {new int[]{-2, -1}, 0, "Negative values non-pair"},
                {new int[]{2000000000, 1000000000, 499999999}, 3, "Large values edge case"}
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int expected = (int) testCases[i][1];
            String description = (String) testCases[i][2];

            // Create a copy because some algorithms sort the array in-place
            int[] numsCopy = Arrays.copyOf(nums, nums.length);
            int actual = solver.countReversePairs(numsCopy);

            System.out.println("Test Case " + (i + 1) + ": " + description);
            System.out.println("Input: " + Arrays.toString(nums));
            System.out.println("Expected: " + expected + ", Actual: " + actual);

            if (actual == expected) {
                System.out.println("Result: PASSED");
                passed++;
            } else {
                System.out.println("Result: FAILED");
            }
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Score: " + passed + "/" + testCases.length);
    }
}