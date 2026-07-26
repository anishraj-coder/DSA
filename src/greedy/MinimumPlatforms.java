package greedy;



import java.util.Arrays;

public class MinimumPlatforms {

    public int findPlatform(int[] s, int[] f) {
    	int n=s.length;
    	if(n<=1)return n;
    	Arrays.sort(s);
    	Arrays.sort(f);
    	int i=0,j=0,count=0,max=0;
    	while(i<n&&j<n) {
    		if(s[i]<=f[j]) {
    			count++;
    			i++;
    		}else {
    			count--;
    			j++;
    		}
    		max=Math.max(count,max);
    	}
        return max;
    }

    public static void main(String[] args) {
        MinimumPlatforms mp = new MinimumPlatforms();

        int[][][] testCases = {
            // Test 1: Standard case (Example 1)
            {{900, 940, 950, 1100, 1500, 1800}, {910, 1200, 1120, 1130, 1900, 2000}},
            
            // Test 2: Mutually exclusive trains (Example 2)
            {{900, 1235, 1100}, {1000, 1240, 1200}},
            
            // Test 3: Unsorted inputs overlapping at peak time (Example 3)
            {{1000, 935, 1100}, {1200, 1240, 1130}},
            
            // Test 4: Single train (Edge Case)
            {{1000}, {1030}},
            
            // Test 5: Exact same arrival and departure time (departure happens first/simultaneously)
            {{900, 910}, {910, 920}},
            
            // Test 6: Same arrival times for all trains
            {{900, 900, 900}, {1000, 1030, 1100}},
            
            // Test 7: All trains overlapping concurrently
            {{100, 200, 300, 400}, {500, 500, 500, 500}},
            
            // Test 8: Chain of trains arriving immediately as previous departs
            {{800, 900, 1000}, {900, 1000, 1100}},
            
            // Test 9: Times with leading single/double digit hours (e.g., 0, 50)
            {{0, 50, 100}, {30, 90, 120}},
            
            // Test 10: Midnight edge cases (0000 to 2359)
            {{0, 100, 2300}, {2359, 200, 2359}}
        };

        int[] expectedResults = {
            3, // Test 1
            1, // Test 2
            3, // Test 3
            1, // Test 4
            1, // Test 5
            3, // Test 6
            4, // Test 7
            1, // Test 8
            2, // Test 9
            3  // Test 10
        };

        int passed = 0;
        System.out.println("--- Running Test Cases ---\n");

        for (int i = 0; i < testCases.length; i++) {
            int actual = mp.findPlatform(testCases[i][0], testCases[i][1]);
            int expected = expectedResults[i];
            boolean isPass = actual == expected;

            if (isPass) {
                passed++;
                System.out.printf("Test Case %2d: PASSED\n", i + 1);
            } else {
                System.out.printf("Test Case %2d: FAILED | Expected: %-3d | Actual: %-3d\n", 
                                  i + 1, expected, actual);
            }
        }

        System.out.printf("\nResult: %d/%d Test Cases Passed.\n", passed, testCases.length);
    }
}