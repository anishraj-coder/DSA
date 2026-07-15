package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {

    /**
     * Your task is to implement this method.
     * * Given an array of points where points[i] = [xi, yi] and an integer k,
     * return the k closest points to the origin (0, 0).
     */
    public int[][] kClosest(int[][] arr, int k) {

        int n=arr.length;
        if(k>=n)return arr;

        PriorityQueue<int[]>pq=new PriorityQueue<>((a,b)->{
            int x1=a[0],y1=a[1],x2=b[0],y2=b[1];
            int l1=x1*x1+y1*y1,l2=x2*x2+y2*y2;
            return l2-l1;
        });

        for(int i=0;i<n;i++){
            pq.offer(arr[i]);
            if(pq.size()>k){
                pq.poll();
            }
        }
        n=pq.size();
        int[][]ans=new int[n][2];
        for(int i=0;i<n;i++){
            ans[i]=pq.poll();
        }
        return ans;
    }

    public static void main(String[] args) {
        KClosestPointsToOrigin solver = new KClosestPointsToOrigin();
        int totalPassed = 0;
        int totalTests = 11;

        // --- TEST CASES ---

        // Test 1: Example 1
        int[][] p1 = {{1, 3}, {-2, 2}};
        int k1 = 1;
        int[][] e1 = {{-2, 2}};

        // Test 2: Example 2
        int[][] p2 = {{3, 3}, {5, -1}, {-2, 4}};
        int k2 = 2;
        int[][] e2 = {{3, 3}, {-2, 4}};

        // Test 3: Points on the axes with identical distances
        int[][] p3 = {{0, 5}, {5, 0}, {-5, 0}, {0, -5}, {3, 4}};
        int k3 = 3;
        int[][] e3 = {{0, 5}, {5, 0}, {-5, 0}}; // All have squared distance 25, any 3 acceptable.

        // Test 4: Origin point included (closest possible)
        int[][] p4 = {{1, 1}, {0, 0}, {-1, -1}};
        int k4 = 1;
        int[][] e4 = {{0, 0}};

        // Test 5: k equals total number of points
        int[][] p5 = {{2, 3}, {1, 1}};
        int k5 = 2;
        int[][] e5 = {{1, 1}, {2, 3}};

        // Test 6: Points with very large coordinates (checking for integer overflow in distance calculation)
        int[][] p6 = {{10000, 10000}, {0, 1}, {9999, 9999}};
        int k6 = 1;
        int[][] e6 = {{0, 1}};

        // Test 7: Duplicate identical points in input
        int[][] p7 = {{1, 2}, {1, 2}, {3, 4}};
        int k7 = 2;
        int[][] e7 = {{1, 2}, {1, 2}};

        // Test 8: All negative coordinates
        int[][] p8 = {{-1, -3}, {-4, -5}, {-2, -1}};
        int k8 = 2;
        int[][] e8 = {{-2, -1}, {-1, -3}};

        // Test 9: Collinear points radiating outwards
        int[][] p9 = {{1, 1}, {3, 3}, {2, 2}, {4, 4}};
        int k9 = 2;
        int[][] e9 = {{1, 1}, {2, 2}};

        // Test 10: Single point array
        int[][] p10 = {{7, 8}};
        int k10 = 1;
        int[][] e10 = {{7, 8}};

        // Test 11: Subtle distance differences (sqrt(50) vs sqrt(52) vs sqrt(45))
        // (-5,-5)->50, (4,6)->52, (-3,6)->45
        int[][] p11 = {{-5, -5}, {4, 6}, {-3, 6}};
        int k11 = 2;
        int[][] e11 = {{-3, 6}, {-5, -5}};


        // --- AUTOMATED EVALUATION ---

        int[][][] allPoints = {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11};
        int[] allK = {k1, k2, k3, k4, k5, k6, k7, k8, k9, k10, k11};
        int[][][] allExpected = {e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11};

        for (int i = 0; i < totalTests; i++) {
            System.out.println("----------------------------------------");
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input Points: " + Arrays.deepToString(allPoints[i]) + ", K = " + allK[i]);

            // Execute user logic
            int[][] actual = solver.kClosest(deepCopy(allPoints[i]), allK[i]);

            System.out.println("Expected Output: " + Arrays.deepToString(allExpected[i]));
            System.out.println("Actual Output:   " + Arrays.deepToString(actual));

            if (validateOutput(actual, allExpected[i], allK[i])) {
                System.out.println("Result:          PASSED");
                totalPassed++;
            } else {
                System.out.println("Result:          FAILED");
            }
        }

        System.out.println("----------------------------------------");
        System.out.println("Final Verdict: " + totalPassed + " / " + totalTests + " Test Cases Passed.");
    }

    // Deep copy helper to keep inputs pristine
    private static int[][] deepCopy(int[][] original) {
        if (original == null) return null;
        int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = original[i].clone();
        }
        return result;
    }

    // Custom validator since output order does not matter, but point contents do.
    private static boolean validateOutput(int[][] actual, int[][] expected, int k) {
        if (actual == null || actual.length != k) {
            return false;
        }
        for (int[] point : actual) {
            if (point == null || point.length != 2) return false;
        }

        // Sort both arrays canonical order (by X first, then by Y) to easily cross-compare
        int[][] actualSorted = deepCopy(actual);
        int[][] expectedSorted = deepCopy(expected);

        autoSortPoints(actualSorted);
        autoSortPoints(expectedSorted);

        return Arrays.deepEquals(actualSorted, expectedSorted);
    }

    private static void autoSortPoints(int[][] pts) {
        Arrays.sort(pts, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
    }
}