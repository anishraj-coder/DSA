package Array;

import java.util.Scanner;

public class reverse {
	public static void rev(int[]arr,int s, int e) {
		while(s<e) {
			int t= arr[s];
			arr[s]=arr[e];
			arr[e]=t;
			s++;
			e--;
		}
	}

	public static void main(String[] args) {
//		Scanner in= new Scanner(System.in);
//		int n= in.nextInt();
//		int[] arr= new int[n];
//		for(int i=0;i<n;i++)
//			arr[i]=in.nextInt();
		int[]arr= {1,2,3,4,5,6,7,8,9};
		rev(arr,0,arr.length-1);
		for(int i:arr)
			System.out.println(i);
//		in.close();
	}

}
