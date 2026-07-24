package greedy;

import java.util.Arrays;

public class LemonadeChange {

    // Implement your solution here
    public boolean lemonadeChange(int[] arr) {
        int n=arr.length,five=0,ten=0,twenty=0;
        for(int i=0;i<n;i++){
            if(arr[i]==5)five++;
            else if(arr[i]==10){
                if(five==0)return false;
                five--;
                ten++;
            }else if(arr[i]==20){
                twenty++;
                if(ten>=1&&five>=1){
                    ten--;five--;
                }else if(five>=3)five-=3;
                else return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LemonadeChange runner = new LemonadeChange();

        TestCase[] testCases = new TestCase[] {
                // Test 1: Standard Example 1 (Enough change for $20 using $10 + $5)
                new TestCase(
                        new int[]{5, 5, 5, 10, 20},
                        true
                ),
                // Test 2: Standard Example 2 (Not enough $5 bills to give change)
                new TestCase(
                        new int[]{5, 5, 10, 10, 20},
                        false
                ),
                // Test 3: First customer gives a $10 bill (Immediate failure)
                new TestCase(
                        new int[]{10},
                        false
                ),
                // Test 4: First customer gives a $20 bill (Immediate failure)
                new TestCase(
                        new int[]{20},
                        false
                ),
                // Test 5: Single customer with exact change ($5)
                new TestCase(
                        new int[]{5},
                        true
                ),
                // Test 6: Giving $15 change using three $5 bills (No $10 available)
                new TestCase(
                        new int[]{5, 5, 5, 5, 20},
                        true
                ),
                // Test 7: Preferring $10 + $5 over three $5 bills for $20 change (Greedy strategy)
                new TestCase(
                        new int[]{5, 5, 5, 10, 5, 10, 5, 10, 20},
                        true
                ),
                // Test 8: Running out of $5 bills while retaining extra $10 bills
                new TestCase(
                        new int[]{5, 5, 10, 20, 5, 20},
                        false
                ),
                // Test 9: Alternating $5 and $10 bills successfully
                new TestCase(
                        new int[]{5, 10, 5, 10, 5, 10},
                        true
                ),
                // Test 10: Long sequence of mixed transactions leading to failure
                new TestCase(
                        new int[]{5, 5, 5, 5, 10, 5, 10, 10, 10, 20},
                        false
                )
        };

        int passed = 0;

        System.out.println("==========================================================");
        System.out.println("          RUNNING TEST CASES FOR DSA PROBLEM               ");
        System.out.println("==========================================================\n");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];

            // Make a deep copy so user's implementation doesn't mutate raw input
            int[] billsCopy = tc.bills.clone();

            boolean actual = runner.lemonadeChange(billsCopy);
            boolean isPass = actual == tc.expected;

            if (isPass) {
                passed++;
                System.out.printf("[PASS] Test Case %2d\n", i + 1);
            } else {
                System.out.printf("[FAIL] Test Case %2d\n", i + 1);
                System.out.println("       Inputs   : bills = " + Arrays.toString(tc.bills));
                System.out.println("       Expected : " + tc.expected);
                System.out.println("       Actual   : " + actual);
            }
            System.out.println("----------------------------------------------------------");
        }

        System.out.printf("\nRESULTS: %d / %d Test Cases Passed.\n", passed, testCases.length);
        System.out.println("==========================================================");
    }

    private static class TestCase {
        int[] bills;
        boolean expected;

        TestCase(int[] bills, boolean expected) {
            this.bills = bills;
            this.expected = expected;
        }
    }
}