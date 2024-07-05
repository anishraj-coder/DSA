package Array;
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
		int[]arr= {1,2,3,4,5,6,7,8,9};
		rev(arr,0,arr.length-1);
		for(int i:arr)
			System.out.println(i);
	}

}
