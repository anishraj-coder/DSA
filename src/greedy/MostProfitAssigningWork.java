package greedy;

import java.util.Arrays;

public class MostProfitAssigningWork {

    // Implement your solution here
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] arr) {
        int n=arr.length,m=difficulty.length;
        
        Arrays.sort(arr);
        reverse(arr);
        Pair[]pair=new Pair[m];
        for(int i=0;i<m;i++)pair[i]=new Pair(difficulty[i],profit[i]);
        Arrays.sort(pair);
        int i=0,j=0,total=0;
        while(i<n&&j<m) {
        	if(pair[j].diff<=arr[i]) {
        		total+=pair[j].profit;
        		i++;
        	}else j++;
        }
        
        return total;
    }
    
    private void reverse(int[]arr) {
    	int i=0,j=arr.length-1;
    	while(i<j) {
    		int t=arr[i];
    		arr[i]=arr[j];
    		arr[j]=t;
    		i++;j--;
    	}
    }
    
    private static class Pair  implements Comparable<Pair>{
    	public int diff,profit;
    	Pair(int diff,int profit){
    		this.diff=diff;
    		this.profit=profit;
    	}
    	
    	@Override
    	public int compareTo(Pair o) {
    		if(this.profit==o.profit)return o.diff-this.diff;
    		return o.profit-this.profit;
    	}
    }

    public static void main(String[] args) {
        MostProfitAssigningWork solver = new MostProfitAssigningWork();

        // Test Cases Definitions
        TestCase[] testCases = new TestCase[] {
            // Test 1: Example 1 from problem description
            new TestCase(
                new int[]{2, 4, 6, 8, 10},
                new int[]{10, 20, 30, 40, 50},
                new int[]{4, 5, 6, 7},
                100
            ),
            // Test 2: Example 2 (No worker can do any job)
            new TestCase(
                new int[]{85, 47, 57},
                new int[]{24, 66, 99},
                new int[]{40, 25, 25},
                0
            ),
            // Test 3: Unsorted difficulty & profit (Higher difficulty does NOT mean higher profit)
            new TestCase(
                new int[]{68, 35, 52, 47, 86},
                new int[]{67, 17, 1, 81, 25},
                new int[]{92, 10, 85, 84, 82},
                324
            ),
            // Test 4: Single job, multiple workers capable
            new TestCase(
                new int[]{10},
                new int[]{100},
                new int[]{10, 12, 15, 8},
                300
            ),
            // Test 5: All workers weaker than the easiest job
            new TestCase(
                new int[]{5, 10, 15},
                new int[]{50, 100, 150},
                new int[]{1, 2, 3, 4},
                0
            ),
            // Test 6: Single worker capable of doing highest profit job
            new TestCase(
                new int[]{10, 20, 30},
                new int[]{50, 100, 150},
                new int[]{30},
                150
            ),
            // Test 7: Duplicate difficulties with different profits
            new TestCase(
                new int[]{10, 10, 10, 20},
                new int[]{20, 80, 50, 60},
                new int[]{10, 15, 20},
                220
            ),
            // Test 8: Worker abilities out of order with large gap
            new TestCase(
                new int[]{1, 3, 5, 7, 9},
                new int[]{10, 20, 30, 40, 50},
                new int[]{100, 2, 8, 0},
                120
            ),
            // Test 9: Lower difficulty job gives strictly higher profit than higher difficulty job
            new TestCase(
                new int[]{5, 15, 25, 35},
                new int[]{100, 20, 30, 40},
                new int[]{10, 20, 30, 40},
                400
            ),
            // Test 10: Large inputs with max boundaries allowed
            new TestCase(
                new int[]{100000, 1, 50000},
                new int[]{100000, 2, 50000},
                new int[]{100000, 100000, 1},
                200002
            )
        };

        // Runner Engine
        int passed = 0;
        System.out.println("=================================================");
        System.out.println("          RUNNING TEST SUITE                     ");
        System.out.println("=================================================");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int actual = solver.maxProfitAssignment(
                tc.difficulty.clone(), 
                tc.profit.clone(), 
                tc.worker.clone()
            );
            
            boolean isCorrect = actual == tc.expected;
            if (isCorrect) passed++;

            System.out.printf("Test %2d: %s\n", i + 1, isCorrect ? "PASSED [✓]" : "FAILED [✗]");
            System.out.printf("   Expected Output : %d\n", tc.expected);
            System.out.printf("   Actual Output   : %d\n", actual);
            System.out.println("-------------------------------------------------");
        }

        System.out.printf("RESULTS: %d/%d Test Cases Passed.\n", passed, testCases.length);
        System.out.println("=================================================");
    }

    // Helper class to encapsulate test inputs and outputs
    private static class TestCase {
        int[] difficulty;
        int[] profit;
        int[] worker;
        int expected;

        TestCase(int[] difficulty, int[] profit, int[] worker, int expected) {
            this.difficulty = difficulty;
            this.profit = profit;
            this.worker = worker;
            this.expected = expected;
        }
    }
}