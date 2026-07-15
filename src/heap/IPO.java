package heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.PriorityQueue;

public class IPO {

    /**
     * Finds the maximized final capital after completing at most k projects.
     * * @param k       The maximum number of distinct projects allowed.
     * @param w       The initial capital.
     * @param profits Array containing the pure profit of each project.
     * @param capital Array containing the minimum capital needed to start each project.
     * @return The maximum total capital achievable.
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n=capital.length;
        Pair[]arr=new Pair[n];
        for(int i=0;i<n;i++)arr[i]=new Pair(capital[i],profits[i]);
        Arrays.sort(arr,(a,b)->{
           if(a.capital!=b.capital)return a.capital-b.capital;
           else return a.profit-b.profit;
        });
        PriorityQueue<Integer>pq=new PriorityQueue<>(Collections.reverseOrder());
        int idx=0;
        for(int i=0;i<k;i++){
            while(idx<n){
                if(arr[idx].capital>w)break;
                pq.offer(arr[idx].profit);
                idx++;
            }
            if(pq.isEmpty())return w;
            w+=pq.poll();
        }
        return w;
    }

    static class Pair{
        int profit,capital;
        Pair(int capital,int profit){
            this.capital=capital;
            this.profit=profit;
        }
    }

    public static void main(String[] args) {
        IPO ipo = new IPO();
        int passed = 0;
        int total = 10;

        // --- Test Case 1: Standard Case (Example 1) ---
        int[] p1 = {1, 2, 3};
        int[] c1 = {0, 1, 1};
        int res1 = ipo.findMaximizedCapital(2, 0, p1, c1);
        passed += verify(1, 4, res1);

        // --- Test Case 2: Standard Case (Example 2) ---
        int[] p2 = {1, 2, 3};
        int[] c2 = {0, 1, 2};
        int res2 = ipo.findMaximizedCapital(3, 0, p2, c2);
        passed += verify(2, 6, res2);

        // --- Test Case 3: Initial capital cannot afford any project ---
        int[] p3 = {1, 2, 3};
        int[] c3 = {5, 10, 15};
        int res3 = ipo.findMaximizedCapital(3, 2, p3, c3);
        passed += verify(3, 2, res3);

        // --- Test Case 4: K is larger than the number of available projects ---
        int[] p4 = {5, 10};
        int[] c4 = {0, 1};
        int res4 = ipo.findMaximizedCapital(10, 0, p4, c4);
        passed += verify(4, 15, res4);

        // --- Test Case 5: All projects require 0 capital ---
        int[] p5 = {1, 5, 2, 9};
        int[] c5 = {0, 0, 0, 0};
        int res5 = ipo.findMaximizedCapital(2, 3, p5, c5);
        passed += verify(5, 17, res5); // Top 2 profits are 9 and 5 -> 3 + 9 + 5 = 17

        // --- Test Case 6: Duplicate profits and capitals ---
        int[] p6 = {2, 2, 3, 3};
        int[] c6 = {0, 0, 1, 1};
        int res6 = ipo.findMaximizedCapital(3, 0, p6, c6);
        passed += verify(6, 8, res6); // Order: 2(c=0)->w=2, then 3(c=1)->w=5, then 3(c=1)->w=8

        // --- Test Case 7: Some projects have 0 profit ---
        int[] p7 = {0, 0, 5};
        int[] c7 = {0, 0, 0};
        int res7 = ipo.findMaximizedCapital(2, 1, p7, c7);
        passed += verify(7, 6, res7);

        // --- Test Case 8: Large Capital Requirements (Chain Reaction) ---
        int[] p8 = {10, 20, 30};
        int[] c8 = {0, 10, 30};
        int res8 = ipo.findMaximizedCapital(3, 0, p8, c8);
        passed += verify(8, 60, res8); // 0->10, 10 satisfies 10->30, 30 satisfies 30->60

        // --- Test Case 9: Capital increases but cannot buy the most profitable remaining option ---
        int[] p9 = {1, 10, 100};
        int[] c9 = {0, 1, 20};
        int res9 = ipo.findMaximizedCapital(2, 0, p9, c9);
        passed += verify(9, 11, res9); // 0->1(c=0), 1->11(c=1), can't afford 100 because max capital reaches 11 but needs 20

        // --- Test Case 10: K is 0 (Cannot take any projects) ---
        int[] p10 = {5, 6, 7};
        int[] c10 = {0, 0, 0};
        int res10 = ipo.findMaximizedCapital(0, 10, p10, c10);
        passed += verify(10, 10, res10);

        System.out.println("\n-------------------------------------------");
        System.out.println("Final Results: " + passed + " / " + total + " Test Cases Passed.");
        System.out.println("-------------------------------------------");
    }

    private static int verify(int testCaseNum, int expected, int actual) {
        boolean isCorrect = expected == actual;
        System.out.printf("Test Case %-2d: %s | Expected: %-5d | Actual: %-5d\n",
                testCaseNum, (isCorrect ? "PASSED" : "FAILED"), expected, actual);
        return isCorrect ? 1 : 0;
    }
}