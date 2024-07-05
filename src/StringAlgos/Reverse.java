package StringAlgos;

public class Reverse {
	public static String reverse(String str) {
		char []ch=str.toCharArray();
		int s=0,e=ch.length-1;
		while(s<e) {
			char t= ch[s];
			ch[s]=ch[e];
			ch[e]=t;
			s++;
			e--;			
		}
		return String.valueOf(ch);
	}
	public static void main(String[]args) {
		String str="Anish";
		System.out.println(reverse(str));
	}
}
