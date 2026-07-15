package arrays;

import java.util.*;

public class ThreeSum {

    /**
     * Finds all unique triplets in the array which give the sum of zero.
     *
     * @param arr the input array of integers
     * @return a list of lists, where each inner list contains three integers summing to 0
     */
    public static List<List<Integer>> findZeroSumTripletsBetter(int[] arr) {
        int n=arr.length;
        if(n<3)return new ArrayList<>();
        List<List<Integer>>ans=new ArrayList<>();
        Arrays.sort(arr);
        for(int i=0;i<n;i++){
            if(i>0&&arr[i-1]==arr[i])continue;;
            int j=i+1,k=n-1;
            while(j<k){
                int sum=arr[i]+arr[j]+arr[k];
                if(sum==0){
                    List<Integer>temp=new ArrayList<>();
                    temp.add(arr[i]);
                    temp.add(arr[j]);
                    temp.add(arr[k]);
                    ans.add(temp);
                    j++;k--;
                    while(j<k&&arr[j-1]==arr[j])j++;
                    while(j<k&&arr[k+1]==arr[k])k--;
                }else if(sum>0)k--;
                else j++;
            }
        }

        return ans; // Placeholder return
    }

    public static List<List<Integer>> findZeroSumTriplets(int[]arr){
        int n=arr.length;

        if(n<3)return new ArrayList<>();

        Arrays.sort(arr);
        List<List<Integer>> ans=new ArrayList<>();
        for(int i=0;i<n;i++){
            if(i>0&&arr[i]==arr[i-1])continue;
            int j=i+1,k=n-1;
            while(j<k){
                int sum=arr[i]+arr[j]+arr[k];
                if(sum==0){
                    List<Integer> temp=List.of(arr[i],arr[j],arr[k]);
                    ans.add(temp);
                    j++;
                    k--;
                    while(j<k&&arr[j]==arr[j-1])j++;
                    while(j<k&&arr[k]==arr[k+1])k--;
                }else if(sum<0){
                    j++;
                }else{
                    k--;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        // Test Case Structure: {input_array, expected_output_list}
        // All expected outputs are sorted internally and externally for reliable comparison.
        Object[][] testCases = {
                // Standard Examples
                {new int[]{-1, 0, 1, 2, -1, -4}, Arrays.asList(
                        Arrays.asList(-1, -1, 2),
                        Arrays.asList(-1, 0, 1))},
                {new int[]{0, 1, 1}, Arrays.asList()},
                {new int[]{0, 0, 0}, Arrays.asList(Arrays.asList(0, 0, 0))},

                // Edge: All Identical / Duplicates
                {new int[]{0, 0, 0, 0}, Arrays.asList(Arrays.asList(0, 0, 0))},
                {new int[]{-1, -1, -1, 2, 2}, Arrays.asList(Arrays.asList(-1, -1, 2))},
                {new int[]{-2, -2, 0, 0, 2, 2}, Arrays.asList(Arrays.asList(-2, 0, 2))},

                // Edge: No Possible Triplets
                {new int[]{-4, -2, 1, 3, 5}, Arrays.asList()},
                {new int[]{1, 2, -2, -1}, Arrays.asList()},

                // Mixed Signs & Symmetric Ranges
                {new int[]{-10, -5, 0, 5, 10}, Arrays.asList(
                        Arrays.asList(-10, 0, 10),
                        Arrays.asList(-5, 0, 5))},
                {new int[]{-3, -2, 0, 2, 3}, Arrays.asList(
                        Arrays.asList(-3, 0, 3),
                        Arrays.asList(-2, 0, 2))},

                // Hard / Multiple Valid Triplets
                {new int[]{-1, 2, 3, -4, -1, 2, 1, 0}, Arrays.asList(
                        Arrays.asList(-4, 1, 3),
                        Arrays.asList(-4, 2, 2),
                        Arrays.asList(-1, -1, 2),
                        Arrays.asList(-1, 0, 1))},
                {new int[]{-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5}, Arrays.asList(
                        Arrays.asList(-5, 0, 5), Arrays.asList(-5, 1, 4), Arrays.asList(-5, 2, 3),
                        Arrays.asList(-4, -1, 5), Arrays.asList(-4, 0, 4), Arrays.asList(-4, 1, 3),
                        Arrays.asList(-3, -2, 5), Arrays.asList(-3, -1, 4), Arrays.asList(-3, 0, 3),
                        Arrays.asList(-3, 1, 2), Arrays.asList(-2, -1, 3), Arrays.asList(-2, 0, 2),
                        Arrays.asList(-1, 0, 1))}
        };

        int passed = 0;
        int failed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int[] input = (int[]) testCases[i][0];
            @SuppressWarnings("unchecked")
            List<List<Integer>> expected = (List<List<Integer>>) testCases[i][1];

            List<List<Integer>> actual = findZeroSumTriplets(input);

            // Normalize both lists (sort inner triplets, sort outer list) for order-independent comparison
            List<List<Integer>> sortedExpected = normalizeTriplets(expected);
            List<List<Integer>> sortedActual = normalizeTriplets(actual);

            boolean isPassed = sortedExpected.equals(sortedActual);

            if (isPassed) {
                passed++;
                System.out.println("Test Case " + (i + 1) + ": PASSED");
            } else {
                failed++;
                System.out.println("Test Case " + (i + 1) + ": FAILED");
            }

            System.out.println("  Input   : " + Arrays.toString(input));
            System.out.println("  Expected: " + sortedExpected);
            System.out.println("  Actual  : " + sortedActual);
            System.out.println("-----------------------------------");
        }

        System.out.println("Total Passed: " + passed + "/" + testCases.length);
        System.out.println("Total Failed: " + failed + "/" + testCases.length);
    }

    /**
     * Helper to sort each triplet and the overall list of triplets.
     * Ensures comparison is independent of element order.
     */
    private static List<List<Integer>> normalizeTriplets(List<List<Integer>> list) {
        List<List<Integer>> normalized = new ArrayList<>();
        for (List<Integer> triplet : list) {
            List<Integer> sortedTriplet = new ArrayList<>(triplet);
            Collections.sort(sortedTriplet);
            normalized.add(sortedTriplet);
        }
        Collections.sort(normalized, (a, b) -> {
            if (!a.get(0).equals(b.get(0))) return a.get(0).compareTo(b.get(0));
            if (!a.get(1).equals(b.get(1))) return a.get(1).compareTo(b.get(1));
            return a.get(2).compareTo(b.get(2));
        });
        return normalized;
    }
}