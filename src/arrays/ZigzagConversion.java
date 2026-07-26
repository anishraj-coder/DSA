package arrays;

import java.util.ArrayList;
import java.util.List;
public class ZigzagConversion {

    public String convert(String s, int rows) {
    	List<StringBuilder>list=new ArrayList<>();
    	for(int i=0;i<rows;i++)list.add(new StringBuilder());
    	int i=0;
    	boolean rev=false;
    	for(char ch:s.toCharArray()) {
    		list.get(i).append(ch);
    		i+=(rev)?1:-1;
    		if(i==0)rev=false;
    		else if(i==rows) {
    			rev=true;
    			i-=2;
    		}
    	}
    	StringBuilder ans=new StringBuilder();
    	for(StringBuilder curr:list) {
    		curr.append(curr);
    	}
        return ans.toString();
    }

    public static void main(String[] args) {
        ZigzagConversion zc = new ZigzagConversion();
        int passed = 0;
        int failed = 0;

        TestCase[] testCases = new TestCase[] {
            // 1. Example 1 from problem statement
            new TestCase("PAYPALISHIRING", 3, "PAHNAPLSIIGYIR"),

            // 2. Example 2 from problem statement
            new TestCase("PAYPALISHIRING", 4, "PINALSIGYAHRPI"),

            // 3. Example 3: Single character string, numRows = 1
            new TestCase("A", 1, "A"),

            // 4. Edge Case: numRows = 1 with longer string (no zigzag occurs)
            new TestCase("ABCDE", 1, "ABCDE"),

            // 5. Edge Case: numRows equals string length
            new TestCase("HELLOMOM", 8, "HELLOMOM"),

            // 6. Edge Case: numRows is strictly greater than string length
            new TestCase("JAVA", 10, "JAVA"),

            // 7. Edge Case: 2 rows (simple alternating pattern)
            new TestCase("ABCDEFGH", 2, "ACEGBDFH"),

            // 8. Strings containing punctuation as per constraints (',', '.')
            new TestCase("A,B.C,D.E", 3, "A.EC,D,B."),

            // 9. Two-character string with numRows = 3 (shorter than numRows but > 1)
            new TestCase("AB", 3, "AB"),

            // 10. Repeated characters with 3 rows
            new TestCase("AAAAAA", 3, "AAAAAA")
        };

        System.out.println("--- Running Test Cases ---\n");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            String result = zc.convert(tc.s, tc.numRows);
            boolean isPass = tc.expected.equals(result);

            if (isPass) {
                passed++;
                System.out.printf("[PASS] Test %2d: input=\"%s\", numRows=%d | Output: \"%s\"\n", 
                                  i + 1, tc.s, tc.numRows, result);
            } else {
                failed++;
                System.out.printf("[FAIL] Test %2d: input=\"%s\", numRows=%d | Expected: \"%s\", Got: \"%s\"\n", 
                                  i + 1, tc.s, tc.numRows, tc.expected, result);
            }
        }

        System.out.println("\n--------------------------");
        System.out.printf("Results: %d Passed, %d Failed\n", passed, failed);
    }

    private static class TestCase {
        String s;
        int numRows;
        String expected;

        TestCase(String s, int numRows, String expected) {
            this.s = s;
            this.numRows = numRows;
            this.expected = expected;
        }
    }
}