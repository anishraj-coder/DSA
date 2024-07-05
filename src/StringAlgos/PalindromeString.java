package StringAlgos;

public class PalindromeString {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		String a="Mom";
		a=a.toUpperCase();
		if(a.equals((new Reverse().reverse(a))))
			System.out.println(true);
		else System.out.println(false);
	}

}
