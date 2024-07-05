package BasicProblemSolving;

import java.util.Scanner;

/*Given a positive integer num, return true if num is a perfect square or false otherwise.

A perfect square is an integer that is the square of an integer. In other words, it is the product of some integer with itself.

You must not use any built-in library function, such as sqrt.
Input Format

Each of the test cases have one line containing the number num.
Constraints

1 <= n <= 2^31-1
Output Format

print true if the number is a perfect square else print false
Sample Input 0

16
Sample Output 0

true*/
public class PerfectSquare {
	public static void main(String[] args) {
		Scanner in= new Scanner(System.in);
		System.out.println("Enter the number:");
		int n=in.nextInt();
		int i=1,ans=1;
		while(i*i<=n) {
			ans=i;
			i++;
		}
		System.out.println("Perfect Square:"+((ans*ans==n)?true:false));
		in.close();
	}
}
