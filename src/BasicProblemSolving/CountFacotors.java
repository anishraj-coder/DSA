package BasicProblemSolving;

import java.util.Scanner;

public class CountFacotors {
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the number:");
		int n=in.nextInt(),count=0;
		for(int i=1;i*i<=n;i++) {
			if(n%i==0) {
				if(n/i==i) 
					count++;
				else count+=2;
			}
		}
		System.out.println("NO. of factors=\t"+count);
		in.close();
	}
}
