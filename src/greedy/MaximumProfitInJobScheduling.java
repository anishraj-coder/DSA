package greedy;

import java.util.Arrays;

public class MaximumProfitInJobScheduling {

    /**
     * Finds the maximum profit you can earn with non-overlapping jobs.
     *
     * @param startTime Array of job start times.
     * @param endTime   Array of job end times.
     * @param profit    Array of job profits.
     * @return The maximum total profit.
     */
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n=startTime.length;
        int[]dp=new int[n+1];
        Arrays.fill(dp, -1);
        
        Pair []arr=new Pair[n];
        for(int i=0;i<n;i++)arr[i]=new Pair(i,startTime[i],endTime[i],profit[i]);
        
        Arrays.sort(arr);
        
        return helper(arr,0,dp);
    }
    
    private int helper(Pair[]arr,int i,int[]dp) {
    	int n=arr.length;
    	if(i==n)return 0;
    	if(dp[i]!=-1)return dp[i];
    	int next=lowerBound(arr,i);
    	int take=helper(arr,next,dp)+arr[i].profit;
    	int not=helper(arr,i+1,dp);
    	return Math.max(take, not);
    }
    
    private int lowerBound(Pair[]arr,int target) {
    	int n=arr.length;
    	int low=target+1,hi=n;
    	while(low<hi) {
    		int mid=(hi-low)/2+low;
    		if(arr[mid].start>=arr[target].end)hi=mid;
    		else low=mid+1;
    	}
    	return low;
    }
    
    private static class Pair implements Comparable<Pair>{
    	int id,start,end,profit;
    	Pair(int id,int start,int end,int profit){
    		this.id=id;
    		this.start=start;
    		this.end=end;
    		this.profit=profit;
    	}
    	@Override
    	public int compareTo(Pair o) {
    		return Integer.compare(this.start, o.start);
    	}
    }

    public static void main(String[] args) {
        MaximumProfitInJobScheduling target = new MaximumProfitInJobScheduling();
        int passed = 0;
        int total = 10;

        // Test Case 1: Standard Example 1
        int[] s1 = {1, 2, 3, 3};
        int[] e1 = {3, 4, 5, 6};
        int[] p1 = {50, 10, 40, 70};
        if (checkTest("Test Case 1", target.jobScheduling(s1, e1, p1), 120)) passed++;

        // Test Case 2: Standard Example 2
        int[] s2 = {1, 2, 3, 4, 6};
        int[] e2 = {3, 5, 10, 6, 9};
        int[] p2 = {20, 20, 100, 70, 60};
        if (checkTest("Test Case 2", target.jobScheduling(s2, e2, p2), 150)) passed++;

        // Test Case 3: Standard Example 3
        int[] s3 = {1, 1, 1};
        int[] e3 = {2, 3, 4};
        int[] p3 = {5, 6, 4};
        if (checkTest("Test Case 3", target.jobScheduling(s3, e3, p3), 6)) passed++;

        // Test Case 4: Single Job
        int[] s4 = {5};
        int[] e4 = {10};
        int[] p4 = {100};
        if (checkTest("Test Case 4", target.jobScheduling(s4, e4, p4), 100)) passed++;

        // Test Case 5: Sequential non-overlapping jobs (Take all)
        int[] s5 = {1, 3, 5, 7};
        int[] e5 = {3, 5, 7, 9};
        int[] p5 = {10, 20, 30, 40};
        if (checkTest("Test Case 5", target.jobScheduling(s5, e5, p5), 100)) passed++;

        // Test Case 6: All jobs completely overlap (Must pick the single maximum profit)
        int[] s6 = {1, 1, 1, 1};
        int[] e6 = {5, 5, 5, 5};
        int[] p6 = {10, 50, 30, 20};
        if (checkTest("Test Case 6", target.jobScheduling(s6, e6, p6), 50)) passed++;

        // Test Case 7: Back-to-back exact boundary jobs (End time X == Start time X)
        int[] s7 = {1, 2, 3, 4};
        int[] e7 = {2, 3, 4, 5};
        int[] p7 = {10, 10, 10, 10};
        if (checkTest("Test Case 7", target.jobScheduling(s7, e7, p7), 40)) passed++;

        // Test Case 8: Long lower-profit job vs. multiple short higher-profit jobs
        int[] s8 = {1, 1, 3, 5};
        int[] e8 = {7, 3, 5, 7};
        int[] p8 = {50, 20, 25, 30};
        if (checkTest("Test Case 8", target.jobScheduling(s8, e8, p8), 75)) passed++;

        // Test Case 9: Unsorted input order (Jobs given out of chronological order)
        int[] s9 = {4, 1, 6, 2};
        int[] e9 = {6, 3, 9, 5};
        int[] p9 = {70, 20, 60, 20};
        if (checkTest("Test Case 9", target.jobScheduling(s9, e9, p9), 150)) passed++;

        // Test Case 10: Large gaps between job times
        int[] s10 = {10, 1000, 100000};
        int[] e10 = {20, 2000, 200000};
        int[] p10 = {500, 600, 700};
        if (checkTest("Test Case 10", target.jobScheduling(s10, e10, p10), 1800)) passed++;

        System.out.println("\n-------------------------------------------");
        System.out.println("Final Result: " + passed + "/" + total + " Test Cases Passed.");
        System.out.println("-------------------------------------------");
    }

    private static boolean checkTest(String testName, int actual, int expected) {
        boolean pass = actual == expected;
        if (pass) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL | Expected: " + expected + ", Got: " + actual);
        }
        return pass;
    }
}