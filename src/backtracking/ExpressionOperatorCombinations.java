package backtracking;

import java.util.*;

/**
 * Problem: Expression Add Operators
 * Given a string num and an integer target, return all possible expressions
 * by inserting '+', '-', or '*' such that the expression evaluates to the target.
 */
public class ExpressionOperatorCombinations {

    /**
     * Implement this method to find all valid expressions.
     *
     * Constraints:
     * - No leading zeros (e.g., "1+05" is invalid).
     * - Standard operator precedence applies (Multiplication before Addition/Subtraction).
     * - Use long for intermediate calculations to avoid overflow.
     */
    public List<String> addOperators(String num, int target) {
        List<String>ans=new ArrayList<>();
        helper(num,target,0,0,0,new StringBuilder(),ans);
        return ans;
    }

    private void helper(String num,int target,int idx,long val,long lastOpr,StringBuilder curr,List<String>ans){
        int n=num.length();
        if(idx==n){
            if(val==target)ans.add(curr.toString());
            return;
        }
        for(int i=idx;i<n;i++){
            if(i>idx&&num.charAt(i)=='0')continue;
            StringBuilder ch=new StringBuilder(String.valueOf(num.charAt(i)));
            long opr=Long.parseLong(ch.toString());
            if(idx==0){
                curr.append(opr);
                helper(num,target,i+1,opr,opr,curr,ans);
                curr.setLength(0);
            }else{
                int length=curr.length();
                curr.append('+').append(opr);
                helper(num,target,i+1,val+opr,opr,curr,ans);
                curr.setLength(length);
                curr.append('-').append(ch);
                helper(num,target,i+1,val-opr,opr,curr,ans);
                curr.setLength(length);
                curr.append('*').append(ch);
                helper(num,target,i+1,val-lastOpr+lastOpr*opr,lastOpr*opr,curr,ans);
                curr.setLength(length);
            }

        }
    }

    public static void main(String[] args) {
        ExpressionOperatorCombinations solver = new ExpressionOperatorCombinations();
        int passed = 0;

        // Test Case Data: {num, target, expected_output_list}
        Object[][] testCases = {
                {"123", 6, Arrays.asList("1+2+3", "1*2*3")},
                {"232", 8, Arrays.asList("2+3*2", "2*3+2")},
                {"105", 5, Arrays.asList("1*0+5", "10-5")},
                {"00", 0, Arrays.asList("0+0", "0-0", "0*0")},
                {"3456237490", 9191, Collections.emptyList()},
                {"12", 3, Arrays.asList("1+2")},
                {"2147483647", 2147483647, Arrays.asList("2147483647")},
                {"123456789", 45, Arrays.asList("1+2+3+4+5+6+7+8+9")}, // Large sequence
                {"1000000009", 9, Arrays.asList("1*0+0+0+0+0+0+0+0+9", "10-0-0-0-0-0-0-0-1+0", "1*0*0*0*0*0*0*0*0+9", "0+0+0+0+0+0+0+0+0+9")}, // Simplified expectations (Truncated for display)
                {"9", 9, Arrays.asList("9")},
                {"0", 0, Arrays.asList("0")},
                {"123", 24, Collections.emptyList()}
        };

        System.out.println("Running Tests...\n" + "=".repeat(60));

        for (int i = 0; i < testCases.length; i++) {
            String num = (String) testCases[i][0];
            int target = (int) testCases[i][1];
            List<String> expected = new ArrayList<>((List<String>) testCases[i][2]);

            List<String> actual = solver.addOperators(num, target);

            // Sort both for comparison since order doesn't matter in the result
            Collections.sort(expected);
            if (actual != null) Collections.sort(actual);

            boolean isMatch = expected.equals(actual);
            if (isMatch) passed++;

            System.out.printf("Test Case %d: num=\"%s\", target=%d\n", i + 1, num, target);
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);
            System.out.println("Result:   " + (isMatch ? "✅ PASSED" : "❌ FAILED"));
            System.out.println("-".repeat(60));
        }

        System.out.printf("\nFinal Result: %d/%d Passed\n", passed, testCases.length);
    }
}