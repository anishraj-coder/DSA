package stacks;

import java.util.ArrayList;
import java.util.Arrays;

public class RightGreaterElementCount {

    class Pair{
        int val,idx;
        Pair(int val,int idx){
            this.val=val;
            this.idx=idx;
        }
    }

    /**
     * For each query index in indices[], count how many elements to the right
     * of that index in arr[] are strictly greater than the element at that index.
     * * @param n       Length of the array
     * @param arr     The input integer array
     * @param queries The number of queries
     * @param indices The indices to check
     * @return        An array of counts for each query
     */
    public int[] countGreaterElements(int n, int[] arr, int queries, int[] indices) {
        Pair[]elements=new Pair[n];
        for(int i=0;i<n;i++){
            elements[i]=new Pair(arr[i],i);
        }
        int[]counts=new int[n];
        mergeSort(elements,0,n-1,counts);

        int[]result=new int[queries];
        for(int i=0;i<queries;i++){
            result[i]=counts[indices[i]];
        }
        return result;
    }

    public void mergeSort(Pair[]arr,int left,int right,int[]counts){
        if(left>=right)return ;
        int mid=left+(right-left)/2;
        mergeSort(arr,left,mid,counts);
        mergeSort(arr,mid+1,right,counts);
        mergeAndCount(arr,left,mid,right,counts);
    }

    public void mergeAndCount(Pair[]arr,int left,int mid,int right,int[]counts){
        int j=mid+1;
        for(int i=left;i<=mid;i++){
            while(j<=right&&arr[j].val<=arr[i].val)j++;
            counts[arr[i].idx]+=(right+1-j);
        }
        int n1=mid-left+1,n2=right-mid;
        Pair[]temp=new Pair[n1+n2];
        j=mid+1;
        int i=left,k=0;
        while(i<=mid&&j<=right){
            if(arr[i].val<=arr[j].val){
                temp[k++]=arr[i++];
            }else temp[k++]=arr[j++];
        }
        while(i<=mid)temp[k++]=arr[i++];
        while(j<=right)temp[k++]=arr[j++];
        for(int x=0;x<n1+n2;x++){
            arr[left+x]=temp[x];
        }
    }

    public static void main(String[] args) {
        RightGreaterElementCount solver = new RightGreaterElementCount();
        int totalPassed = 0;

        // Test Cases Definitions
        Object[][] testCases = {
                { "Example 1", new int[]{3, 4, 2, 7, 5, 8, 10, 6}, new int[]{0, 5}, new int[]{6, 1} },
                { "Example 2", new int[]{1, 2, 3, 4, 1}, new int[]{0, 3}, new int[]{3, 0} },
                { "All Identical", new int[]{5, 5, 5, 5, 5}, new int[]{0, 2}, new int[]{0, 0} },
                { "Strictly Decreasing", new int[]{10, 8, 6, 4, 2}, new int[]{0, 1, 4}, new int[]{0, 0, 0} },
                { "Strictly Increasing", new int[]{1, 2, 3, 4, 5}, new int[]{0, 2, 4}, new int[]{4, 2, 0} },
                { "Last Element Query", new int[]{10, 20, 30}, new int[]{2}, new int[]{0} },
                { "Large Jump", new int[]{10, 1, 1, 1, 1, 11}, new int[]{0}, new int[]{1} },
                { "Duplicates to the Right", new int[]{5, 10, 10, 2, 10}, new int[]{0}, new int[]{3} },
                { "Single Element", new int[]{100}, new int[]{0}, new int[]{0} },
                { "Negative Numbers", new int[]{-5, -2, -10, 0, -1}, new int[]{0, 2}, new int[]{3, 2} }
        };

        System.out.println("--- Starting Test Execution ---");
        for (int i = 0; i < testCases.length; i++) {
            String name = (String) testCases[i][0];
            int[] arr = (int[]) testCases[i][1];
            int[] indices = (int[]) testCases[i][2];
            int[] expected = (int[]) testCases[i][3];

            int[] actual = solver.countGreaterElements(arr.length, arr, indices.length, indices);

            boolean passed = Arrays.equals(actual, expected);
            if (passed) totalPassed++;

            System.out.printf("Test %d (%s): %s\n", i + 1, name, passed ? "PASSED" : "FAILED");
            if (!passed) {
                System.out.println("   Input Arr: " + Arrays.toString(arr));
                System.out.println("   Queries:   " + Arrays.toString(indices));
                System.out.println("   Expected:  " + Arrays.toString(expected));
                System.out.println("   Actual:    " + Arrays.toString(actual));
            }
        }

        System.out.println("-------------------------------");
        System.out.printf("Final Result: %d/%d Passed\n", totalPassed, testCases.length);
    }
}