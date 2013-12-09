package at.fhkaernten;

import java.util.Scanner;

public class Ex0202 {

	static int N = 3;

	public static void main(String[] args) {
		// 2.1

		Scanner sc = new Scanner(System.in);
		Threads1[] r = new Threads1[N];
		Thread[] t = new Thread[N];
		String abort;

		Threads1.createThreadArray(t, r);

		do {
			System.out.println("Sollen die Threads beendet werden? [y/n]");
			abort = sc.next();
		} while (!abort.equals("y"));

		Threads1.stopThreadArray(r);

		// 2.2

		System.out.println("Wie viele Threads sollen erzeugt werden?");
		int n = sc.nextInt();
		r = new Threads1[n];
		t = new Thread[n];
		int[] delay = new int[n];
		for (int i = 0; i < n; i++) {
			System.out.println("Delay für Thread " + (i + 1));
			delay[i] = sc.nextInt();
		}

		Threads1.createThreadArray(t, r, delay);

		do {
			System.out.println("Sollen die Threads beendet werden? [y/n]");
			abort = sc.next();
		} while (!abort.equals("y"));

		Threads1.stopThreadArray(r);

		(new Thread(new ClockThread())).start();

		sc.close();

	}

}
