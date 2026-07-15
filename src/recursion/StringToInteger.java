package recursion;

public class StringToInteger {

    /**
     * Converts a string to a 32-bit signed integer (atoi).
     * * @param s The input string.
     * @return The converted integer.
     */
    public int myAtoi(String s) {
        if(s.isBlank())return 0;
        long number=0;
        int n=s.length(),idx=0,sign=1;
        char[]arr=s.toCharArray();
        while(idx<n&&arr[idx]==' ' )idx++;
        if(arr[idx]=='-') {
            sign = -1;
            idx++;
        }else if(arr[idx]=='+')idx++;
        if(!Character.isDigit(arr[idx]))return 0;
        for(int i=idx;i<n;i++){
            if(!Character.isDigit(arr[i]))break;
            number=number*10 +(int)(arr[i]-'0');
            if(sign==-1&& -number<Integer.MIN_VALUE)return Integer.MIN_VALUE;
            if(sign==1&& number>Integer.MAX_VALUE)return Integer.MAX_VALUE;
        }
        return sign*Math.toIntExact(number);
    }

    public static void main(String[] args) {
        StringToInteger converter = new StringToInteger();

        String[] inputs = {
                "42",                       // Basic positive
                "   -42",                   // Leading whitespace and negative
                "1337c0d3",                 // Trailing non-digits
                "0-1",                      // Non-digit after first digit
                "words and 987",            // Leading words
                "-91283472332",             // Underflow (below MIN_VALUE)
                "91283472332",              // Overflow (above MAX_VALUE)
                "  +0000000000012345678",   // Leading zeros and plus sign
                " ",                        // Only whitespace
                "+-12"                      // Multiple signs (invalid)
        };

        int[] expected = {
                42,
                -42,
                1337,
                0,
                0,
                -2147483648,
                2147483647,
                12345678,
                0,
                0
        };

        int passed = 0;

        System.out.println("Running Test Cases...\n" + "=".repeat(50));

        for (int i = 0; i < inputs.length; i++) {
            int result = converter.myAtoi(inputs[i]);
            boolean isMatch = result == expected[i];

            if (isMatch) passed++;

            System.out.printf("Test Case %d:\n", i + 1);
            System.out.printf("Input:    \"%s\"\n", inputs[i]);
            System.out.printf("Expected: %d\n", expected[i]);
            System.out.printf("Actual:   %d\n", result);
            System.out.printf("Result:   %s\n", isMatch ? "PASSED ✅" : "FAILED ❌");
            System.out.println("-".repeat(30));
        }

        System.out.printf("\nFinal Result: %d/%d Passed\n", passed, inputs.length);
    }
}