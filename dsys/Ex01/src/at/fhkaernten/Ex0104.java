package at.fhkaernten;

import java.util.Scanner;

public class Ex0104 {

	private static final int SIZE = 32;

	private static void printArray(int[] array) {
		System.out.print("[");
		for (int i = 0; i < array.length; i++) {
			if (i > 0)
				System.out.print(",");
			System.out.print(array[i]);
		}
		System.out.println("]");
	}

	// 4.2
	private static void fillArray(int[] array) {
		for (int i = 0; i < array.length; i++)
			array[i] = (int) (Math.random() * 10);
	}

	// 4.3
	private static int firstPositionOf(int[] a, int n) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == n) {
				return i;
			}
		}
		return -1;
	}

	// 4.4
	private static int occurences(int[] a, int n) {
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == n) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		// 4.1
		int[] x = new int[SIZE];
		Scanner sc = new Scanner(System.in);

		printArray(x);
		fillArray(x);
		printArray(x);
		System.out.println("Zahl suchen: ");
		int n = sc.nextInt();
		System.out.println("Erster Index: " + firstPositionOf(x, n));
		System.out.println("Anzahl: " + occurences(x, n));

		sc.close();

		// 4.5
		// If data is passed by value, the data is copied from the variable used
		// for example in main() to a variable used by the function, such in
		// this examples Ex04.
		// If data is passed by reference, a pointer to the data is copied
		// instead of the actual variable as is done in a call by value.

	}
}