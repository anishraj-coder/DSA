package heap;

import java.util.*;

public class Skyline {

    /**
     * Fills the skyline outline based on building coordinates.
     * Implement your solution here.
     *
     * @param arr int[][] where buildings[i] = [left, right, height]
     * @return List<List<Integer>> representing the key points [[x1, y1], [x2, y2], ...]
     */
    public List<List<Integer>> getSkyline(int[][] arr) {

        List<Pair>list=new ArrayList<>();
        for(int[]a:arr){
            int sp=a[0],ep=a[1],height=a[2];
            list.add(new Pair(sp,-height));
            list.add(new Pair(ep,height));
        }
        Collections.sort(list);
        PriorityQueue<Integer>pq=new PriorityQueue<>(Collections.reverseOrder());
        List<List<Integer>>ans=new ArrayList<>();
        pq.add(0);
        int prev=0;
        HashMap<Integer,Integer>map=new HashMap<>();
        for(int i=0;i<list.size();i++){
            Pair curr=list.get(i);
            if(curr.height<0){
                pq.offer(-curr.height);
            }else map.put(curr.height,map.getOrDefault(curr.height,0)+1);

            while(!pq.isEmpty()&&map.containsKey(pq.peek())){
                int rem=pq.poll();
                map.put(rem,map.get(rem)-1);
                if(map.get(rem)==0)map.remove(rem);
            }

            if(prev!=pq.peek()){
                List<Integer>temp=new ArrayList<>();
                temp.add(curr.pos);
                temp.add(pq.peek());
                prev=pq.peek();
                ans.add(temp);
            }
        }

        return ans;
    }

    static class Pair implements Comparable<Pair>{
        int pos,height;
        Pair(int pos,int height){
            this.pos=pos;
            this.height=height;
        }
        @Override
        public int compareTo(Pair o){
            if(this.pos!=o.pos)return this.pos-o.pos;
            return this.height-o.height;
        }
    }

    public static void main(String[] args) {
        Skyline solver = new Skyline();
        int passed = 0;
        int total = 10;

        // --- TEST CASE 1: Standard overlapping buildings (Example 1) ---
        int[][] b1 = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        List<List<Integer>> exp1 = createExpected(new int[][]{{2, 10}, {3, 15}, {7, 12}, {12, 0}, {15, 10}, {20, 8}, {24, 0}});
        passed += runTest(1, exp1, solver.getSkyline(b1));

        // --- TEST CASE 2: Continuous buildings of same height (Example 2) ---
        int[][] b2 = {{0, 2, 3}, {2, 5, 3}};
        List<List<Integer>> exp2 = createExpected(new int[][]{{0, 3}, {5, 0}});
        passed += runTest(2, exp2, solver.getSkyline(b2));

        // --- TEST CASE 3: Single building ---
        int[][] b3 = {{1, 5, 10}};
        List<List<Integer>> exp3 = createExpected(new int[][]{{1, 10}, {5, 0}});
        passed += runTest(3, exp3, solver.getSkyline(b3));

        // --- TEST CASE 4: Disjoint/Separated buildings ---
        int[][] b4 = {{1, 3, 5}, {5, 8, 10}};
        List<List<Integer>> exp4 = createExpected(new int[][]{{1, 5}, {3, 0}, {5, 10}, {8, 0}});
        passed += runTest(4, exp4, solver.getSkyline(b4));

        // --- TEST CASE 5: One building completely contained inside a taller one ---
        int[][] b5 = {{2, 8, 5}, {3, 6, 12}};
        List<List<Integer>> exp5 = createExpected(new int[][]{{2, 5}, {3, 12}, {6, 5}, {8, 0}});
        passed += runTest(5, exp5, solver.getSkyline(b5));

        // --- TEST CASE 6: One building completely contained inside a shorter one (Hidden) ---
        int[][] b6 = {{2, 8, 12}, {4, 6, 5}};
        List<List<Integer>> exp6 = createExpected(new int[][]{{2, 12}, {8, 0}});
        passed += runTest(6, exp6, solver.getSkyline(b6));

        // --- TEST CASE 7: Multiple buildings sharing the same starting point ---
        int[][] b7 = {{2, 5, 8}, {2, 7, 15}, {2, 9, 10}};
        List<List<Integer>> exp7 = createExpected(new int[][]{{2, 15}, {7, 10}, {9, 0}});
        passed += runTest(7, exp7, solver.getSkyline(b7));

        // --- TEST CASE 8: Multiple buildings sharing the same ending point ---
        int[][] b8 = {{2, 9, 10}, {4, 9, 15}, {6, 9, 8}};
        List<List<Integer>> exp8 = createExpected(new int[][]{{2, 10}, {4, 15}, {9, 0}});
        passed += runTest(8, exp8, solver.getSkyline(b8));

        // --- TEST CASE 9: Stepping stones / Cascade effect (ascending and descending) ---
        int[][] b9 = {{1, 6, 4}, {2, 5, 8}, {3, 4, 12}};
        List<List<Integer>> exp9 = createExpected(new int[][]{{1, 4}, {2, 8}, {3, 12}, {4, 8}, {5, 4}, {6, 0}});
        passed += runTest(9, exp9, solver.getSkyline(b9));

        // --- TEST CASE 10: MAX_VALUE Boundaries & Large Scale Gap ---
        int[][] b10 = {{0, 2147483647, 5}, {2147483646, 2147483647, 2147483647}};
        List<List<Integer>> exp10 = createExpected(new int[][]{{0, 5}, {2147483646, 2147483647}, {2147483647, 0}});
        passed += runTest(10, exp10, solver.getSkyline(b10));

        System.out.println("\n==================================");
        System.out.println("Final Results: " + passed + " / " + total + " Passed");
        System.out.println("==================================");
    }

    private static List<List<Integer>> createExpected(int[][] arr) {
        List<List<Integer>> list = new ArrayList<>();
        for (int[] p : arr) {
            list.add(Arrays.asList(p[0], p[1]));
        }
        return list;
    }

    private static int runTest(int id, List<List<Integer>> expected, List<List<Integer>> actual) {
        boolean isMatch = Objects.equals(expected, actual);
        System.out.println("Test Case " + id + ": " + (isMatch ? "PASSED" : "FAILED"));
        if (!isMatch) {
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual:   " + actual);
        }
        return isMatch ? 1 : 0;
    }
}