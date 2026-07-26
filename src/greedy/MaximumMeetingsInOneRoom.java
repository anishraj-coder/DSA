package greedy;

import java.util.ArrayList;
import java.util.List;

public class MaximumMeetingsInOneRoom {

    public ArrayList<Integer> maxMeetings(int n, int[] s, int[] f) {
    	
    	List<Pair> list=new ArrayList<>();
    	for(int i=0;i<n;i++) {
    		list.add(new Pair(s[i],f[i],i));
    		
    	}
    	
    	list.sort(
    			(a,b)->a.e-b.e);
    	ArrayList<Integer>ans=new ArrayList<>();
    	int e=-1;
    	for(int i=0;i<n;i++) {
    		if(i==0) {
    			ans.add(list.get(i).pos);
    			e=list.get(i).e;
    			continue;
    		}
    		Pair p=list.get(i);
    		if(p.s<=e)continue;
    		else {
    			ans.add(p.pos);
    			e=p.e;
    		}
    	}
    	ans.sort((a,b)->a-b);
        return ans;
    }
    
    private static class Pair{
    	public int s,e,pos;
    	Pair(int s,int e,int pos){
    		this.s=s;
    		this.e=e;
    		this.pos=pos+1;
    	}
    }

    public static void main(String[] args) {
        MaximumMeetingsInOneRoom solver = new MaximumMeetingsInOneRoom();
        int passed = 0;
        int failed = 0;

        TestCase[] testCases = new TestCase[] {
            // 1. Example 1 from problem statement
            new TestCase(
                new int[]{1, 3, 0, 5, 8, 5},
                new int[]{2, 4, 6, 7, 9, 9},
                new int[]{1, 2, 4, 5}
            ),

            // 2. Example 2: Single meeting
            new TestCase(
                new int[]{3},
                new int[]{7},
                new int[]{1}
            ),

            // 3. All overlapping meetings (Only 1 can be chosen, tie-breaker: smallest finish time, then smallest index)
            new TestCase(
                new int[]{1, 1, 1},
                new int[]{5, 3, 4},
                new int[]{2}
            ),

            // 4. Consecutive meetings where start time equals finish time of previous (Strict inequality constraint: start > prev_finish)
            new TestCase(
                new int[]{1, 2, 3},
                new int[]{2, 3, 4},
                new int[]{1, 3}
            ),

            // 5. Tie-breaker rule check: Same finish time, pick smaller original 1-based index
            new TestCase(
                new int[]{1, 2, 0},
                new int[]{5, 5, 5},
                new int[]{1}
            ),

            // 6. Already sorted non-overlapping meetings
            new TestCase(
                new int[]{1, 5, 10},
                new int[]{3, 8, 12},
                new int[]{1, 2, 3}
            ),

            // 7. Unsorted meetings requiring proper index tracking
            new TestCase(
                new int[]{12, 1, 5},
                new int[]{15, 3, 8},
                new int[]{2, 3, 1}
            ),

            // 8. Zero-duration meetings (s[i] == f[i])
            new TestCase(
                new int[]{2, 2, 3},
                new int[]{2, 3, 3},
                new int[]{1, 3}
            ),

            // 9. Multiple overlapping with distinct choice prioritizing earlier finish time
            new TestCase(
                new int[]{1, 2, 3, 4, 7, 8, 9, 10},
                new int[]{10, 4, 6, 5, 8, 9, 10, 11},
                new int[]{2, 4, 5, 6, 8}
            ),

            // 10. Boundary inputs with large time values
            new TestCase(
                new int[]{0, 1000000000, 500000000},
                new int[]{500000000, 1000000000, 900000000},
                new int[]{1, 2}
            )
        };

        System.out.println("--- Running Test Cases ---\n");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            ArrayList<Integer> result = solver.maxMeetings(tc.s.length, tc.s, tc.f);
            
            boolean isPass = tc.expected.equals(result);

            if (isPass) {
                passed++;
                System.out.printf("[PASS] Test %2d | Output: %s\n", i + 1, result);
            } else {
                failed++;
                System.out.printf("[FAIL] Test %2d | Expected: %s, Got: %s\n", 
                                  i + 1, tc.expected, result);
            }
        }

        System.out.println("\n--------------------------");
        System.out.printf("Results: %d Passed, %d Failed\n", passed, failed);
    }

    private static class TestCase {
        int[] s;
        int[] f;
        ArrayList<Integer> expected;

        TestCase(int[] s, int[] f, int[] expectedArray) {
            this.s = s;
            this.f = f;
            this.expected = new ArrayList<>();
            for (int val : expectedArray) {
                this.expected.add(val);
            }
        }
    }
}