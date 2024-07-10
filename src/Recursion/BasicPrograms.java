package Recursion;

public class BasicPrograms {
	public static int sum(int n) {
		if (n == 1)
			return 1;
		return n + sum(n - 1);
	}

	public static int fibonacci(int n) {
		if (n <= 1)
			return n;
		return fibonacci(n - 1) + fibonacci(n - 2);

	}

	public static void printIncr(int n) {
		if (n == 1) {
			System.out.print(1 + " ");
			return;
		}
		printIncr(n - 1);
		System.out.print(n + " ");
	}

	public static void printDecr(int n) {
		if (n == 1) {
			System.out.print(1 + " ");
			return;
		}
		System.out.print(n + " ");
		printDecr(n - 1);
	}

	public static void main(String[] args) {
		int n = 10;
		System.out.println("Sum=\t" + sum(n));
		System.out.println("Fibonacci=\t" + fibonacci(n));
		System.out.println("PrintIncr:");
		printIncr(n);
		System.out.println("\nPrintDecr:");
		printDecr(n);

	}

}
