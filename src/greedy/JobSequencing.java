package greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;

public class JobSequencing {

    /**
     * Finds the maximum number of jobs completed and maximum profit earned.
     *
     * @param deadline Array of job deadlines.
     * @param profit   Array of job profits.
     * @return An array of size 2 where index 0 is max jobs and index 1 is max profit.
     */
    public int[] JobScheduling(int[] deadline, int[] profit) {
        
    	int n=deadline.length;
    	ArrayList<Pair> list=new ArrayList<>();
    	int max=-1,count=0,totalProfit=0;
    	for(int i=0;i<n;i++) {
    		list.add(new Pair(i,deadline[i],profit[i]));
    		max=Math.max(max,deadline[i]);
    	}
    	
    	Collections.sort(list);
    	
    	int[]hash=new int[max+1];
    	Arrays.fill(hash, -1);
    	for(int i=0;i<n;i++) {
    		for(int j=list.get(i).deadline;j>0;j--) {
    			if(hash[j]==-1) {
    				hash[j]=list.get(i).id;
    				count++;
    				totalProfit+=list.get(i).profit;
    				break;
    			}
    		}
    	}
        return new int[]{count, totalProfit};
    }
    
    private static class Pair implements Comparable<Pair>{
    	int id,profit,deadline;
    	
    	Pair(int id,int deadline,int profit){
    		this.id=id;
    		this.deadline=deadline;
    		this.profit=profit;
    	}
    	
    	@Override
    	public int compareTo(Pair o) {
    		if(this.profit==o.profit)return o.deadline-this.deadline;
    		return o.profit-this.profit;
    	}
    }

    public static void main(String[] args) {
        JobSequencing jobSequencing = new JobSequencing();
        int passed = 0;
        int total = 10;

        // Test Case 1: Standard Example 1
        int[] d1 = {4, 1, 1, 1};
        int[] p1 = {20, 10, 40, 30};
        int[] expected1 = {2, 60};
        if (checkTest("Test Case 1", jobSequencing.JobScheduling(d1, p1), expected1)) passed++;

        // Test Case 2: Standard Example 2
        int[] d2 = {2, 1, 2, 1, 1};
        int[] p2 = {100, 19, 27, 25, 15};
        int[] expected2 = {2, 127};
        if (checkTest("Test Case 2", jobSequencing.JobScheduling(d2, p2), expected2)) passed++;

        // Test Case 3: Standard Example 3
        int[] d3 = {3, 1, 2, 2};
        int[] p3 = {50, 10, 20, 30};
        int[] expected3 = {3, 100};
        if (checkTest("Test Case 3", jobSequencing.JobScheduling(d3, p3), expected3)) passed++;

        // Test Case 4: Single Job
        int[] d4 = {1};
        int[] p4 = {50};
        int[] expected4 = {1, 50};
        if (checkTest("Test Case 4", jobSequencing.JobScheduling(d4, p4), expected4)) passed++;

        // Test Case 5: All jobs have deadline 1 (Can only pick the single max profit job)
        int[] d5 = {1, 1, 1, 1};
        int[] p5 = {10, 50, 20, 40};
        int[] expected5 = {1, 50};
        if (checkTest("Test Case 5", jobSequencing.JobScheduling(d5, p5), expected5)) passed++;

        // Test Case 6: Strictly increasing deadlines (All jobs can be completed)
        int[] d6 = {1, 2, 3, 4};
        int[] p6 = {10, 20, 30, 40};
        int[] expected6 = {4, 100};
        if (checkTest("Test Case 6", jobSequencing.JobScheduling(d6, p6), expected6)) passed++;

        // Test Case 7: High profit jobs with late deadlines, low profit with early deadlines
        int[] d7 = {2, 1, 3};
        int[] p7 = {10, 20, 30};
        int[] expected7 = {3, 60};
        if (checkTest("Test Case 7", jobSequencing.JobScheduling(d7, p7), expected7)) passed++;

        // Test Case 8: Duplicate profits and deadlines
        int[] d8 = {2, 2, 2, 2};
        int[] p8 = {50, 50, 50, 50};
        int[] expected8 = {2, 100};
        if (checkTest("Test Case 8", jobSequencing.JobScheduling(d8, p8), expected8)) passed++;

        // Test Case 9: Deadlines larger than total job count
        int[] d9 = {5, 6, 7};
        int[] p9 = {10, 20, 30};
        int[] expected9 = {3, 60};
        if (checkTest("Test Case 9", jobSequencing.JobScheduling(d9, p9), expected9)) passed++;

        // Test Case 10: Mixed tight deadlines forcing profit choices
        int[] d10 = {1, 2, 2, 3, 1};
        int[] p10 = {100, 19, 27, 25, 15};
        int[] expected10 = {3, 152};
        if (checkTest("Test Case 10", jobSequencing.JobScheduling(d10, p10), expected10)) passed++;

        System.out.println("\n-------------------------------------------");
        System.out.println("Final Result: " + passed + "/" + total + " Test Cases Passed.");
        System.out.println("-------------------------------------------");
    }

    private static boolean checkTest(String testName, int[] actual, int[] expected) {
        boolean pass = Arrays.equals(actual, expected);
        if (pass) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL | Expected: " + Arrays.toString(expected) + ", Got: " + Arrays.toString(actual));
        }
        return pass;
    }
}