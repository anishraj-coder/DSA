package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KWeakestRows {

    /**
     * Finds the indices of the k weakest rows in the matrix ordered from weakest to strongest.
     * * @param mat The m x n binary matrix of 1's (soldiers) and 0's (civilians).
     * @param k   The number of weakest rows to return.
     * @return    An array of indices of the k weakest rows.
     */
    public int[] kWeakestRows(int[][] arr, int k) {
        int n=arr.length;

        PriorityQueue<Pair>pq=new PriorityQueue<>((a,b)->{
           if(a.val!=b.val)return b.val-a.val;
           else return b.idx-a.idx;
        });
        for(int i=0;i<n;i++){
            int val=count(arr[i]);

            pq.offer(new Pair(i,val));
            if(pq.size()>k)pq.poll();
        }


        int[]ans=new int[k];
        for(int i=0;i<k;i++)ans[k-i-1]=pq.poll().idx;


        return ans;
    }

    private int count(int[]arr){
        int n=arr.length,low=0,hi=n-1;
        if(arr[0]==0)return 0;
        while(low<hi){
            int mid=(hi-low+1)/2+low;
            if(arr[mid]==1)low=mid;
            else hi=mid-1;
        }
        return low+1;
    }

    static class Pair{
        int idx,val;
        Pair(int idx,int val){
            this.idx=idx;
            this.val=val;
        }
    }

    public static void main(String[] args) {
        KWeakestRows processor = new KWeakestRows();
        int passed = 0;
        int total = 0;

        // --- TEST CASES ---

        // Test 1: Standard Example 1
        int[][] mat1 = {
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1}
        };
        int k1 = 3;
        int[] expected1 = {2, 0, 3};
        passed += runTestCase(++total, processor, mat1, k1, expected1);

        // Test 2: Standard Example 2 (Multiple rows with identical worker count)
        int[][] mat2 = {
                {1, 0, 0, 0},
                {1, 1, 1, 1},
                {1, 0, 0, 0},
                {1, 0, 0, 0}
        };
        int k2 = 2;
        int[] expected2 = {0, 2};
        passed += runTestCase(++total, processor, mat2, k2, expected2);

        // Test 3: Edge Case - All rows are completely empty (all 0s)
        int[][] mat3 = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        int k3 = 2;
        int[] expected3 = {0, 1}; // Tie broken by strict index sequence
        passed += runTestCase(++total, processor, mat3, k3, expected3);

        // Test 4: Edge Case - All rows are completely full (all 1s)
        int[][] mat4 = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        int k4 = 3;
        int[] expected4 = {0, 1, 2};
        passed += runTestCase(++total, processor, mat4, k4, expected4);

        // Test 5: Edge Case - Matrix size is minimal (2x2) and k = m
        int[][] mat5 = {
                {1, 1},
                {1, 0}
        };
        int k5 = 2;
        int[] expected5 = {1, 0};
        passed += runTestCase(++total, processor, mat5, k5, expected5);

        // Test 6: Edge Case - k = 1 (Only fetching the single weakest row)
        int[][] mat6 = {
                {1, 1, 1},
                {1, 0, 0},
                {1, 1, 0}
        };
        int k6 = 1;
        int[] expected6 = {1};
        passed += runTestCase(++total, processor, mat6, k6, expected6);

        // Test 7: Strictly increasing strength order
        int[][] mat7 = {
                {0, 0, 0},
                {1, 0, 0},
                {1, 1, 0},
                {1, 1, 1}
        };
        int k7 = 4;
        int[] expected7 = {0, 1, 2, 3};
        passed += runTestCase(++total, processor, mat7, k7, expected7);

        // Test 8: Strictly decreasing strength order (reverse sorted input)
        int[][] mat8 = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 0},
                {0, 0, 0}
        };
        int k8 = 3;
        int[] expected8 = {3, 2, 1};
        passed += runTestCase(++total, processor, mat8, k8, expected8);

        // Test 9: Alternating tie-breaking patterns
        int[][] mat9 = {
                {1, 1, 0}, // 2
                {1, 0, 0}, // 1
                {1, 1, 0}, // 2
                {1, 0, 0}  // 1
        };
        int k9 = 4;
        int[] expected9 = {1, 3, 0, 2};
        passed += runTestCase(++total, processor, mat9, k9, expected9);

        // Test 10: Single-column matrix (extreme aspect ratio)
        int[][] mat10 = {
                {1},
                {0},
                {1},
                {0}
        };
        int k10 = 2;
        int[] expected10 = {1, 3};
        passed += runTestCase(++total, processor, mat10, k10, expected10);

        // Test 11: Large input configuration with single row matching
        int[][] mat11 = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0},
                {1, 1, 1, 1, 1}
        };
        int k11 = 1;
        int[] expected11 = {2};
        passed += runTestCase(++total, processor, mat11, k11, expected11);


        // --- SUMMARY ---
        System.out.println("\n--------------------------------------------------");
        System.out.printf("Execution Done! Passed: %d / %d\n", passed, total);
        System.out.println("--------------------------------------------------");
    }

    private static int runTestCase(int testNum, KWeakestRows processor, int[][] mat, int k, int[] expected) {
        int[] actual = processor.kWeakestRows(mat, k);
        boolean isCorrect = Arrays.equals(actual, expected);

        System.out.printf("Test Case %2d: ", testNum);
        if (isCorrect) {
            System.out.println("[PASSED]");
        } else {
            System.out.println("[FAILED]");
            System.out.println("   Input Matrix: " + Arrays.deepToString(mat));
            System.out.println("   k: " + k);
            System.out.println("   Expected    : " + Arrays.toString(expected));
            System.out.println("   Actual      : " + Arrays.toString(actual));
        }
        return isCorrect ? 1 : 0;
    }
}