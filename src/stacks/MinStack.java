package stacks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * All operations (push, pop, top, getMin) must be O(1) time complexity.
 */
public class MinStack {

    Stack<Integer>st;
    Stack<Integer>min;

    public MinStack() {
        this.st=new Stack<>();
        this.min=new Stack<>();
    }

    public void push(int val) {
        if(st.isEmpty()){
            st.push(val);
            min.push(val);
        }else{
            st.push(val);
            min.push(Math.min(val,min.peek()));
        }
    }

    public void pop() {
        if(st.isEmpty())return;
        st.pop();
        min.pop();
    }

    public int top() {
        if(st.isEmpty())return -1;
        return st.peek();
    }

    public int getMin() {
        if(st.isEmpty())return -1;
        return min.peek();
    }

    public static void main(String[] args) {
        int passed = 0;
        int totalTests = 10;

        System.out.println("Running MinStack Tests...\n");

        // Test 1: Basic Example from Problem
        passed += runTest(1, new String[]{"push(-2)", "push(0)", "push(-3)", "getMin", "pop", "top", "getMin"},
                new Integer[]{null, null, null, -3, null, 0, -2});

        // Test 2: Decreasing values
        passed += runTest(2, new String[]{"push(3)", "push(2)", "push(1)", "getMin", "pop", "getMin", "pop", "getMin"},
                new Integer[]{null, null, null, 1, null, 2, null, 3});

        // Test 3: Increasing values
        passed += runTest(3, new String[]{"push(1)", "push(2)", "push(3)", "getMin", "pop", "getMin"},
                new Integer[]{null, null, null, 1, null, 1});

        // Test 4: Duplicate minimums (Crucial edge case)
        passed += runTest(4, new String[]{"push(5)", "push(5)", "push(2)", "push(2)", "pop", "getMin", "pop", "getMin"},
                new Integer[]{null, null, null, null, null, 2, null, 5});

        // Test 5: Negative numbers mix
        passed += runTest(5, new String[]{"push(-10)", "push(14)", "getMin", "push(-20)", "getMin", "pop", "getMin"},
                new Integer[]{null, null, -10, null, -20, null, -10});

        // Test 6: Integer boundaries
        passed += runTest(6, new String[]{"push(2147483647)", "push(-2147483648)", "getMin", "top", "pop", "getMin"},
                new Integer[]{null, null, -2147483648, -2147483648, null, 2147483647});

        // Test 7: Interleaved operations
        passed += runTest(7, new String[]{"push(10)", "push(20)", "getMin", "pop", "push(5)", "getMin"},
                new Integer[]{null, null, 10, null, null, 5});

        // Test 8: Min is at the bottom
        passed += runTest(8, new String[]{"push(0)", "push(5)", "push(10)", "getMin", "pop", "pop", "getMin"},
                new Integer[]{null, null, null, 0, null, null, 0});

        // Test 9: Stack with single element
        passed += runTest(9, new String[]{"push(42)", "getMin", "top"},
                new Integer[]{null, 42, 42});

        // Test 10: Repeated push/pop of the same min
        passed += runTest(10, new String[]{"push(1)", "push(1)", "pop", "getMin", "push(0)", "getMin", "pop", "getMin"},
                new Integer[]{null, null, null, 1, null, 0, null, 1});

        System.out.println("\n-----------------------------------------------------------------------");
        System.out.printf("Final Result: %d/%d Tests Passed%n", passed, totalTests);
    }

    private static int runTest(int id, String[] ops, Integer[] expected) {
        MinStack ms = new MinStack();
        boolean testPassed = true;
        List<String> results = new ArrayList<>();

        for (int i = 0; i < ops.length; i++) {
            String op = ops[i];
            Integer exp = expected[i];
            Integer actual = null;

            try {
                if (op.startsWith("push")) {
                    int val = Integer.parseInt(op.substring(5, op.length() - 1));
                    ms.push(val);
                } else if (op.equals("pop")) {
                    ms.pop();
                } else if (op.equals("top")) {
                    actual = ms.top();
                } else if (op.equals("getMin")) {
                    actual = ms.getMin();
                }

                if (exp != null && !exp.equals(actual)) {
                    testPassed = false;
                }
            } catch (Exception e) {
                testPassed = false;
                System.err.println("Test " + id + " crashed during operation: " + op);
                break;
            }
        }

        System.out.printf("Test %-2d: %s%n", id, testPassed ? "PASSED" : "FAILED");
        if (!testPassed) {
            System.out.println("   Operations: " + Arrays.toString(ops));
            System.out.println("   Expected outcomes: " + Arrays.toString(expected));
        }
        return testPassed ? 1 : 0;
    }
}