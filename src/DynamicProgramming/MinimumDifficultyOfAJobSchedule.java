package DynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

public class MinimumDifficultyOfAJobSchedule {

    // =========================================================================
    // APPROACH 1: Top-Down Dynamic Programming (Recursion + Memoization)
    // =========================================================================
    public int minDifficultyTopDown(int[] arr, int d) {
        int n=arr.length;
        if(n<d)return -1;
        int[][]dp=new int[n][d+1];
        
        for(int[]a:dp)Arrays.fill(a, -1);
        
        return helper(arr,0,d,dp);
    }
    
    private int helper(int[]arr,int i,int d,int[][]dp) {
    	int n=arr.length;
    	if(d==1) {
    		int ans=Integer.MIN_VALUE;
    		for(int j=i;j<n;j++)ans=Math.max(ans, arr[j]);
    		return ans;
    	}
    	if(dp[i][d]!=-1)return dp[i][d]; 
    	int max=Integer.MIN_VALUE,ans=Integer.MAX_VALUE;
    	for(int j=i;j<=n-d;j++) {
    		max=Math.max(arr[j],max );
    		ans=Math.min(ans, max+helper(arr,j+1,d-1,dp));
    	}
    	
    	return dp[i][d]= ans;
    }

    // =========================================================================
    // APPROACH 2: Bottom-Up Dynamic Programming (Tabulation)
    // =========================================================================
    public int minDifficultyBottomUp(int[] arr, int d) {
        int n=arr.length;
        if(n<d)return -1;
        
        int[]prev=new int[n+1];
        Arrays.fill(prev, Integer.MAX_VALUE);

        prev[n]=0;
        
        for(int dayrem=1;dayrem<=d;dayrem++) {
        	int[]curr=new int[n+1];
        	Arrays.fill(curr, Integer.MAX_VALUE);
        	for(int i=0;i<=n-dayrem;i++) {
        		int max=Integer.MIN_VALUE;
        		for(int j=i;j<=n-dayrem;j++) {
        			max=Math.max(max, arr[j]);
        			if(prev[j+1]!=Integer.MAX_VALUE) {
        				curr[i]=Math.min(curr[i],max+prev[j+1]);
        			}
        		}
        	}
        	prev=curr;
        }
        
        
        return prev[0];
    }

    // =========================================================================
    // APPROACH 3: Monotonic Stack / Optimized DP
    // =========================================================================
    public int minDifficultyMonotonicStack(int[] jobDifficulty, int d) {
        // TODO: Implement Monotonic Stack / Optimized DP approach
        return -1;
    }

    // =========================================================================
    // TEST RUNNER & TEST CASES
    // =========================================================================
    
    static class TestCase {
        int id;
        int[] jobDifficulty;
        int d;
        int expectedOutput;
        String description;

        TestCase(int id, int[] jobDifficulty, int d, int expectedOutput, String description) {
            this.id = id;
            this.jobDifficulty = jobDifficulty;
            this.d = d;
            this.expectedOutput = expectedOutput;
            this.description = description;
        }
    }

    public static void main(String[] args) {
        MinimumDifficultyOfAJobSchedule solver = new MinimumDifficultyOfAJobSchedule();

        TestCase[] testCases = new TestCase[] {
            // Case 1: Standard decreasing order
            new TestCase(1, new int[]{6, 5, 4, 3, 2, 1}, 2, 7, "Standard decreasing order array"),
            
            // Case 2: Impossible schedule (more days than jobs)
            new TestCase(2, new int[]{9, 9, 9}, 4, -1, "Impossible schedule (d > jobDifficulty.length)"),
            
            // Case 3: Exactly 1 job per day
            new TestCase(3, new int[]{1, 1, 1}, 3, 3, "d equals number of jobs (1 job per day)"),
            
            // Case 4: Single day schedule (d = 1)
            new TestCase(4, new int[]{7, 1, 7, 1, 7, 1}, 1, 7, "Single day schedule (d = 1)"),
            
            // Case 5: Array containing zeros
            new TestCase(5, new int[]{0, 0, 0, 0}, 2, 0, "All zero difficulties"),
            
            // Case 6: Strictly increasing difficulties
            new TestCase(6, new int[]{1, 2, 3, 4, 5, 6}, 3, 11, "Strictly increasing order array"),
            
            // Case 7: Sharp spike in the middle
            new TestCase(7, new int[]{10, 1, 1, 100, 1, 10}, 3, 120, "Large spike in job difficulty"),
            
            // Case 8: Repeating values with moderate d
            new TestCase(8, new int[]{3, 2, 3, 2, 3, 2}, 4, 11, "Alternating elements with moderate days"),
            
            // Case 9: Large values / Upper boundary checks
            new TestCase(9, new int[]{1000, 1000, 1000, 1000, 1000}, 2, 2000, "Maximum possible element values"),
            
            // Case 10: Tricky grouping for minimal total sum
            new TestCase(10, new int[]{7, 1, 7, 1, 7, 1}, 3, 15, "Optimal multi-day grouping trade-off")
        };

        Scanner scanner = new Scanner(System.in);
        System.out.println("=================================================");
        System.out.println("   Minimum Difficulty of a Job Schedule Tester   ");
        System.out.println("=================================================");
        System.out.println("Select the approach to test:");
        System.out.println("1. Top-Down Dynamic Programming (Recursion + Memo)");
        System.out.println("2. Bottom-Up Dynamic Programming (Tabulation)");
        System.out.println("3. Monotonic Stack / Optimized DP");
        System.out.print("Enter choice (1, 2, or 3): ");

        int choice = scanner.nextInt();
        String approachName = "";

        switch (choice) {
            case 1:
                approachName = "Top-Down DP";
                break;
            case 2:
                approachName = "Bottom-Up DP";
                break;
            case 3:
                approachName = "Monotonic Stack / Optimized DP";
                break;
            default:
                System.out.println("Invalid choice. Exiting.");
                scanner.close();
                return;
        }

        System.out.println("\nRunning Test Cases for: " + approachName);
        System.out.println("-------------------------------------------------");

        int passed = 0;
        for (TestCase test : testCases) {
            int actualOutput;
            switch (choice) {
                case 1:
                    actualOutput = solver.minDifficultyTopDown(test.jobDifficulty.clone(), test.d);
                    break;
                case 2:
                    actualOutput = solver.minDifficultyBottomUp(test.jobDifficulty.clone(), test.d);
                    break;
                case 3:
                    actualOutput = solver.minDifficultyMonotonicStack(test.jobDifficulty.clone(), test.d);
                    break;
                default:
                    actualOutput = -1;
            }

            boolean isPass = (actualOutput == test.expectedOutput);
            if (isPass) {
                passed++;
                System.out.printf("[PASS] Test %2d: %s\n", test.id, test.description);
            } else {
                System.out.printf("[FAIL] Test %2d: %s\n", test.id, test.description);
                System.out.println("       Inputs   : jobDifficulty = " + Arrays.toString(test.jobDifficulty) + ", d = " + test.d);
                System.out.println("       Expected : " + test.expectedOutput);
                System.out.println("       Actual   : " + actualOutput);
            }
        }

        System.out.println("-------------------------------------------------");
        System.out.printf("Results: %d/%d Passed.\n", passed, testCases.length);
        System.out.println("=================================================");

        scanner.close();
    }
}