package BasicProblemSolving;

import java.util.Scanner;

public class IsPrime {
    /**
     * Determines whether a given number is prime.
     * 
     * @param n The number to check for primality
     * @return true if the number is prime, false otherwise
     */
    public static boolean isPrime(int n) {
        // Base cases
        if (n <= 1)
            return false;  // Numbers less than or equal to 1 are not prime
        if (n == 2 || n == 3)
            return true;   // 2 and 3 are prime numbers
        if (n % 2 == 0 || n % 3 == 0)
            return false;  // Numbers divisible by 2 or 3 are not prime

        // Check for divisibility by numbers up to the square root of n
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0)
                return false;  // If n is divisible by i or (i+2), it's not prime
            /*Excellent question! This condition is a key optimization 
             * in the prime-checking algorithm.
             *  Let's break it down:

```java
if (n % i == 0 || n % (i + 2) == 0)
    return false;
```

This condition is used within the loop that starts with `i = 5` and increments `i`
 by 6 in each iteration (`i += 6`).
 Here's why it's used:

1. Pattern of Prime Numbers:
   After eliminating multiples of 2 and 3, all prime numbers follow the pattern 6k ± 1,
    where k is a positive integer. 
   This means prime numbers (except 2 and 3) can only be of the form 6k - 1 or 6k + 1.

2. Checking Two Numbers at Once:
   - `i` represents numbers of the form 6k - 1
   - `i + 2` represents numbers of the form 6k + 1

3. Efficiency:
   By checking both `i` and `i + 2` in a single iteration, we cover all potential prime
    factors without
    checking numbers that are definitely not prime (like 6k, 6k + 2, 6k + 3, 6k + 4).

4. Example:
   When i = 5:
   - We check if n is divisible by 5 (6 * 1 - 1)
   - We also check if n is divisible by 7 (6 * 1 + 1)
   
   Next iteration, i = 11:
   - We check if n is divisible by 11 (6 * 2 - 1)
   - We also check if n is divisible by 13 (6 * 2 + 1)

   And so on...

5. */
        }

        // If no divisors found, the number is prime
        return true;
    }

    /**
     * Main method to run the program.
     * Prompts the user for input and displays whether the input is prime.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number:");
        int n = in.nextInt();
        System.out.println("Is prime result: \t" + isPrime(n));
        in.close();
    }
}