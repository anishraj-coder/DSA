package BasicProblemSolving;

import java.util.Scanner;

/*You need to find the square root of a given number N. If N is not a perfect square, then floor(√x) must be returned.
Input Format

Each of the test cases have one line containing the number N.
Constraints

1 ≤ N ≤ 10^9
Output Format

Output a line containing the required output for above query.
Sample Input 0

4
Sample Output 0

2
Sample Input 1

11
Sample Output 1

3*/


public class SqrtFloor {

	public static void main(String[] args) {
		Scanner in= new Scanner (System.in);
		System.out.println("Enter the number:");
		int n= in.nextInt();
		int i=1,ans=1;
		while(i*i<=n) {
			ans=i;
			i++;
		}
		System.out.println("floor(Sqrt(n)):\t"+ans);
		in.close();
	}

}
