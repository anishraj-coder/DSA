package Queue;

import java.util.LinkedList;
import java.util.Queue;

public class KthElement {
	public static String kthElement(int k) {
		Queue<String> q = new LinkedList<>();
		String ans = "";
		q.add("1");
		q.add("2");
		for (int i = 0; i < k; i++) {
			String temp = q.remove();
			if (k - 1 == i)
				ans = temp;
			q.add(temp + "1");
			q.add(temp + "2");
		}
		return ans;
	}

	public static String kthElementPalindrome(int k) {
		Queue<String> q = new LinkedList<>();
		String ans = "";
		q.add("11");
		q.add("22");
		for (int i = 0; i < k; i++) {
			String temp = q.remove();
			if (k - 1 == i) {
				ans = temp;
				break;
			}
			String left = temp.substring(0, temp.length() / 2);
			String right = temp.substring(temp.length() / 2, temp.length());
			q.add(left + "11" + right);
			q.add(left + "22" + right);
		}
		return ans;
	}

	public static void main(String[] args) {
		System.out.println(kthElement(5));
		System.out.println(kthElementPalindrome(5));

	}

}
