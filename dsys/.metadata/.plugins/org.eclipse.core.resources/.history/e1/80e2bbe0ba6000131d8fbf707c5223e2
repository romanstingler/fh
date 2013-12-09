package at.fhkaernten;

import java.util.Scanner;

public class Ex0203 {

	public static void main(String[] args) throws InterruptedException {
		// 3.1
		int n = 0;
		Scanner sc = new Scanner(System.in);

		System.out.println("Anzahl Threads: ");
		n = sc.nextInt();
		sc.close();

		long start = System.currentTimeMillis();
		Threads2[] t = new Threads2[n];
		for (int i = 0; i < n; i++) {
			t[i] = new Threads2();
			t[i].start();
		}

		// 3.3

		for (int i = 0; i < n; i++) {
			t[i].join();
			System.out.println("Thread: " + t[i].getId() + " Ergebnis: "
					+ t[i].getSum());
		}
		long end = System.currentTimeMillis();
		System.out.println("Zeit: " + (end - start) + "ms");
	}

}
