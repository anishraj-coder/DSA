package recursion;

import java.util.*;

public class ParenthesesGenerator {

    /**
     * Generates all combinations of well-formed parentheses.
     * @param n the number of pairs of parentheses
     * @return a list of all valid combinations
     */
    public List<String> generateParenthesis(int n) {
        List<String>ans=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        helper(0,0,n,sb,ans);
        return ans;
    }

    private void helper(int open, int close, int n, StringBuilder curr,List<String>ans){
        if(open==n&&close==n){
            ans.add(curr.toString());
            return;
        }
        if(open<n){
            curr.append("(");
            helper(open+1,close,n,curr,ans);
            curr.deleteCharAt(curr.length()-1);
        }
        if(close<open){
            curr.append(")");
            helper(open,close+1,n,curr,ans);
            curr.deleteCharAt(curr.length()-1);
        }
    }

    public static void main(String[] args) {
        ParenthesesGenerator generator = new ParenthesesGenerator();

        // Test cases: Input n -> Expected Output List
        Map<Integer, List<String>> testCases = new LinkedHashMap<>();

        testCases.put(1, Arrays.asList("()"));
        testCases.put(2, Arrays.asList("(())", "()()"));
        testCases.put(3, Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()"));
        testCases.put(4, Arrays.asList(
                "(((())))", "((()()))", "((())())", "((()))()", "(()(()))",
                "(()()())", "(()())()", "(())(())", "(())()()", "()((()))",
                "()(()())", "()(())()", "()()(())", "()()()()"
        ));

        // Edge cases and higher constraints
        testCases.put(0, Arrays.asList("")); // Technically out of constraints (1-8) but good for safety

        int passed = 0;
        int total = 0;

        for (Map.Entry<Integer, List<String>> entry : testCases.entrySet()) {
            int n = entry.getKey();
            List<String> expected = new ArrayList<>(entry.getValue());
            List<String> actual = generator.generateParenthesis(n);

            // Sort both for comparison as order might vary in backtracking
            Collections.sort(expected);
            if (actual != null) Collections.sort(actual);

            boolean isMatch = expected.equals(actual);
            total++;
            if (isMatch) passed++;

            System.out.println("Test Case " + total + ": n = " + n);
            System.out.println("Expected: " + entry.getValue());
            System.out.println("Actual:   " + actual);
            System.out.println("Result:   " + (isMatch ? "PASSED ✅" : "FAILED ❌"));
            System.out.println("--------------------------------------------------");
        }

        // Additional hard test case for n=8 (Checking count only to avoid massive console output)
        System.out.println("Test Case 6: n = 8 (Hard/Constraint Limit)");
        List<String> resultN8 = generator.generateParenthesis(8);
        int expectedCountN8 = 1430; // 8th Catalan Number
        if (resultN8 != null && resultN8.size() == expectedCountN8) {
            System.out.println("Result:   PASSED ✅ (Found all " + expectedCountN8 + " combinations)");
            passed++;
        } else {
            System.out.println("Result:   FAILED ❌ (Expected " + expectedCountN8 + ", but got " + (resultN8 == null ? 0 : resultN8.size()) + ")");
        }
        total++;

        System.out.println("\nFinal Score: " + passed + "/" + total);
    }
}