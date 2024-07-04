package Array;

public class KthRotation {
	public static void rev(int[]arr,int s,int e) {
		while(s<e) {
			int t= arr[s];
			arr[s]=arr[e];
			arr[e]=t;
			s++;
			e--;
		}
	}
	public static void fromBack(int[]arr,int k) {
		rev(arr,0,arr.length-1);
		rev(arr,0,k-1);
		rev(arr,k,arr.length-1);
	}
	public static void fromFront(int[]arr,int k) {
		rev(arr,0,arr.length-1);
		rev(arr,0,arr.length-k-1);
		rev(arr,arr.length-k,arr.length-1);
	}
	public static void view(int[]arr) {
		for(int i: arr)
			System.out.print(i);
		System.out.println();
	}
	public static void main(String[] args) {
		int[]arr= {1,2,3,4,5,6,7,8,9};
		fromBack(arr,3);
		view(arr);
		for(int i=0;i<arr.length;i++)
			arr[i]=i+1;
		fromFront(arr,3);
		view(arr);
		
	}

}
