package heap;

import java.util.*;

public class TaskSchedulerIntervals {

    // TO BE IMPLEMENTED BY YOU
    public int leastInterval(char[] arr, int n) {
        PriorityQueue<Pair>pq=new PriorityQueue<>((a,b)->{
           if(a.freq!=b.freq)return b.freq-a.freq;
           else return a.valid-b.valid;
        });

        int[]freq=new int[26];
        for(char ch:arr){
            freq[ch-'A']++;
        }
        for(int i=0;i<arr.length;i++){
            char ch=(char)(i+'A');
            if(freq[i]>0)pq.offer(new Pair(ch,freq[i]));
        }

        int curr=0;
        Queue<Pair> queue=new LinkedList<>();
        while(!pq.isEmpty()||!queue.isEmpty()){
            while(!queue.isEmpty()&&queue.peek().valid<=curr){
                pq.offer(queue.poll());
            }

            while(!pq.isEmpty()){
                Pair res=pq.poll();
                if(res.valid<=curr){
                    res.freq--;
                    res.valid=curr+n+1;
                    if(res.freq>0)pq.offer(res);
                    break;
                }else{
                    queue.offer(res);
                }
            }
            curr++;
        }

        return curr;
    }

    static class Pair{
        char ch;
        int freq,valid;
        Pair(char ch,int freq){
            this.ch=ch;
            this.freq=freq;
            this.valid=-1;
        }
        Pair(char ch,int freq,int valid){
            this(ch,freq);
            this.valid=valid;
        }
    }

    public static void main(String[] args) {
        TaskSchedulerIntervals scheduler = new TaskSchedulerIntervals();
        int passed = 0;
        int totalTests = 11;

        // Test Case 1: Standard case with idle slots (Example 1)
        char[] tasks1 = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n1 = 2;
        int expected1 = 8;
        passed += runTest(1, scheduler.leastInterval(tasks1, n1), expected1, tasks1, n1);

        // Test Case 2: No idle slots needed due to variety (Example 2)
        char[] tasks2 = {'A', 'C', 'A', 'B', 'D', 'B'};
        int n2 = 1;
        int expected2 = 6;
        passed += runTest(2, scheduler.leastInterval(tasks2, n2), expected2, tasks2, n2);

        // Test Case 3: High cooling period, lots of idle slots (Example 3)
        char[] tasks3 = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n3 = 3;
        int expected3 = 10;
        passed += runTest(3, scheduler.leastInterval(tasks3, n3), expected3, tasks3, n3);

        // Test Case 4: Edge case - Cooldown is 0 (No idling ever needed)
        char[] tasks4 = {'A', 'A', 'B', 'C', 'C', 'D'};
        int n4 = 0;
        int expected4 = 6;
        passed += runTest(4, scheduler.leastInterval(tasks4, n4), expected4, tasks4, n4);

        // Test Case 5: Edge case - Single task
        char[] tasks5 = {'A'};
        int n5 = 50;
        int expected5 = 1;
        passed += runTest(5, scheduler.leastInterval(tasks5, n5), expected5, tasks5, n5);

        // Test Case 6: Edge case - All tasks are identical
        char[] tasks6 = {'A', 'A', 'A', 'A'};
        int n6 = 2;
        int expected6 = 10; // A -> idle -> idle -> A -> idle -> idle -> A -> idle -> idle -> A
        passed += runTest(6, scheduler.leastInterval(tasks6, n6), expected6, tasks6, n6);

        // Test Case 7: Complex case - Multiple tasks sharing the maximum frequency
        char[] tasks7 = {'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'D', 'D'};
        int n7 = 2;
        int expected7 = 11; // A, B, C dominate: A->B->C->A->B->C->A->B->C->D->D is 11 tasks total, fits perfectly
        passed += runTest(7, scheduler.leastInterval(tasks7, n7), expected7, tasks7, n7);

        // Test Case 8: Complex case - High task variety overrides the cooling limitation
        char[] tasks8 = {'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int n8 = 2;
        int expected8 = 8; // 'A' can be separated easily by other distinct tasks
        passed += runTest(8, scheduler.leastInterval(tasks8, n8), expected8, tasks8, n8);

        // Test Case 9: Extreme case - Large n relative to task size
        char[] tasks9 = {'A', 'B', 'A'};
        int n9 = 100;
        int expected9 = 102; // A -> B -> (99 idles) -> A => 1 + 1 + 99 + 1 = 102
        passed += runTest(9, scheduler.leastInterval(tasks9, n9), expected9, tasks9, n9);

        // Test Case 10: Tail-end constraint - Lower frequency tasks filling the gaps safely
        char[] tasks10 = {'A', 'A', 'A', 'B', 'B', 'C'};
        int n10 = 3;
        int expected10 = 9; // A -> B -> C -> idle -> A -> B -> idle -> idle -> A
        passed += runTest(10, scheduler.leastInterval(tasks10, n10), expected10, tasks10, n10);

        // Test Case 11: Large input with mixed frequencies
        char[] tasks11 = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int n11 = 2;
        int expected11 = 16; // A max frequency = 6. Pattern: A -> X -> Y -> A -> ... (6 blocks) -> Last A requires no trailing idles.
        passed += runTest(11, scheduler.leastInterval(tasks11, n11), expected11, tasks11, n11);

        System.out.println("\n--------------------------------------------");
        System.out.println("Final Result: " + passed + " / " + totalTests + " Test Cases Passed.");
        System.out.println("--------------------------------------------");
    }

    private static int runTest(int id, int actual, int expected, char[] tasks, int n) {
        boolean isSuccess = (actual == expected);
        System.out.printf("Test Case %2d: %s\n", id, isSuccess ? "PASSED" : "FAILED");
        if (!isSuccess) {
            System.out.println("  Inputs: tasks = " + Arrays.toString(tasks) + ", n = " + n);
            System.out.println("  Expected Output : " + expected);
            System.out.println("  Actual Output   : " + actual);
        }
        return isSuccess ? 1 : 0;
    }
}